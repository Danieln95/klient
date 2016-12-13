package sdk.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import sdk.connection.Connection;
import sdk.connection.ResponseCallback;
import sdk.connection.ResponseParser;
import sdk.models.Lecture;
import sdk.models.Review;
import sdk.models.User;
import sdk.models.Study;
import sdk.models.Course;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class Service {

    //Vi skal have fat i vores connection object
    private Connection connection;
    private Gson gson;

    public Service() {
        this.connection = new Connection();
        this.gson = new Gson();
    }

/*
K01 :​ Klient skal kunne logge ind og ud                                        (login) @POST
K02 ​: Klient skal kunne skrive en kommentar til en lektioner                   (addReview) @POST
K03 ​: Klient skal kunne se andres kommentarer og bedømmelser                   (getReviews) @GET
K04 ​: Klient skal kunne bedømme lektioner (Rating og evt. kommentere)          (addReview) @POST
K05 ​: Klient skal kunne modtage lektioner fra serveren                         (getLectures) @GET
K06 ​: Klient skal kunne se samlet deltagelse af lektion                        (getCourses) @GET
K07 :​ Klient skal kunne slette egen kommentar                                  (deleteReview) @PUT
K08 ​: Klient (admin-bruger) skal kunne slette kommentarer                      (deleteReview) @PUT men sletter hele reviewet, hvilket ikke var meningen men at den bare skulle slette kommentaren.

@GET - WORKS
getLectures K05
getCourses  K06
getReviews  K03

@POST - WORKS
login       K01
addReview   K02, K04

@PUT - WORKS
deleteReview  K07, K08

_________________________

Testet og Virker
    -Login
    -getLecture
    -getCourses
    -getReviews
    -addReview
    -deleteReview

________________________

TUI:
    -addReview
    -getReview
    -getLecture
    -getCourses

--- HUSK AT TJEKKE API FOR ALLE ---
 */



//K01
    public void login(User user , final ResponseCallback<User> responseCallback) {
        try {

            HttpPost postRequest = new HttpPost(Connection.serverURL + "/login");
            postRequest.addHeader("Content-Type", "application/json");
            StringEntity jsonUser = new StringEntity(gson.toJson(user));
            postRequest.setEntity(jsonUser);

            this.connection.execute(postRequest, new ResponseParser() {
                public void payload(String json) {
                    User newUser = gson.fromJson(json, User.class);
                    responseCallback.success(newUser);
                }

                public void error(int status) {

                    responseCallback.error(status);
                }
            });

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

//K02
    public void addReview(Review review , final ResponseCallback<Review> responseCallback) {

        try {

            HttpPost postReview = new HttpPost(Connection.serverURL + "/student/review");

            postReview.addHeader("Content-Type", "application/json");
            StringEntity jsonReview = new StringEntity(gson.toJson(review));
            postReview.setEntity(jsonReview);

            this.connection.execute(postReview, new ResponseParser() {
                public void payload(String json) {
                    Review newReview = gson.fromJson(json, Review.class);
                   responseCallback.success(newReview);
               }

                public void error(int status) {

                    responseCallback.error(status);
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


//K03
    public void getReviews(Integer lectureId , final ResponseCallback<ArrayList<Review>> responseCallback) {
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/review/" + lectureId);
        this.connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {
                ArrayList<Review> reviews = gson.fromJson(json, new TypeToken<ArrayList<Review>>(){
                }.getType());
                responseCallback.success(reviews);
            }


            public void error(int status) {
                responseCallback.error(status);

            }
        });

    }

//K04 - Samme som K02




    //K05
    public void getLectures(Integer courseId, final ResponseCallback<ArrayList<Lecture>> responseCallback) {
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/lecture/" + courseId);
        this.connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {
                ArrayList<Lecture> lectures = gson.fromJson(json, new TypeToken<ArrayList<Lecture>>() {

                }.getType());
                responseCallback.success(lectures);
            }

            public void error(int status) {
            responseCallback.error(status);
            }
        });

    }

//K06
        public void getCourses(Integer id, final ResponseCallback<ArrayList<Course>> responseCallback) {
            HttpGet getRequest = new HttpGet(Connection.serverURL + "/course/" + id);
            this.connection.execute(getRequest, new ResponseParser() {
                //2 metoder der kan kaldes efter om der sker fejl eller ej.
            public void payload(String json) {
                //typetoken anvendes fordi det ellers ikke forstår at den skal bruge Lecture klassen
                ArrayList<Course> courses = gson.fromJson(json, new TypeToken<ArrayList<Course>>() {
                }.getType());
                responseCallback.success(courses);
            }

            public void error(int status) {
                responseCallback.error(status);
            }
        });
    }

//K07
    public void deleteReview(Review review , final ResponseCallback<Review> responseCallback) {

        try {

            HttpPut putRequest = new HttpPut(Connection.serverURL + "/student/review/");
            putRequest.addHeader("Content-Type", "application/json");
            StringEntity jsonReview = new StringEntity(gson.toJson(review));
            putRequest.setEntity(jsonReview);

            this.connection.execute(putRequest, new ResponseParser() {
                public void payload(String json) {
                    Review deleteReview = gson.fromJson(json, Review.class);
                    responseCallback.success(deleteReview);
                }

                public void error(int status) {

                    responseCallback.error(status);
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //K08 - Kopieres fra K07

    }





package sdk.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.HttpDelete;
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

/*
 - Update book
 - Delete book
 - Create book
 - Get all
*/

public class BookService {

    //Vi skal have fat i vores connection object
    private Connection connection;
    private Gson gson;

    public BookService() {
        this.connection = new Connection();
        this.gson = new Gson();
    }

    /*
    public void update(String id, Book book, final ResponseCallback<Book> responseCallback) {
        try {
            HttpPut putRequest = new HttpPut(Connection.serverURL + "/books/" + id);
            putRequest.addHeader("Content-Type", "application/json");
            putRequest.addHeader("authorization", "81dc9bdb52d04dc20036dbd8313ed055");

            StringEntity jsonBook = new StringEntity(gson.toJson(book));
            putRequest.setEntity(jsonBook);

            connection.execute(putRequest, new ResponseParser() {

                public void payload(String json) {
                    Book updatedBook = gson.fromJson(json, Book.class);
                    responseCallback.success(updatedBook);
                }

                public void error(int status) {
                    responseCallback.error(status);
                }
            });

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
*/


/*
K01 :​ Klient skal kunne logge ind og ud                                        (login) @POST
K02 ​: Klient skal kunne skrive en kommentar til en lektioner                   (addReview) @POST
K03 ​: Klient skal kunne se andres kommentarer og bedømmelser                   (getReviews) @GET
K04 ​: Klient skal kunne bedømme lektioner (Rating og evt. kommentere)          Er det addReview?
K05 ​: Klient skal kunne modtage lektioner fra serveren                         (getLectures) @GET
K06 ​: Klient skal kunne se samlet deltagelse af lektion                        (getCourses) @GET
K07 :​ Klient skal kunne slette egen kommentar                                  (deleteReview) @DELETE
K08 ​: Klient (admin-bruger) skal kunne slette kommentarer                      Er det deleteReview?

@GET
getLectures K05
getCourses  K06
getReviews  K03

@POST
login       K01
addReview   K02

@DELETE
deleteReview  K07


--- HUSK AT TJEKKE API FOR ALLE ---
 */

//K01
    public void login(User user, final ResponseCallback<User> responseCallback) {
        try {

            HttpPost postRequest = new HttpPost(Connection.serverURL + "/login");
            System.out.println(Connection.serverURL + "/login");
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
    public void addReview(final ResponseCallback<ArrayList<Review>> responseCallback) {
            HttpPost addReview = new HttpPost(Connection.serverURL + "/review");
            postRequest.addHeader("Content-Type", "application/json");
            StringEntity jsonReview = new StringEntity(gson.toJson(addReview));
            postRequest.setEntity(jsonReview);

        this.connection.execute(postRequest, new ResponseParser() {
            public void payload(String json) {
                Review newReview = gson.fromJson(json, Review.class);
                responseCallback.success(newReview);
            }

            public void error(int status) {

                responseCallback.error(status);
            }
        });
    }

//K03
    public void getReviews(final ResponseCallback<ArrayList<Review>> responseCallback) {
        HttpGet getRequest = new HttpGet(Connection.serverURL + "//review/{lectureId}");
        this.connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {
                ArrayList<Review> reviews = gson.fromJson(json, new TypeToken<ArrayList<Lecture>>(){
                }.getType());
                responseCallback.success(reviews);
            }


            public void error(int status) {
                responseCallback.error(status);

            }
        });

    }

//K04 PAS

    //K05
    public void getLectures(final ResponseCallback<ArrayList<Lecture>> responseCallback) {
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/lecture/{code}");
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
        public void getCourses(final ResponseCallback<ArrayList<Course>> responseCallback) {
            HttpGet getRequest = new HttpGet(Connection.serverURL + "/course/{userId}");
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



}


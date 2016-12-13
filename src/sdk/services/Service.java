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

/**
 * Some of the code below is repeated in different methods. The code will only be commented once
 */


public class Service {

    //Connection objektet skal bruges i Service klassen.
    private Connection connection;
    private Gson gson;

    public Service() {
        this.connection = new Connection();
        this.gson = new Gson();
    }


    /**
     * @param user
     * @param responseCallback Login method is using a Http post request to check whether a given user exist in the database
     */
    public void login(User user, final ResponseCallback<User> responseCallback) {
        try {

            HttpPost postRequest = new HttpPost(Connection.serverURL + "/login");
            /**
             * The application is using json as content type
             */
            postRequest.addHeader("Content-Type", "application/json");
/**
 * The JSON string is using a StringEntity to be sent.
 */

            StringEntity jsonUser = new StringEntity(gson.toJson(user));
            postRequest.setEntity(jsonUser);

            //Two statements - success or failure (error).
            /**
             * Two statements - success or failure (error).
             * Payload is the success. it will grant access by converting to gson from json
             * error is when something is wrong, and will not return anything.
             */
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

    /**
     * @param review
     * @param responseCallback The add review method is used to add a review to the database table "review".
     *                         This is done with a Http Post request. It is requesting the origin server to accept the entity below.
     */
    public void addReview(Review review, final ResponseCallback<Review> responseCallback) {

        try {

            /**
             * The post request need an server URL (collected from the Connection class, and an API given below, but
             * defined on the server
             */
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


    /**
     * @param lectureId
     * @param responseCallback The get reviews method is using a Http get request to collect a review, in an arraylist,
     *                         from a given lecture id.
     */
    public void getReviews(Integer lectureId, final ResponseCallback<ArrayList<Review>> responseCallback) {
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/review/" + lectureId);
        this.connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {
                /**
                 * Typetoken is used to make the method using the Review class
                 */
                ArrayList<Review> reviews = gson.fromJson(json, new TypeToken<ArrayList<Review>>() {
                }.getType());
                responseCallback.success(reviews);
            }


            public void error(int status) {
                responseCallback.error(status);

            }
        });

    }

    /**
     * @param courseId
     * @param responseCallback This method is used to collect a given lecture from an arraylist. To do so, I am using the Http Get request
     *                         and set the server URL and a specified API from the server. Moreover the client need to add the course id
     *                         to see the review.
     */
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

    /**
     * @param id
     * @param responseCallback This is the method to receive attending course for a user defined User ID. This is done with a
     *                         Http get request and an arraylist with the data listed for courses.
     */
    public void getCourses(Integer id, final ResponseCallback<ArrayList<Course>> responseCallback) {
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/course/" + id);
        this.connection.execute(getRequest, new ResponseParser() {

            public void payload(String json) {

                ArrayList<Course> courses = gson.fromJson(json, new TypeToken<ArrayList<Course>>() {
                }.getType());
                responseCallback.success(courses);
            }

            public void error(int status) {
                responseCallback.error(status);
            }
        });
    }

    /**
     * @param review
     * @param responseCallback Delete review is soft deleting a review that the user specifies. This is done with a Http put request, as this can
     *                         be used to manipulate with data in the database. The server is created to do so by changing a value from 0 to 1
     *                         with a boolean variable.
     */
    public void deleteReview(Review review, final ResponseCallback<Review> responseCallback) {

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

}





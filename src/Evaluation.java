import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.HttpGet;
import sdk.connection.Connection;
import sdk.connection.ResponseCallback;
import sdk.connection.ResponseParser;
import sdk.models.Course;
import sdk.models.Lecture;
import sdk.models.Review;
import sdk.models.User;
import sdk.services.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Daniel on 14-11-2016.
 */
public class Evaluation {

    Scanner input;
    private Service service;

    public Evaluation(Service service) {

        input = new Scanner(System.in);
        this.service = service;

    }

    public static void main(String[] args) {

        Service service = new Service();


        /* ----------TEST AF ADD REVIEW--------- VIRKER

        Review review = new Review();
        review.setLectureId(10);
        review.setUserId(2);

        service.addReview(review, new ResponseCallback<Review>() {
            public void success(Review data) {
                System.out.println("review add");
            }

            public void error(int status) {
                System.out.print(status);
            }
        });
*/
        /*-----------TEST AF GET REVIEWS--------------- works

        service.getReviews(2, new ResponseCallback<ArrayList<Review>>() {
            public void success(ArrayList<Review> data) {
                for (Review reviews: data) {
                    System.out.println(reviews.getLectureId());

                }
            }

            public void error(int status) {

            }
        });
*/

 /*----------- TEST AF getLectures ------------ VIRKER

        service.getLectures(5, new ResponseCallback<ArrayList<Lecture>>() {
            public void success(ArrayList<Lecture> data) {
                for (Lecture lectures: data){
                    System.out.println(lectures.getStartDate());
                }
            }

            public void error(int status) {

            }
        });
*/



/*------------TEST AF getCourses ----------- WORKS


        service.getCourses(2, new ResponseCallback<ArrayList<Course>>() {
            public void success(ArrayList<Course> data) {

                for (Course courses: data){
                    System.out.println(courses.getCode());
                }
            }

            public void error(int status) {

            }
        });

*/



//---------------TEST AF DELETE REVIEWS-------------- Virker ikke...
        Review review = new Review();
        review.setId(1);
        review.setUserId(2);

        service.deleteReview(review, new ResponseCallback<Review>() {
            public void success(Review data) {
                System.out.print("DELETED");
            }

            public void error(int status) {
                System.out.print(status);
            }
        });

    }

/*
        public void addReview() {

        Review reviewToCreate = new Review();

        System.out.println("Indtast ID");
        int id = input.nextInt();
        reviewToCreate.setId(id);

        System.out.println("Indtast userID");
        int userId = input.nextInt();
        reviewToCreate.setUserId(userId);

        System.out.println("Indtast lectureID");
        int lectureId = input.nextInt();
        reviewToCreate.setLectureId(lectureId);

        System.out.println("Indtast rating");
        int rating = input.nextInt();
        reviewToCreate.setRating(rating);

        System.out.println("Indtast comment");
        String comment = input.nextLine();
        reviewToCreate.setComment(comment);

        service.addReview(reviewToCreate, new ResponseCallback<Review>() {
            public void success(Review data) {
                System.out.println("Review created");
            }

            public void error(int status) {
                System.out.println(status);
            }
        });

/*
        public void getReviews() {

        Review reviewToGet = new Review();

        System.out.println("Indtast ID på ønsket review");
        int id = input.nextInt();
        reviewToGet.getId();

        service.getReviews(reviewToGet, new ResponseCallback<ArrayList<Review>>() {
            public void success(Review data) {
                System.out.println(Review);
            }

            public void error(int status) {
                System.out.println(status);
            }
        });
    }
*/

/*
        public void deleteReview() {
            Review reviewToDelete = new Review();

        System.out.println("ID på review der skal slettes");
        int id = input.nextInt();
        reviewToDelete.setId(id);

            service.deleteReview(reviewToDelete, new ResponseCallback<Review>() {
                public void success(Review data) {
                    System.out.println("Reviewet er nu slettet");
                }

                public void error(int status) {
                    System.out.println("status");
                }
            });

    }
*/



        /*LOGIN***
        User user = new User();
        BookService bookServices = new BookService();



        String username = "student@cbs.dk";
        user.setCbsMail(username);


        String password = "cbs";

        String doubleHashed = Digester.hashWithSalt(Digester.hashWithSalt(password));
        user.setPassword(doubleHashed);

        System.out.println(doubleHashed);

        System.out.println("69015ed720025673825c03b1c1634f46");


        bookServices.login(user, new ResponseCallback<User>() {
            public void success(User data) {
                System.out.println(data.getId());
            }

            public void error(int status) {

            }
        });
*/
    }









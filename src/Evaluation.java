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

    public Evaluation() {

        input = new Scanner(System.in);

    }

    public static void main(String[] args) {

        Evaluation evaluation = new Evaluation();
        evaluation.deleteReview();









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



/*---------------TEST AF DELETE REVIEWS-------------- WORKS
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

*/
    }



public void login() {
    Service service = new Service();

    User user = new User();

    System.out.println("Username");
    String cbsMail = input.next();
    user.setCbsMail(cbsMail);

    System.out.println("Password");
    String password = input.next();
    String doubleHashed = Digester.hashWithSalt(Digester.hashWithSalt(password));
    user.setPassword(doubleHashed);

    service.login(user, new ResponseCallback<User>() {
        public void success(User data) {
            System.out.println(data.getId());
        }

        public void error(int status) {
            System.out.println(status);
        }
    });

}



    public void addReview() {
        Service service = new Service();

        Review reviewToCreate = new Review();

        System.out.println("ID");
        int id = input.nextInt();
        reviewToCreate.setId(id);

        System.out.println("User ID");
        int userId = input.nextInt();
        reviewToCreate.setUserId(userId);

        System.out.println("Lecture ID");
        int lectureId = input.nextInt();
        reviewToCreate.setLectureId(lectureId);

        System.out.println("Rate from 1 to 5");
        int rating = input.nextInt();
        reviewToCreate.setRating(rating);

        System.out.println("Comment on the review");
        String comment = input.next();
        reviewToCreate.setComment(comment);

        service.addReview(reviewToCreate, new ResponseCallback<Review>() {
            public void success(Review data) {
                System.out.println("Review created");
            }

            public void error(int status) {
                System.out.println(status);
            }
        });
    }


    public void getReviews() {

        Service service = new Service();
        Review reviewToGet = new Review();

        System.out.println("Lecture ID:");
        int lectureId = input.nextInt();
        reviewToGet.getLectureId();

        service.getReviews(lectureId, new ResponseCallback<ArrayList<Review>>() {
            public void success(ArrayList<Review> data) {
                for (Review review : data) {
                    System.out.println(review.getComment());
                    System.out.println(review.getRating());
                }
            }

            public void error(int status) {
                System.out.println(status);
            }

        });
    }


    public void getLectures() {

        Service service = new Service();
        Lecture lectureToGet = new Lecture();

        System.out.println("Course ID:");
        int courseId = input.nextInt();
        lectureToGet.getCourseId();

        service.getLectures(courseId, new ResponseCallback<ArrayList<Lecture>>() {
            public void success(ArrayList<Lecture> data) {
                for (Lecture lecture : data) {
                    System.out.println(lecture.getStartDate());
                    System.out.println(lecture.getStart());
                    System.out.println(lecture.getDescription());
                    System.out.println(lecture.getLocation());
                }
            }

            public void error(int status) {
                System.out.println(status);
            }
        });
    }

    public void getCourses() {

        Service service = new Service();
        Course courseToGet = new Course();

        System.out.println("User ID:");
        int userId = input.nextInt();
        courseToGet.getId();

        service.getCourses(userId, new ResponseCallback<ArrayList<Course>>() {
            public void success(ArrayList<Course> data) {
                for (Course course : data) {
                    System.out.println(course.getDisplaytext());

                }
            }

            public void error(int status) {
                System.out.println(status);
            }
        });
    }


    public void deleteReview() {
        Service service = new Service();
        Review review = new Review();

        System.out.println("Type ID");
        int id = input.nextInt();
        review.setId(id);

        System.out.println("Type User ID");
        int userId = input.nextInt();
        review.setUserId(userId);

        service.deleteReview(review, new ResponseCallback<Review>() {
            public void success(Review data) {
                System.out.println("Review deleted");
            }

            public void error(int status) {
                System.out.println(status);
            }
        });

    }

    }















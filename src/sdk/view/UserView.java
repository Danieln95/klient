package sdk.view;

import sdk.connection.ResponseCallback;
import sdk.models.Course;
import sdk.models.Lecture;
import sdk.models.Review;
import sdk.models.User;
import sdk.security.Digester;
import sdk.services.Service;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by kknhd on 10-12-2016.
 */


public class UserView {

    private Service service;
    Scanner input;
    private User user;

    public UserView(Service service, User user) {
        this.user = user;
        input = new Scanner(System.in);
        this.service = service;
        this.user = user;

    }


    public void userMenu() {


        System.out.println("Welcome to UserMenu");
        System.out.println("(1) -  Get lectures");
        System.out.println("(2) -  Get courses");
        System.out.println("(3) -  Get review");
        System.out.println("(4) -  Add Review (rating)");
        System.out.println("(5) -  Delete review");
        System.out.println("(6) -  Log out");


        int choice = input.nextInt();
        switch (choice) {

            case 1:
                Lecture lectureToGet = new Lecture();

                System.out.println("Course ID:");
                int courseId = input.nextInt();
                lectureToGet.getCourseId();

                service.getLectures(courseId, new ResponseCallback<ArrayList<Lecture>>() {
                    public void success(ArrayList<Lecture> data) {
                        for (Lecture lecture : data) {
                            System.out.println("Starttidspunkt: " + lecture.getStartDate());
                            System.out.println("Beskrivelse: " + lecture.getDescription());
                            System.out.println();
                            userMenu();
                        }
                    }

                    public void error(int status) {

                    }
                });


                break;

            case 2:

                Course courseToGet = new Course();

                System.out.println("User ID:");
                int userId = input.nextInt();
                courseToGet.getId();

                service.getCourses(userId, new ResponseCallback<ArrayList<Course>>() {
                    public void success(ArrayList<Course> data) {
                        for (Course course : data) {
                            System.out.println("You are attending: ");
                            System.out.println("The Course is: " + course.getCode());
                            System.out.println("Course ID: " + course.getDisplaytext());
                            System.out.println();
                            userMenu();
                        }
                    }

                    public void error(int status) {

                    }
                });


                break;

            case 3:

                Review reviewToGet = new Review();

                System.out.println("Lecture ID:");
                int lectureId = input.nextInt();
                reviewToGet.getLectureId();

                service.getReviews(lectureId, new ResponseCallback<ArrayList<Review>>() {
                    public void success(ArrayList<Review> data) {
                        for (Review review : data) {
                            System.out.println("Comment: " + review.getComment());
                            System.out.println("Rating: " + review.getRating());
                            System.out.println();
                            userMenu();
                        }
                    }

                    public void error(int status) {

                    }

                });

                break;

            case 4:

                Review reviewToCreate = new Review();

                reviewToCreate.setUserId(user.getId());

                System.out.println("Lecture ID: ");
                lectureId = input.nextInt();
                reviewToCreate.setLectureId(lectureId);

                System.out.println("Rate from 1 to 5: ");
                int rating = input.nextInt();
                reviewToCreate.setRating(rating);

                System.out.println("Comment on the review: ");
                input.nextLine();
                String comment = input.nextLine();
                reviewToCreate.setComment(comment);

                service.addReview(reviewToCreate, new ResponseCallback<Review>() {
                    public void success(Review data) {
                        System.out.println("Thank you for reviewing");
                        System.out.println();
                        userMenu();
                    }

                    public void error(int status) {
                    }
                });

                break;

            case 5:

                Review review = new Review();


                System.out.println("Type Review ID");
                int id = input.nextInt();
                review.setId(id);

                review.setUserId(user.getId());


                service.deleteReview(review, new ResponseCallback<Review>() {
                    public void success(Review data) {
                        System.out.println("Review deleted");
                        System.out.println();
                        userMenu();
                    }

                    public void error(int status) {

                    }
                });


                break;

            case 6:
                System.out.println();
                View view = new View(service);
                view.mainMenu();
                System.out.println();

                break;

            default:

                break;

        }
    }
}


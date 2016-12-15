package sdk.view;

import sdk.connection.ResponseCallback;
import sdk.models.Course;
import sdk.models.Lecture;
import sdk.models.Review;
import sdk.models.User;
import sdk.services.Service;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Daniel on 10-12-2016.
 */


public class UserView {

    Scanner input;
    private Service service;
    private User user;

    public UserView(Service service, User user) {
        this.user = user;
        input = new Scanner(System.in);
        this.service = service;
        this.user = user;

    }

    /**
     * The user menu is the only menu in the application. From here the user can select several methods that
     * the application can run. This is done with a switch case, that reads input from the user through the
     * scanner import
     */

    public void userMenu() {


        System.out.println("Welcome to the user menu. You have following choices: \n");
        System.out.println("(1) -  Get lectures");
        System.out.println("(2) -  Get your courses");
        System.out.println("(3) -  Get your review");
        System.out.println("(4) -  Add review to lecture");
        System.out.println("(5) -  Delete your review");
        System.out.println("(6) -  Log out");

            /**
            * Integer variable used to read the user input from the imported scanner.
             */
        int choice = input.nextInt();

        switch (choice) {

            /**
             * First case is get lectures. The user is told to put in the course id, the scanner then reads the input
             * and checks if the input exist in the database. Either it is a success, or failure (error).
             * Success will then list the lectures start time and the description of the lecture.  It will then return
             * to the userMenu.
             *
             */
            case 1:
                //Opretter en instans som skal bruges senere
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

            /**
             * The second case is get courses. It reads the users id and match it with the data in the MySQL database.
             * if there's a match, the application is printing a list with the name of the course and course id that
             * the given user is attending. Then returning to user menu.
             */

            case 2:


                service.getCourses(user.getId(), new ResponseCallback<ArrayList<Course>>() {
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
            /**
             * Third case is the get review method. From here the user will enter a lecture id. The scanner will then read the input
            * and check for success or error in the database. If there's a match it will print comment and rating on the given
            * lecture id. Then returning to user menu.
            */
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
            /**
            * The fourth case is create review. The user has the opportunity to create a review. First the users id is set
            * automatically. Then the user is told to give several inputs that the imported scanner reads. If the review can
             * be created, the success method will run. From here the user will be aware of the fact that he or shes review is
             * created.
            *
            */
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

            /**
             * The fifth case is used when a user want to delete a review. The user input a review id that it want to
             * delete. If this review ID is made by the same user id as the given user has, the success method will run.
             * It display a message for the user, making aware that the review is deleted. Then returns to user menu.
             */

            case 5:

                Review reviewToDelete = new Review();


                System.out.println("Type Review ID");
                int id = input.nextInt();
                reviewToDelete.setId(id);

                reviewToDelete.setUserId(user.getId());


                service.deleteReview(reviewToDelete, new ResponseCallback<Review>() {
                    public void success(Review data) {
                        System.out.println("Review deleted");
                        System.out.println();
                        userMenu();
                    }

                    public void error(int status) {

                    }
                });

                break;

            /**
             * The sixth case is simply a simulation of "logout", as it return to main menu without anything else. And therefore
            * returns to a switch case with 2 cases. log in or exit.
            */
            case 6:
                System.out.println();
                View view = new View(service);
                view.mainMenu();
                System.out.println();

                break;


        }
    }
}


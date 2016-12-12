import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.HttpGet;
import sdk.connection.Connection;
import sdk.connection.ResponseCallback;
import sdk.connection.ResponseParser;
import sdk.models.Lecture;
import sdk.models.Review;
import sdk.models.User;
import sdk.services.Service;
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
        addReview();

    }

    public static void main(String[] args) {

        Service service = new Service();

        Review review = new Review();
        review.setLectureId(5);
        review.setUserId(2);

        service.addReview(review, new ResponseCallback<Review>() {
            public void success(Review data) {
                System.out.println("review add");
            }

            public void error(int status) {
                System.out.print(status);
            }
        });

    }


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
}





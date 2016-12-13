package sdk.view;

import sdk.connection.ResponseCallback;
import sdk.models.Review;
import sdk.services.Service;

import java.util.Scanner;

/**
 * Created by kknhd on 10-12-2016.
 */
public class userView {
    Scanner input;

    public userView(){
        input = new Scanner(System.in);
    }
    /*
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

        System.out.println("Rate from 1-5");
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
    */

}

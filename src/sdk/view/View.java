package sdk.view;


import sdk.connection.ResponseCallback;
import sdk.models.User;
import sdk.security.Digester;
import sdk.services.Service;
import sdk.models.Review;
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

import java.util.Scanner;



/**
 * Created by Daniel on 23-11-2016.
 */
public class View {
    Scanner input;
    private Service service;

    public View(Service service) {
        this.service = service;
    }

    public void mainMenu() {

        System.out.println("Welcome to Evaluation");
        System.out.println("(1) -  Login");
        System.out.println("(2) -  Exit");

        input = new Scanner(System.in);
        int choice = input.nextInt();

        switch (choice) {

            case 1:
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
                        UserView userview = new UserView(service, data);

                        userview.userMenu();

                    }

                    public void error(int status) {
                        System.out.println(status);
                    }
                });
                break;

            case 2:
                System.out.println("Have a nice day");
                System.exit(0);

                break;

            default:

                break;
        }


    }

}





package sdk.view;


import sdk.connection.ResponseCallback;
import sdk.models.User;
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
        input = new Scanner(System.in);
        this.service = service;

        login();
    }

    public static void main(String[] args) {
        Service service = new Service();
        View view = new View(service);
        view.login();
    }

    public void login() {

        Service service = new Service();
        User user = new User();

        System.out.println("Velkommen til undervisningsevaluering");
        System.out.println("Indtast venligst brugernavn og adgangskode");


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

}
/*
    private void createBook() {

        System.out.println("Create book is running");

        Book bookToCreate = new Book();


        System.out.println("Indtast bogens ISBN");
        Long isbn = input.nextLong();
        bookToCreate.setIsbn(isbn);


        System.out.println("Indtast bogens titel");
        String title = input.next();
        bookToCreate.setTitle(title);

        System.out.println("Indtast bogens version");
        String edition = input.next();
        bookToCreate.setEdition(edition);

        System.out.println("Indtast bogens forfatter eller forfattere");
        String author = input.next();
        bookToCreate.setAuthor(author);

        bookServices.create(bookToCreate, new ResponseCallback<Book>() {

            public void success(Book data) {
                System.out.println("Book created");
            }


            public void error(int status) {
                System.out.println(status);
            }
        });
*/




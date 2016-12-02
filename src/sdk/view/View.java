package sdk.view;


import sdk.connection.ResponseCallback;
import sdk.models.Book;
import sdk.models.User;
import sdk.services.BookService;

import java.util.Scanner;



/**
 * Created by Daniel on 23-11-2016.
 */
public class View {
    Scanner input;
    private BookService bookServices;

    public View(BookService bookServices) {
        input = new Scanner(System.in);
        this.bookServices = bookServices;

        login();
    }

    private void login() {


        System.out.println("===Velkommen til Brugtbogbørsen===");
        System.out.println("Indtast venligst brugernavn og adgangskode");


        User user = new User();


        System.out.println("\nBrugernavn: ");
        String username = input.next();

        user.setUsername(username);
        System.out.println("\nPassword: ");
        String password = input.next();
        user.setPassword(password);


        bookServices.login(user, new ResponseCallback<User>() {
            public void success(User data) {
                System.out.println(data.getUsername());
            }

            public void error(int status) {

            }
        });


}

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


    }


}

package sdk.view;


import sdk.connection.ResponseCallback;
import sdk.models.User;
import sdk.services.BookService;

import java.util.Scanner;



/**
 * Created by Daniel on 23-11-2016.
 */
public class View {
    Scanner input;
    private BookService service;

    public View(BookService service) {
        input = new Scanner(System.in);
        this.service = service;

        login();
    }

    public static void main(String[] args) {
        BookService service = new BookService();
        View view = new View(service);
        view.login();
    }

    private void login() {


        System.out.println("Velkommen til undervisningsevaluering");
        System.out.println("Indtast venligst brugernavn og adgangskode");


        User user = new User();


        System.out.println("\nBrugernavn: ");
        String username = input.next();

        user.setCbsMail(username);
        System.out.println("\nPassword: ");
        String password = input.next();
        user.setPassword(password);


        service.login(user, new ResponseCallback<User>() {
            public void success(User data) {
                System.out.print("yes");
                System.out.println(data.getCbsMail());
            }

            public void error(int status) {
                System.out.printf("status");

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



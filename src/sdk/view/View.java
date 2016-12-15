package sdk.view;


import sdk.connection.ResponseCallback;
import sdk.models.User;
import sdk.security.Digester;
import sdk.services.Service;
import java.util.Scanner;




/**
 * Created by Daniel on 23-11-2016.
 */
public class View {
    Scanner input;
    private Service service;

    /**
     *
     * @param service
     * View constructor, taking Service as parameter.
     */

    public View(Service service) {
        this.service = service;
    }

    /**
     * This is the main menu. The user has 2 opportunities. Either login, or exit.
     * This is registred with the help of an imported scanner and a switch case, checking for inputs.
     */

    public void mainMenu() {

        System.out.println("Welcome to Evaluation");
        System.out.println("(1) -  To login");
        System.out.println("(2) -  To exit");

        input = new Scanner(System.in);
        int choice = input.nextInt();

        switch (choice) {
/**
 * The first case is login. First the user input the username, which from the server is programmed into being the cbs
 * mail. The password is then provided by the user. But as a security measurement this will then have to be double
 * hashed with salt. After this, the application will check for a match with the given cbs mail.
 *
 * If these are a match, the success method will run, letting the user go to user menu in the UserView class.
 * If these are mismatching, it will return an error code.
 */
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
                        System.out.println("Error code: " + status);
                    }
                });
                break;
/**
 * The second case is exit. This will print a message and call for system.exit, and therefore stopping the application.
 */
            case 2:
                System.out.println("Have a nice day");
                System.exit(0);

                break;

        }


    }

}





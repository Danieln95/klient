import sdk.services.Service;
import sdk.view.View;

/**
 * Created by Daniel on 13-12-2016.
 */
public class Main {
    /**
     *
     * @param args
     * The main class, is running the application. This first initialize the Service class, then View class.
     * Last of all it run the mainMenu in the View class.
     */
    public static void main(String[] args) {
        Service service = new Service();

       View view = new View(service);
        view.mainMenu();

    }
}

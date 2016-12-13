import sdk.services.Service;
import sdk.view.View;

/**
 * Created by kknhd on 13-12-2016.
 */
public class Main {

    public static void main(String[] args) {
        Service service = new Service();

       View view = new View(service);
        view.mainMenu();

    }
}

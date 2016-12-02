import sdk.connection.ResponseCallback;
import sdk.models.User;
import sdk.services.BookService;

/**
 * Created by Daniel on 14-11-2016.
 */
public class BookStore {
  /*  private static CookieStore httpCookie = new BasicCookieStore();
    private SSLConnectionSocketFactory ssl;
*/

    public static void main(String[] args) throws Exception{

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

    }
/*
    public void setSSL() {

        try {
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            this.ssl = new SSLConnectionSocketFactory(
                    builder.build(), NoopHostnameVerifier.INSTANCE);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
*/
    /*
    public static void main(String[] args) {

        BookService bookService = new BookService();

        Book bookToUpdate = new Book();
        bookToUpdate.setSubtitle("undertitel");
        bookService.update("5829af983f5250a6bd2d25e4", bookToUpdate, new ResponseCallback<Book>() {

            public void success(Book data) {
                int i = 1;
                System.out.println("Success");
            }

            public void error(int status) {
                int i = 2;
            }
        });
    }
    */
}


import org.apache.http.client.CookieStore;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import sdk.connection.ResponseCallback;
import sdk.models.Book;
import sdk.models.User;
import sdk.services.BookService;


import java.util.ArrayList;

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



        String username = "test";
        user.setUsername(username);

        String password = "1234";
        user.setPassword(password);


        bookServices.login(user, new ResponseCallback<User>() {
            public void success(User data) {
                System.out.println(data.getUsername());
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


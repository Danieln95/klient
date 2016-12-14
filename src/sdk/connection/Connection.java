package sdk.connection;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Connection {
    /**
     * First of all I need to declare the serverURL and that I am using it as a http client. To establish a connection
     * to the server.
     */
    public static String serverURL = "http://localhost:5000/api";
    private CloseableHttpClient httpClient;

    public Connection() {
        this.httpClient = HttpClients.createDefault();
    }

    /**
     * @param uriRequest
     * @param parser     This execute method is checking for the status code, if this code is within 200 to 300, it may continue. if not,
     *                   it will return the error status code. Codes within 200 and 300 is the success codes.
     *                   <p>
     *                   Also adding responseparser as extra argument. It has 2 arguments in an if else loop.
     */
    public void execute(HttpUriRequest uriRequest, final ResponseParser parser) {

        // Create a custom response handler
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            public String handleResponse(final HttpResponse response) throws IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    parser.error(status); //det ene argument fra responsepasser
                }
                return null;
            }

        };

        try {
            //Returning JSON to client
            String json = this.httpClient.execute(uriRequest, responseHandler);
            parser.payload(json);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

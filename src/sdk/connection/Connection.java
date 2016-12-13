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

  public static String serverURL = "http://localhost:5000/api";
  private CloseableHttpClient httpClient;

  public Connection(){
    this.httpClient = HttpClients.createDefault();
  }

//Vi tilføjer responseparser som et ekstra argument. På den lever 2 argumenter
  public void execute(HttpUriRequest uriRequest, final ResponseParser parser){

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

package sdk.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import sdk.connection.Connection;
import sdk.connection.ResponseCallback;
import sdk.connection.ResponseParser;
import sdk.models.Book;
import sdk.models.Delete;
import sdk.models.User;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/*
 - Update book
 - Delete book
 - Create book
 - Get all
*/

public class BookService {

    //Vi skal have fat i vores connection object
    private Connection connection;
    private Gson gson;

    public BookService() {
        this.connection = new Connection();
        this.gson = new Gson();
    }

    public void update(String id, Book book, final ResponseCallback<Book> responseCallback) {
        try {
            HttpPut putRequest = new HttpPut(Connection.serverURL + "/books/" + id);
            putRequest.addHeader("Content-Type", "application/json");
            putRequest.addHeader("authorization", "81dc9bdb52d04dc20036dbd8313ed055");

            StringEntity jsonBook = new StringEntity(gson.toJson(book));
            putRequest.setEntity(jsonBook);

            connection.execute(putRequest, new ResponseParser() {

                public void payload(String json) {
                    Book updatedBook = gson.fromJson(json, Book.class);
                    responseCallback.success(updatedBook);
                }

                public void error(int status) {
                    responseCallback.error(status);
                }
            });

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


        //Slettet bogen ud fra id.
    public void delete(String id, final ResponseCallback<Integer> responseCallback) {

        HttpDelete deleteRequest = new HttpDelete(Connection.serverURL + "/books/" + id);
        deleteRequest.addHeader("Content-Type", "application/json");
        deleteRequest.addHeader("authorization", "NTxX4aHJ974xlJY6N3xFJXBB1gG7w8G8u8C20IFwp5Qvd4v1kHWf9PjBb1bc5Ei8");
        connection.execute(deleteRequest, new ResponseParser() {
            public void payload(String json) {

                Delete delete = gson.fromJson(json, Delete.class);
                responseCallback.success(delete.getCount());

            }

            public void error(int status) {
                responseCallback.error(status);
            }
        });
    }

    public void create(Book book, final ResponseCallback<Book> responseCallback) {
        try {
            HttpPost postRequest = new HttpPost(Connection.serverURL + "/books");
            postRequest.addHeader("Content-Type", "application/json");
            postRequest.addHeader("authorization", "NTxX4aHJ974xlJY6N3xFJXBB1gG7w8G8u8C20IFwp5Qvd4v1kHWf9PjBb1bc5Ei8");
            StringEntity jsonBook = new StringEntity(gson.toJson(book));
            postRequest.setEntity(jsonBook);

            this.connection.execute(postRequest, new ResponseParser() {
                public void payload(String json) {
                    Book newBook = gson.fromJson(json, Book.class);
                    responseCallback.success(newBook);
                }

                public void error(int status) {
                    responseCallback.error(status);
                }
            });

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void login(User user, final ResponseCallback<User> responseCallback) {
        try {
            HttpPost postRequest = new HttpPost(Connection.serverURL + "/login");
            postRequest.addHeader("Content-Type", "application/json");
            StringEntity jsonUser = new StringEntity(gson.toJson(user));
            postRequest.setEntity(jsonUser);

            this.connection.execute(postRequest, new ResponseParser() {
                public void payload(String json) {
                    User newUser = gson.fromJson(json, User.class);
                    responseCallback.success(newUser);
                }

                public void error(int status) {
                    responseCallback.error(status);
                }
            });

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }



    //Vi bruger void fordi lige så snart vi implementere det så er det ikke et getall vi implementere men koden
    public void getAll(final ResponseCallback<ArrayList<Book>> responseCallback) {

        HttpGet getRequest = new HttpGet(Connection.serverURL + "/books"); //Et get request til books, hvor vi sætter url til /books
        this.connection.execute(getRequest, new ResponseParser() {

            //2 metoder der kan kaldes efter om der sker fejl eller ej.
            public void payload(String json) {

                //typetoken anvendes fordi det ellers ikke forstår at den skal bruge book klassen
                ArrayList<Book> books = gson.fromJson(json, new TypeToken<ArrayList<Book>>() {
                }.getType());
                responseCallback.success(books);
            }

            public void error(int status) {
                responseCallback.error(status);

            }
        });

    }
}

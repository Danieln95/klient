package sdk.connection;

public interface ResponseCallback<T> {

//For at lave et generisk bruges T. Java generics hedder T.
// Brugeren kan selv definere hvad T skal være.
    void success(T data);
    void error(int status);
}

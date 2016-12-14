package sdk.connection;

public interface ResponseCallback<T> {

    /**
     * @param data This is the responsecallback interface, it has 2 options. success or error. This is used in the following
     *             coding of classes. the success is using "T" from java generics that let the user define what "T" is.
     */

    void success(T data);

    void error(int status);
}

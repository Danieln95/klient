package sdk.connection;

/**
 * Created by Daniel on 14-11-2016.
 */
public interface ResponseParser {
    /**
     * @param json This is the response parser interface. It has 2 options, payload and error. The payload use json as parametre
     *             and error will return a error status code.
     */
    void payload(String json);

    void error(int status);

}

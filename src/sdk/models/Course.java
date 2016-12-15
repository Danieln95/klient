package sdk.models;

/**
 * Created by Daniel on 10-12-2016.
 */

/**
 * The model classes all has variables and getters/setters for given variables.
 * These are matching the server DTOs.
 */
public class Course {

    private String id;
    private String code;
    private String displaytext;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplaytext() {
        return displaytext;
    }

    public void setDisplaytext(String displaytext) {
        this.displaytext = displaytext;
    }
}

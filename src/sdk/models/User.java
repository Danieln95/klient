package sdk.models;

/**
 * Created by kknhd on 02-12-2016.
 */


/**
 * The model classes all has variables and getters/setters for given variables.
 * These are matching the server DTOs.
 */

public class User {

    private int id;
    private String cbsMail, password, type;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCbsMail() {
        return cbsMail;
    }

    public void setCbsMail(String cbsMail) {
        this.cbsMail = cbsMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

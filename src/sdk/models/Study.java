package sdk.models;

/**
 * Created by kknhd on 02-12-2016.
 */


/**
 * The model classes all has variables and getters/setters for given variables.
 * These are matching the server DTOs.
 */
public class Study {

    private  int id;
    private  String name;
    private  String shortname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
}

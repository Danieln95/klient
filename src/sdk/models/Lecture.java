package sdk.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Daniel on 02-12-2016.
 */

/**
 * The model classes all has variables and getters/setters for given variables.
 * These are matching the server DTOs.
 */
public class Lecture {

    private int id;
    private int courseId;
    private String type;
    private String description;
    private List<String> start = new ArrayList<String>();
    private List<String> end = new ArrayList<String>();
    private Date startDate;
    private Date endDate;
    private String location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getStart() {
        return start;
    }

    public void setStart(List<String> start) {
        this.start = start;
    }

    public List<String> getEnd() {
        return end;
    }

    public void setEnd(List<String> end) {
        this.end = end;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

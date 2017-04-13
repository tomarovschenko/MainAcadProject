package mainAcadProject.entity;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

/**
 * Created by ) on 23.03.2017.
 */
public class CourseEntity implements Serializable{
    private IntegerProperty id;
    private StringProperty name;
    private DoubleProperty total_hours;
    private boolean remote=false;

    public CourseEntity() {
    }

    public boolean isRemote() {
        return remote;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getTotal_hours() {
        return total_hours.get();
    }

    public DoubleProperty total_hoursProperty() {
        return total_hours;
    }

    public void setTotal_hours(double total_hours) {
        this.total_hours.set(total_hours);
    }

    @Override
    public String toString(){
        return "Course: id: "+id+"; course_name: "+name+"; total hours: "+total_hours;
    }
}

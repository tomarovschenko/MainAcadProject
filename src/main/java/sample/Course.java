package sample;

import javafx.beans.property.*;

/**
 * Created by ) on 10.04.2017.
 */
public class Course {
    private boolean del=false;
    private IntegerProperty id;
    private StringProperty name;
    private DoubleProperty total_hours;

    public Course() {
    }

    public Course(int id, String name, double total_hours) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.total_hours = new SimpleDoubleProperty(total_hours);
    }

    public int getId() {
        return id.getValue();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.getValue();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getTotal_hours() {
        return total_hours.getValue();
    }

    public DoubleProperty total_hoursProperty() {
        return total_hours;
    }

    public void setTotal_hours(double total_hours) {
        this.total_hours.set(total_hours);
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    @Override
    public String toString(){
        return "Course: id: "+id+"; course_name: "+name+"; total hours: "+total_hours;
    }


}

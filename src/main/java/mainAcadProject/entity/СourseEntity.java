package mainAcadProject.entity;

import java.io.Serializable;

/**
 * Created by ) on 23.03.2017.
 */
public class СourseEntity implements Serializable{
    private int id;
    private String name;
    private double total_hours;

    public СourseEntity() {
    }

    public СourseEntity(int id, String name, double total_hours) {
        this.id = id;
        this.name = name;
        this.total_hours = total_hours;
    }

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

    public double getTotal_hours() {
        return total_hours;
    }

    public void setTotal_hours(double total_hours) {
        this.total_hours = total_hours;
    }

    @Override
    public String toString(){
        return "Course: id: "+id+"; course_name: "+name+"; total hours: "+total_hours;
    }
}

package mainAcadProject.entity;

import javafx.beans.property.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ) on 23.03.2017.
 */
public class CourseEntity implements Serializable{
    private int id;
    private String name;
    private Double total_hours;
    private boolean remote=false;
    private List <StudyGroupEntity> studyGroup;

    public CourseEntity() {
    }


    public boolean isRemote() {
        return remote;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
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

    public StringProperty nameProperty() {
        return new SimpleStringProperty(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotal_hours() {
        return total_hours;
    }

    public DoubleProperty total_hoursProperty(){
        return new SimpleDoubleProperty(total_hours);
    }

    public void setTotal_hours(Double total_hours) {
        this.total_hours = total_hours;
    }

    public List<StudyGroupEntity> getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(List<StudyGroupEntity> studyGroup) {
        this.studyGroup = studyGroup;
    }

    @Override
    public String toString(){
        return "Course: id: "+id+"; course_name: "+name+"; total hours: "+total_hours+"; "+isRemote();
    }
}

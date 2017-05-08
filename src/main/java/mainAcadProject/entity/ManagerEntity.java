package mainAcadProject.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ) on 05.04.2017.
 */
public class ManagerEntity implements Serializable {
    private int id;
    private String name;
    private String access;
    private boolean remote=false;
    private List<StudentEntity> students;

    public ManagerEntity() {
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

    public String getAccess() {
        return access;
    }

    public StringProperty accessProperty() {
        return new SimpleStringProperty(access);
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public List<StudentEntity> getStudent() {
        return students;
    }

    public void setStudent(List<StudentEntity> student) {
        this.students = student;
    }

    @Override
    public String toString(){
        return name;
    }
}

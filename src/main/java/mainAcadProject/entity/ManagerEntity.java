package mainAcadProject.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ) on 05.04.2017.
 */
public class ManagerEntity implements Serializable {
    private int id;
    private String name;
    private String access;
    private List<StudentEntity> students;

    public ManagerEntity() {
    }

    public ManagerEntity(int id, String name, String access) {
        this.id = id;
        this.name = name;
        this.access = access;
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

    public String getAccess() {
        return access;
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
        return "Manager: id: "+id+"; name: "+name+"; access: "+access;
    }
}

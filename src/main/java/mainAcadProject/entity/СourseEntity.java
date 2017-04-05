package mainAcadProject.entity;

import java.io.Serializable;

/**
 * Created by ) on 23.03.2017.
 */
public class СourseEntity implements Serializable{
    private int id;
    private String name;

    public СourseEntity() {
    }

    public СourseEntity(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString(){
        return "Course: [course_id: "+id+"; course_name: "+name+";]";
    }
}

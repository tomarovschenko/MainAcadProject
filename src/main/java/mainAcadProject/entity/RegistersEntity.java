package mainAcadProject.entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by ) on 07.04.2017.
 */
public class RegistersEntity implements Serializable {
    private int id;
    private LocalDate date;
    private double spent_hours;
    private StudyGroupEntity group;

    public RegistersEntity() {
    }

    public RegistersEntity(int id, LocalDate date, double spent_hours, StudyGroupEntity group) {
        this.id = id;
        this.date = date;
        this.spent_hours = spent_hours;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getSpent_hours() {
        return spent_hours;
    }

    public void setSpent_hours(double spent_hours) {
        this.spent_hours = spent_hours;
    }

    public StudyGroupEntity getGroup() {
        return group;
    }

    public void setGroup(StudyGroupEntity group) {
        this.group = group;
    }

    @Override
    public String toString(){
        return "Registers: id: "+id+"; date: "+date+"; spent hours: "+spent_hours+"; group: "+group.getName();
    }
}

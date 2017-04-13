package mainAcadProject.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by ) on 23.03.2017.
 */

public class StudyGroupEntity implements Serializable {
    private int id;
    private String  name;
    private CourseEntity course;
    private LocalDate plan_startDate;
    private LocalDate plan_endDate;
    private LocalDate fact_startDate;
    private LocalDate fact_endDate;
    private double total_hours;
    private double spent_hourse;
    private List<InstructorEntity> instructor;
    private List<RegistersEntity> registers;

    public StudyGroupEntity() {
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

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public LocalDate getPlan_startDate() {
        return plan_startDate;
    }

    public void setPlan_startDate(LocalDate plan_startDate) {
        this.plan_startDate = plan_startDate;
    }

    public LocalDate getPlan_endDate() {
        return plan_endDate;
    }

    public void setPlan_endDate(LocalDate plan_endDate) {
        this.plan_endDate = plan_endDate;
    }

    public LocalDate getFact_startDate() {
        return fact_startDate;
    }

    public void setFact_startDate(LocalDate fact_startDate) {
        this.fact_startDate = fact_startDate;
    }

    public LocalDate getFact_endDate() {
        return fact_endDate;
    }

    public void setFact_endDate(LocalDate fact_endDate) {
        this.fact_endDate = fact_endDate;
    }

    public List<InstructorEntity> getInstructor() {
        return instructor;
    }

    public void setInstructor(List<InstructorEntity> instructor) {
        this.instructor = instructor;
    }

    public double getTotal_hours() {
        return total_hours;
    }

    public void setTotal_hours(double total_hours) {
        this.total_hours = total_hours;
    }

    public double getSpent_hourse() {
        return spent_hourse;
    }

    public void setSpent_hourse(double spent_hourse) {
        this.spent_hourse = spent_hourse;
    }

    public List<RegistersEntity> getRegisters() {
        return registers;
    }

    public void setRegisters(List<RegistersEntity> registers) {
        this.registers = registers;
    }

    @Override
    public String toString(){
        return "Study group: id: "+id+"; name: "+name+"; course: "+course.getName()+"; total hourse: "+total_hours+
                "; spent hours: "+spent_hourse+"; plan start: "+plan_startDate+"; plan end: "+plan_endDate+
                "; fact start: "+fact_startDate+"; fact end: "+fact_endDate;
    }
}

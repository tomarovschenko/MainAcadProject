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
    private СourseEntity course;
    private LocalDate plan_startDate,
                        plan_endDate,
                        fact_startDate,
                        fact_endDate;
    private List<InstructorEntity> instructor;

    public StudyGroupEntity() {
    }

    public StudyGroupEntity(int id, String name, СourseEntity course, LocalDate plan_startDate, LocalDate plan_endDate,
                            LocalDate fact_startDate, LocalDate fact_endDate) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.plan_startDate = plan_startDate;
        this.plan_endDate = plan_endDate;
        this.fact_startDate = fact_startDate;
        this.fact_endDate = fact_endDate;
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

    public СourseEntity getCourse() {
        return course;
    }

    public void setCourse(СourseEntity course) {
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

    @Override
    public String toString(){
        return "Study group: id: "+id+"; name: "+name+"; course: "+course.getName()+"; plan start: "+plan_startDate+
                "; plan end: "+plan_endDate+"; fact start: "+fact_startDate+"; fact end: "+fact_endDate;
    }
}

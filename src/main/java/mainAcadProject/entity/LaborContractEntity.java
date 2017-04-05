package mainAcadProject.entity;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by ) on 23.03.2017.
 */
public class LaborContractEntity implements Serializable{
    private int id;
    private String number;
    private LocalDate date_start;
    private LocalDate date_end;
    private InstructorEntity instructor;

    public LaborContractEntity(){
    }

    public LaborContractEntity(int id, String number, LocalDate date_start, InstructorEntity instructor) {
        this.id = id;
        this.number = number;
        this.date_start = date_start;
        this.instructor = instructor;
    }

    public LaborContractEntity(int id, String number, LocalDate date_start, LocalDate date_end, InstructorEntity instructor) {
        this.id = id;
        this.number = number;
        this.date_start = date_start;
        this.date_end = date_end;
        this.instructor = instructor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate_start() {
        return date_start;
    }

    public void setDate_start(LocalDate date_start) {
        this.date_start = date_start;
    }

    public LocalDate getDate_end() {
        return date_end;
    }

    public void setDate_end(LocalDate date_end) {
        this.date_end = date_end;
    }

    public InstructorEntity getInstructor() {
        return instructor;
    }

    public void setInstructor(InstructorEntity instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString(){
        return "LaborContract: id: "+id+"; number: "+number+"; date_start: "+date_start+"; date_end: "+date_end+
                "; instructor: "+instructor.getSurname()+" "+instructor.getName()+" "+instructor.getPatronymic();
    }
}


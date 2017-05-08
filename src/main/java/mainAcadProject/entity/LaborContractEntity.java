package mainAcadProject.entity;
import javafx.beans.property.*;
import mainAcadProject.converter.StringDateConverter;
import mainAcadProject.dao.InstructorDao;

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
    private boolean remote=false;
    private String instructor_id;
    //private InstructorEntity instructor;

    public LaborContractEntity(){
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

    public String getNumber() {
        return number;
    }

    public StringProperty numberProperty(){
        return new SimpleStringProperty(number);
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate_start() {
        return date_start;
    }

    public StringProperty date_startProperty(){
        String date = StringDateConverter.convertToString(date_start);
        return new SimpleStringProperty(date);
    }

    public void setDate_start(LocalDate date_start) {
        this.date_start = date_start;
    }

    public LocalDate getDate_end() {
        return date_end;
    }

    public StringProperty date_endProperty(){
        String date = StringDateConverter.convertToString(date_end);
        return new SimpleStringProperty(date);
    }

    public void setDate_end(LocalDate date_end) {
        this.date_end = date_end;
    }

    public String getInstructor_id() {
        return instructor_id;
    }

    public StringProperty instructorIdProperty(){
        return new SimpleStringProperty(instructor_id);
    }

    public void setInstructor_id(String instructor_id) {
        this.instructor_id = instructor_id;
    }

    @Override
    public String toString(){
        return "LaborContract: id: "+id+"; number: "+number+"; date_start: "+date_start+"; date_end: "+date_end+ "; "+remote+ "; instructor id"+instructor_id;
    }

    public StringProperty getFullName(){
        String fullName = InstructorDao.fullNameInstructor(getInstructor_id());
        return new SimpleStringProperty(fullName);
    }




}

//"LaborContract: id: "+id+"; number: "+number+"; date_start: "+date_start+"; date_end: "+date_end+
                //"; instructor: "+instructor.getSurname()+" "+instructor.getName()+" "+instructor.getPatronymic()+"; "+remote;
package mainAcadProject.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ) on 23.03.2017.
 */
public class InstructorEntity implements Serializable {
    private int instructor_id;
    private String  surname;
    private String  name;
    private String  patronymic;
    private String  phone;
    private String  email;
    private String  working;
    List <LaborContractEntity> contracts;
    List <StudyGroupEntity> groups;

    public InstructorEntity() {
    }

    public InstructorEntity(int instructor_id, String surname, String name,
                            String patronymic, String phone, String email, String working){
        this.instructor_id=instructor_id;
        this.surname=surname;
        this.name=name;
        this.patronymic=patronymic;
        this.phone=phone;
        this.email=email;
        this.working=working;
    }

    public int getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(int instructor_id) {
        this.instructor_id = instructor_id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorking() {
        return working;
    }

    public void setWorking(String working) {
        this.working = working;
    }

    public List<LaborContractEntity> getContracts() {
        return contracts;
    }

    public void setContracts(List<LaborContractEntity> contracts) {
        this.contracts = contracts;
    }

    public List<StudyGroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(List<StudyGroupEntity> groups) {
        this.groups = groups;
    }

    @Override
    public String toString(){
        return "Instructor: [id: "+instructor_id+";/t"+"surname: "+surname+";/t"+"name: "+name+";/t"+"patronymic: "+
                patronymic+";/t"+"phone: "+phone+";/t"+"email: "+email+";/t"+"working: "+working+";]";
    }

}

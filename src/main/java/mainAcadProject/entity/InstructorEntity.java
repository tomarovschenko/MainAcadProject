package mainAcadProject.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ) on 23.03.2017.
 */
public class InstructorEntity implements Serializable {
    private int id;
    private String  surname;
    private String  name;
    private String  patronymic;
    private String  phone;
    private String passport;
    private String  email;
    private String  working;
    private boolean remote=false;
    List <LaborContractEntity> contracts=new ArrayList<>();
    List <StudyGroupEntity> groups;

    public InstructorEntity() {
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

    public String getSurname() {
        return surname;
    }

    public StringProperty surnameProperty() {
        return new SimpleStringProperty(surname);
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public StringProperty nameProperty(){
        return new SimpleStringProperty(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public StringProperty patronymicProperty(){
        return new SimpleStringProperty(patronymic);
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public StringProperty phoneProperty(){
        return new SimpleStringProperty(phone);
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public StringProperty emailProperty(){
        return new SimpleStringProperty(email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorking() {
        return working;
    }

    public StringProperty workingProperty(){
        return new SimpleStringProperty(working);
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

    public void addContracts(LaborContractEntity contract) {
        contract.setInstructor(this);
        this.contracts.add(contract);
    }

    public List<StudyGroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(List<StudyGroupEntity> groups) {
        this.groups = groups;
    }

    public String getPassport() {
        return passport;
    }

    public StringProperty passportProperty(){
        return new SimpleStringProperty(passport);
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    @Override
    public String toString(){
        return "Instructor: id: "+id+"; surname: "+surname+"; name: "+name+"; patronymic: "+
                patronymic+"; phone: "+phone+"; passport: "+passport+"; email: "+email+"; working: "+working;
    }

}

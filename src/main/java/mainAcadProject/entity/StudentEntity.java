package mainAcadProject.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mainAcadProject.dao.ManagerDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ) on 23.03.2017.
 */
public class StudentEntity implements Serializable{
    private String id;
    private String surname;
    private String name;
    private String patronymic;
    private String phone;
    private String email;
    private String address;
    private String passport;
    private boolean remote=false;
    private int manager_id;
    private List <ContractEntity> contracts = new ArrayList<>();
    private List<StudyGroupEntity> groups;

    public StudentEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }


    public StringProperty surnameProperty(){
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

    public String getAddress() {
        return address;
    }

    public StringProperty addressProperty(){
        return new SimpleStringProperty(address);
    }

    public void setAddress(String address) {
        this.address = address;
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

    public boolean isRemote() {
        return remote;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public void addContracts(ContractEntity contract) {
        this.contracts.add(contract);
    }

    public List<ContractEntity> getContracts() {
        return contracts;
    }

    public List<StudyGroupEntity> getGroups() {
        return groups;
    }

    @Override
    public String toString(){
        return "Student: id: "+id+"; surname: "+surname+"; name: "+name+"; patronymic: "+patronymic+"; tel: "+phone+
                "; passport: "+passport+"; email: "+email+"; address: "+address+"; manager: "+manager_id;
    }

    public StringProperty managerName(){
        String name = ManagerDao.managerName(getManager_id());
        return new SimpleStringProperty(name);
    }

    public String getManagerName(){
        return ManagerDao.managerName(getManager_id());
    }
}

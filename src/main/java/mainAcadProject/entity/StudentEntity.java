package mainAcadProject.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ) on 23.03.2017.
 */
public class StudentEntity implements Serializable{
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private String phone;
    private String email;
    private String address;
    private ManagerEntity manager;
    private List <ContractEntity> contracts;
    private List<StudyGroupEntity> groups;

    public StudentEntity() {
    }

    public StudentEntity(int id, String surname, String name, String patronymic, String phone, String email, String address,
                         ManagerEntity manager) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.manager = manager;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ManagerEntity getManager() {
        return manager;
    }

    public void setManager(ManagerEntity manager) {
        this.manager = manager;
    }

    public List<ContractEntity> getContract() {
        return contracts;
    }

    public void setContract(List<ContractEntity> contract) {
        this.contracts = contract;
    }

    public List<StudyGroupEntity> getGroup() {
        return groups;
    }

    public void setGroup(List<StudyGroupEntity> group) {
        this.groups = group;
    }

    @Override
    public String toString(){
        return "Student: id: "+id+"; surname: "+surname+"; name: "+name+"; patronymic: "+patronymic+"; tel: "+phone+
                "; email: "+email+"; address: "+address+"; manager: "+manager.getName();
    }
}

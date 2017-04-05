package mainAcadProject.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by ) on 05.04.2017.
 */
public class ContractEntity implements Serializable {
    private int id;
    private String numb;
    private LocalDate startDate;
    private LocalDate endDate;
    private double amount;
    private StudentEntity student;
    private List <PaymentEntity> payments;

    public ContractEntity() {
    }

    public ContractEntity(int id, String numb, LocalDate startDate, LocalDate endDate, double amount, StudentEntity student) {
        this.id = id;
        this.numb = numb;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return numb;
    }

    public void setName(String name) {
        this.numb = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public List<PaymentEntity> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentEntity> payments) {
        this.payments = payments;
    }

    @Override
    public String toString(){
        return "Contract: id: "+id+"; №: "+numb+"; start: "+startDate+"; end: "+endDate+"; amount: "+amount+
                "; student: "+student.getSurname()+" "+student.getName()+" "+student.getPatronymic();
    }
}

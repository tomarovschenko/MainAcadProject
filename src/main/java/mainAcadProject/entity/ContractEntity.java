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
    private boolean remote = false;
    private String student_id;
    private List <PaymentEntity> payments;

    public ContractEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumb() {
        return numb;
    }

    public void setNumb(String numb) {
        this.numb = numb;
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

    public boolean isRemote() {
        return remote;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    @Override
    public String toString(){
        return "Contract: id: "+id+"; №: "+numb+"; start: "+startDate+"; end: "+endDate+"; amount: "+amount+
                "; student: "+student_id;
    }
}

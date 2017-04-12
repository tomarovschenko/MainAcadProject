package mainAcadProject.entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by ) on 05.04.2017.
 */
public class PaymentEntity implements Serializable {
    private int id;
    private String amount_plan;
    private LocalDate date_plan;
    private String amount_fact;
    private LocalDate date_fact;
    private String invoice_num;
    private LocalDate invoice_date;
    private ContractEntity contract;

    public PaymentEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount_plan() {
        return amount_plan;
    }

    public void setAmount_plan(String amount_plan) {
        this.amount_plan = amount_plan;
    }

    public LocalDate getDate_plan() {
        return date_plan;
    }

    public void setDate_plan(LocalDate date_plan) {
        this.date_plan = date_plan;
    }

    public String getAmount_fact() {
        return amount_fact;
    }

    public void setAmount_fact(String amount_fact) {
        this.amount_fact = amount_fact;
    }

    public LocalDate getDate_fact() {
        return date_fact;
    }

    public void setGetDate_plan(LocalDate date_fact) {
        this.date_fact = date_fact;
    }

    public String getInvoice_num() {
        return invoice_num;
    }

    public void setInvoice_num(String invoice_num) {
        this.invoice_num = invoice_num;
    }

    public LocalDate getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(LocalDate invoice_date) {
        this.invoice_date = invoice_date;
    }

    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }

    @Override
    public String toString(){
        return "Payment: id: "+id+";  amount_plan: "+amount_plan+"; date_plan: "+date_plan+"; invoice: "+invoice_num+
                "; date: "+invoice_date+"; amount_fact: "+amount_fact+"; date_fact: "+date_fact+"; contract: "+contract.getName();
    }
}

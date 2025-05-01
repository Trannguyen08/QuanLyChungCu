package main.java.utc2.apartmentManage.model;

public class PaidHistory {
    private int bill_id;
    private String paidDate;
    private double amount;
    private String note;

    public PaidHistory(int bill_id, String paidDate, double amount, String note) {
        this.bill_id = bill_id;
        this.paidDate = paidDate;
        this.amount = amount;
        this.note = note;
    }

    public int getBill_id() {
        return bill_id;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public double getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
    
}

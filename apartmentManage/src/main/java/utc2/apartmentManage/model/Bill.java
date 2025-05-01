
package main.java.utc2.apartmentManage.model;

public class Bill {
    private int billId;
    private Double totalAmount;
    private String billDate;
    private String dueDate;
    private String status;

    public Bill(int billId, String billDate, String dueDate, Double totalAmount, String status) {
        this.billId = billId;
        this.totalAmount = totalAmount;
        this.billDate = billDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public int getBillId() {
        return billId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public String getBillDate() {
        return billDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }


    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
package main.java.utc2.apartmentManage.model;

public class BillInfo {
    private int billId;
    private int apartmentId;
    private String fullName;
    private String dueDate;
    private double totalAmount;

    public BillInfo(int billId, int apartmentId, String fullName, String dueDate, double totalAmount) {
        this.billId = billId;
        this.apartmentId = apartmentId;
        this.fullName = fullName;
        this.dueDate = dueDate;
        this.totalAmount = totalAmount;
    }

    public int getBillId() {
        return billId;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    
}

package java.utc2.apartmentManage.model;

public class BillManager {
    private int billId;
    private int apartmentId;
    private String fullName;
    private String dueDate;
    private double totalAmount;
    private String status;
    private String paymentDate;

    public BillManager(int billId, int apartmentId, String fullName, String dueDate, double totalAmount, String status, String paymentDate) {
        this.billId = billId;
        this.apartmentId = apartmentId;
        this.fullName = fullName;
        this.dueDate = dueDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentDate = paymentDate;
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

    public String getStatus() {
        return status;
    }

    public String getPaymentDate() {
        return paymentDate;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    
    
}

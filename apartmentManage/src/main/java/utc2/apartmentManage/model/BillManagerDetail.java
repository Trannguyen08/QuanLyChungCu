package main.java.utc2.apartmentManage.model;

public class BillManagerDetail {
    private int id;
    private String name;
    private double price;
    private String paidDate;

    public BillManagerDetail(int id, String name, double price, String paidDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.paidDate = paidDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }
    
    
}

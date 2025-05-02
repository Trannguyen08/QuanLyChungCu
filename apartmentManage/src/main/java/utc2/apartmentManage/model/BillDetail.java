package main.java.utc2.apartmentManage.model;

public class BillDetail {
    private int bill_id;
    private String name;
    private double price;
    private int num;
    private double amount;

    public BillDetail(int bill_id, String name, double price, int num, double amount) {
        this.bill_id = bill_id;
        this.name = name;
        this.price = price;
        this.num = num;
        this.amount = amount;
    }

    public int getBill_id() {
        return bill_id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getNum() {
        return num;
    }

    public double getAmount() {
        return amount;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    
    
}

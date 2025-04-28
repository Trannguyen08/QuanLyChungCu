package main.java.utc2.apartmentManage.model;

public class Apartment {
    private int id;
    private int index;
    private int floor;
    private String building;
    private int numRooms;
    private int numWc;
    private String interior;
    private String status;
    private double area;
    private double rentPrice;
    private double purchasePrice;

    public Apartment(int id, int index, int floor, String building, int numRooms, int numWc, String interior,
                     String status, double area, double rentPrice, double purchasePrice) {
        this.id = id;
        this.index = index;
        this.floor = floor;
        this.building = building;
        this.numRooms = numRooms;
        this.status = status;
        this.area = area;
        this.rentPrice = rentPrice;
        this.purchasePrice = purchasePrice;
        this.numWc = numWc;
        this.interior = interior;
    }

    public double getArea() {
        return area;
    }

    public String getBuilding() {
        return building;
    }

    public int getFloor() {
        return floor;
    }

    public int getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public String getStatus() {
        return status;
    }

    public int getNumWc() {
        return numWc;
    }

    public String getInterior() {
        return interior;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    public void setNumWc(int numWc) {
        this.numWc = numWc;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

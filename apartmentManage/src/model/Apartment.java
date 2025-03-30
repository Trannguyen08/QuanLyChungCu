package model;

public class Apartment {
    private int id;
    private int index;
    private int floor;
    private String building;
    private int numRooms;
    private String status;
    private double area;
    private double rentPrice;
    private long purchasePrice;

    public Apartment(int id, int index, int floor, String building, int numRooms, String status, double area, double rentPrice, long purchasePrice) {
        this.id = id;
        this.index = index;
        this.floor = floor;
        this.building = building;
        this.numRooms = numRooms;
        this.status = status;
        this.area = area;
        this.rentPrice = rentPrice;
        this.purchasePrice = purchasePrice;
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

    public long getPurchasePrice() {
        return purchasePrice;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public String getStatus() {
        return status;
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

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    public void setPurchasePrice(long purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "area=" + area +
                ", id=" + id +
                ", index=" + index +
                ", floor=" + floor +
                ", building='" + building + '\'' +
                ", numRooms=" + numRooms +
                ", status='" + status + '\'' +
                ", rentPrice=" + rentPrice +
                ", purchasePrice=" + purchasePrice +
                '}';
    }
}

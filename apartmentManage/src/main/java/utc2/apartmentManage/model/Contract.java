package main.java.utc2.apartmentManage.model;

public class Contract {
    private int id;
    private String ownerName;
    private String apartmentIndex;
    private String contractType;
    private String startDate;
    private String endDate;
    private double contractValue;
    private String contractStatus;

    public Contract(int id, String ownerName, String apartmentIndex, String contractType, String startDate,
                    String endDate, double contractValue, String contractStatus) {
        this.id = id;
        this.ownerName = ownerName;
        this.apartmentIndex = apartmentIndex;
        this.contractType = contractType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contractValue = contractValue;
        this.contractStatus = contractStatus;
    }

    public String getApartmentIndex() {
        return apartmentIndex;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public String getContractType() {
        return contractType;
    }

    public double getContractValue() {
        return contractValue;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setApartmentIndex(String apartmentIndex) {
        this.apartmentIndex = apartmentIndex;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public void setContractValue(double contractValue) {
        this.contractValue = contractValue;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}

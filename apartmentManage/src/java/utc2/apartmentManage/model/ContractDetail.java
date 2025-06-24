package java.utc2.apartmentManage.model;

public class ContractDetail {
    private int contractId;
    private int apartmentId;
    private int residentId;
    private String contractType;
    private String startDate;
    private String endDate;
    private double contractValue;
    private String buyerName;
    private String buyerPhone;
    private String buyerEmail;
    private String buyerCCCD;
    private String building;
    private double area;
    private double purchasePrice;
    private int floor;
    private String apartmentIndex;

    public ContractDetail(int contractId, int apartmentId, int residentId, String contractType, 
                            String startDate, String endDate, double contractValue, String buyerName, 
                            String buyerPhone, String buyerEmail, String buyerCCCD, String building, 
                            double area, double purchasePrice, int floor, String apartmentIndex) {
        
        this.contractId = contractId;
        this.apartmentId = apartmentId;
        this.residentId = residentId;
        this.contractType = contractType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contractValue = contractValue;
        this.buyerName = buyerName;
        this.buyerPhone = buyerPhone;
        this.buyerEmail = buyerEmail;
        this.buyerCCCD = buyerCCCD;
        this.building = building;
        this.area = area;
        this.purchasePrice = purchasePrice;
        this.floor = floor;
        this.apartmentIndex = apartmentIndex;
    }

    public int getContractId() {
        return contractId;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public int getResidentId() {
        return residentId;
    }

    public String getContractType() {
        return contractType;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getContractValue() {
        return contractValue;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public String getBuyerCCCD() {
        return buyerCCCD;
    }

    public String getBuilding() {
        return building;
    }

    public double getArea() {
        return area;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public int getFloor() {
        return floor;
    }

    public String getApartmentIndex() {
        return apartmentIndex;
    }
    
    
    
}

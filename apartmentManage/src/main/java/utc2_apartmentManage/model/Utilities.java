
package main.java.utc2_apartmentManage.model;

public class Utilities {
    private String serviceName;
    private String serviceType;
    private double price;
    private String unit;
    private String description;

    public Utilities(String serviceName, String serviceType, double price, String unit, String description) {
        this.serviceName = serviceName;
        this.serviceType = serviceType;
        this.price = price;
        this.unit = unit;
        this.description = description;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public Double getPrice() {
        return price;
    }

    public String getUnit() {
        return unit;
    }
    
    public String getDescription() {
        return description;
    }


    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}


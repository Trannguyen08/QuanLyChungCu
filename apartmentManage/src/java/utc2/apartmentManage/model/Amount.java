package java.utc2.apartmentManage.model;

public class Amount {
    private String name;
    private double total;
    private double percent;

    public Amount(String name, double total) {
        this.name = name;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public double getTotal() {
        return total;
    }

    public double getPercent() {
        return percent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
    
    
    
}

package java.utc2.apartmentManage.model;

public class Attendance {
    private int id;
    private String date;
    private String checkin;
    private String checkout;
    private int employeeId;

    public Attendance(int id, String date, String checkin, String checkout, int employeeId) {
        this.id = id;
        this.date = date;
        this.checkin = checkin;
        this.checkout = checkout;
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    
    
    
}

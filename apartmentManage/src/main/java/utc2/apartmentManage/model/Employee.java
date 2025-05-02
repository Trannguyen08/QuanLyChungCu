package main.java.utc2.apartmentManage.model;


public class Employee {
    private int id;
    private String name;
    private String gender;
    private String date;
    private String phoneNumber;
    private String email;
    private String idcard;
    private String position;
    private double salary;
    private String status;
    private int infoID;
    private int accID;
    private String shift;

    public Employee(int id, String name, String gender, String date, String phoneNumber, String email, 
                    String idcard, String position, double salary, String status, int infoID, int accID, String shift) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.position = position;
        this.salary = salary;
        this.status = status;
        this.date = date;
        this.infoID = infoID;
        this.accID = accID;
        this.idcard = idcard;
        this.shift = shift;
    }

    public int getId() {
        return id;
    }

    public String getShift() {
        return shift;
    }
    
    

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public int getInfoID() {
        return infoID;
    }

    public int getAccID() {
        return accID;
    }

    public String getIdcard() {
        return idcard;
    }
    
    

    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

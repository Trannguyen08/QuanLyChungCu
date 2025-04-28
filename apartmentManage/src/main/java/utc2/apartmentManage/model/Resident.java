package main.java.utc2.apartmentManage.model;

import java.sql.Date;


public class Resident {
    private int residentID;
    private String name;
    private String gender;
    private String birthDate;
    private String phoneNumber;
    private String email;
    private String idCard;
    private int apartmentID;
    private int userID;
    private int infoID;

    public Resident(int residentID, String name, String gender, String birthDate, String phoneNumber, 
                    String email, String idCard, int apartmentID, int userID, int infoID) {
        this.residentID = residentID;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.idCard = idCard;
        this.apartmentID = apartmentID;
        this.userID = userID;
        this.infoID = infoID;
    }

    public int getResidentID() {
        return residentID;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getIdCard() {
        return idCard;
    }

    public int getApartmentID() {
        return apartmentID;
    }

    public int getUserID() {
        return userID;
    }

    public int getInfoID() {
        return infoID;
    }
    
    
    
    

    public void setResidentID(int residentID) {
        this.residentID = residentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setApartmentID(int apartmentID) {
        this.apartmentID = apartmentID;
    }
}

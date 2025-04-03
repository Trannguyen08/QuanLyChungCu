package main.java.utc2_apartmentManage.model;

import java.sql.Date;


public class Resident {
    private int residentID;
    private String name;
    private String gender;
    private Date birthDate;
    private String phoneNumber;
    private String email;
    private String idCard;
    private int apartmentID;

    public Resident(int residentID, String name, String gender, Date birthDate, 
                    String phoneNumber, String email, String idCard, int apartmentID) {
        this.residentID = residentID;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.idCard = idCard;
        this.apartmentID = apartmentID;
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

    public Date getBirthDate() {
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

    public void setResidentID(int residentID) {
        this.residentID = residentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDate(Date birthDate) {
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

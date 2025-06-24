package java.utc2.apartmentManage.model;

public class Resident {
    private int residentID;
    private String name;
    private String apartmentInfo;
    private String birthDate;
    private String phoneNumber;
    private String email;
    private String idCard;
    private int apartmentID;
    private int userID;
    private int infoID;
    private String contractStatus;

    public Resident(int residentID, String name, String apartmentInfo, String birthDate, String phoneNumber,
                    String email, String idCard, int apartmentID, int userID, int infoID, String contractStatus) {
        this.residentID = residentID;
        this.name = name;
        this.apartmentInfo = apartmentInfo;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.idCard = idCard;
        this.apartmentID = apartmentID;
        this.userID = userID;
        this.infoID = infoID;
        this.contractStatus = contractStatus;
    }

    public int getResidentID() {
        return residentID;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return apartmentInfo;
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

    public String getContractStatus() {
        return contractStatus;
    }
    
    
    
    

    public void setResidentID(int residentID) {
        this.residentID = residentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String apartmentInfo) {
        this.apartmentInfo = apartmentInfo;
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

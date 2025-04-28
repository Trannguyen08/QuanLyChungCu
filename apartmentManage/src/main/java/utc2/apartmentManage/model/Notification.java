package main.java.utc2.apartmentManage.model;

public class Notification {
    private int ID;
    private String type;
    private String title;
    private String mess;
    private String sentDate;
    
    public Notification(int ID, String type, String title, String mess, String sentDate){
        this.ID = ID;
        this.mess = mess;
        this.sentDate = sentDate;
        this.title = title;
        this.type= type;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
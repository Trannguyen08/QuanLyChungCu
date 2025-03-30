package model;


public class Notification {
    private int ID;
    private  int ownerID;
    private String title;
    private String mess;
    private String type;
    
    public Notification(int ID, int ownerID, String title, String mess, String type){
        this.ID = ID;
        this.mess = mess;
        this.ownerID = ownerID;
        this.title = title;
        this.type= type;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
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
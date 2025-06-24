package java.utc2.apartmentManage.model;

import java.time.LocalDateTime;

public class Notification {
    private int ID;
    private String recipant;
    private String type;
    private String title;
    private String mess;
    private LocalDateTime sentDate;
    private int seen;
    
    public Notification(int ID, String recipant, String type, String title, String mess, LocalDateTime sentDate, int seen){
        this.recipant = recipant;
        this.ID = ID;
        this.mess = mess;
        this.sentDate = sentDate;
        this.title = title;
        this.type= type;
        this.seen = seen;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }

    public String getTitle() {
        return title;
    }

    public int getSeen() {
        return seen;
    }

    public String getRecipant() {
        return recipant;
    }

    public void setRecipant(String recipant) {
        this.recipant = recipant;
    }

    public void setSeen(int seen) {
        this.seen = seen;
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
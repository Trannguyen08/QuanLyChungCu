package java.utc2.apartmentManage.model;

public class Account {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phoneNum;
    private String role;

    public Account(int id, String username, String password, String email, String phoneNum, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getRole() {
        return role;
    }
    

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

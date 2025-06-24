package java.utc2.apartmentManage.model;

public class EmployeeInfo {
    private int id;
    private String name;
    private String job;
    private String shift;
    private int workDateNum;

    public EmployeeInfo(int id, String name, String job, String shift, int workDateNum) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.shift = shift;
        this.workDateNum = workDateNum;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShift() {
        return shift;
    }

    public int getWorkDateNum() {
        return workDateNum;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
    
    

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public void setWorkDateNum(int workDateNum) {
        this.workDateNum = workDateNum;
    }
    
    
    
}

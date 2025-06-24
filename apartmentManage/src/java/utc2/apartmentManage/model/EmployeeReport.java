package java.utc2.apartmentManage.model;
 
public class EmployeeReport {
    private int id;
    private String name;
    private String job;
    private String shift;
    private int workDateNum;
    private int totalMin;
    private double salary;
    private double bonus;
    private double foul;
    private double exactlySalary;

    public EmployeeReport(int id, String name, String job, String shift, int workDateNum, 
                        int totalHour, double salary, double bonus, double foul, double exactlySalary) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.shift = shift;
        this.workDateNum = workDateNum;
        this.totalMin = totalHour;
        this.salary = salary;
        this.bonus = bonus;
        this.foul = foul;
        this.exactlySalary = exactlySalary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public void setWorkDateNum(int workDateNum) {
        this.workDateNum = workDateNum;
    }

    public void setTotalHour(int totalHour) {
        this.totalMin = totalHour;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public void setFoul(double foul) {
        this.foul = foul;
    }

    public void setExactlySalary(double exactlySalary) {
        this.exactlySalary = exactlySalary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getShift() {
        return shift;
    }

    public int getWorkDateNum() {
        return workDateNum;
    }

    public int getTotalHour() {
        return totalMin;
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    public double getFoul() {
        return foul;
    }

    public double getExactlySalary() {
        return exactlySalary;
    }
    
    
    
    
    
}

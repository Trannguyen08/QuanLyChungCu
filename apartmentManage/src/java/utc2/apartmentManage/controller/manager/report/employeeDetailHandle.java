package java.utc2.apartmentManage.controller.manager.report;

import utc2.apartmentManage.model.EmployeeInfo;
import utc2.apartmentManage.service.implement.manager.reportIEmployeeDetailMP;

import javax.swing.*;


public class employeeDetailHandle {
    private JComboBox<String> month, year;
    private JTable table;
    private JLabel id, name, chucvu, calam, songay;
    private EmployeeInfo er;
    private final reportIEmployeeDetailMP reportService = new reportIEmployeeDetailMP();

    public employeeDetailHandle(JComboBox<String> month, JComboBox<String> year, JTable table, JLabel id, 
                                JLabel name, JLabel job, JLabel calam, JLabel songay, EmployeeInfo er) {
        this.month = month;
        this.year = year;
        this.table = table;
        this.id = id;
        this.name = name;
        this.calam = calam;
        this.songay = songay;
        this.er = er;
        this.chucvu = job;
        
        init();
    }
    
    public void init() {
        id.setText("ID: " + er.getId());
        name.setText("Tên nhân viên: " + er.getName());
        chucvu.setText("Chức vụ: " + er.getJob());
        calam.setText("Ca làm: " + er.getShift());
        songay.setText("Số ngày công: " + er.getWorkDateNum());
        
        int monthNum = Integer.parseInt(month.getSelectedItem().toString().trim());
        int yearNum = Integer.parseInt(year.getSelectedItem().toString().trim());
        
        reportService.setUpTable2(table, er.getId(), monthNum, yearNum);    
    } 
}

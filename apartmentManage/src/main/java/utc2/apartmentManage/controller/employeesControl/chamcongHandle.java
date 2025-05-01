package main.java.utc2.apartmentManage.controller.employeesControl;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import javax.swing.*;
import main.java.utc2.apartmentManage.model.Account;
import main.java.utc2.apartmentManage.model.Attendance;
import main.java.utc2.apartmentManage.model.Employee;
import main.java.utc2.apartmentManage.repository.ManagerRepository.employeeRepository;
import main.java.utc2.apartmentManage.service.employeesService.chamcongIMP;

public class chamcongHandle {
    private Account acc;
    private Employee emp;
    private JTable table;
    private JButton ccBtn;
    private JComboBox<String> month, year;
    private chamcongIMP ccService = new chamcongIMP();
    private employeeRepository employeeRepo = new employeeRepository();
    private boolean isCheckInDone = false;
    private boolean isCheckOutDone = false;
    private int attenID;


    public chamcongHandle(JTable table, JButton ccBtn, JComboBox<String> month, JComboBox<String> year, Account acc) {
        this.table = table;
        this.ccBtn = ccBtn;
        this.month = month;
        this.year = year;
        this.acc = acc;
        this.emp = employeeRepo.getEmployeeByAccID(acc.getId());
        
        this.month.addActionListener(e -> {
            loadData();
        });

        this.year.addActionListener(e -> {
            loadData();
        });
        
        loadData();
        addEvent();
    }
    
    public void loadData() {
        Attendance a = ccService.isExistTodayDate(emp.getId());
        if( a == null ) {
            this.attenID = ccService.getNewID();
            ccService.add(this.attenID, emp.getId(), getTodaySqlDate());
        } else {
            // Nếu đã có -> kiểm tra checkin/check out để gán biến trạng thái
            String checkIn = a.getCheckin();
            String checkOut = a.getCheckout();

            isCheckInDone = (checkIn != null);
            isCheckOutDone = (checkOut != null);
        }
        
        int monthNum = Integer.parseInt((String) month.getSelectedItem());
        int yearNum = Integer.parseInt((String) year.getSelectedItem());
        ccService.setUpTable(table, monthNum, yearNum, emp.getId());
    }
    
    public Time getTodaySqlTime() {
        return new Time(System.currentTimeMillis());
    }

    public Date getTodaySqlDate() {
        return Date.valueOf(LocalDate.now());
    }

    private void addEvent() {
        this.ccBtn.addActionListener(e -> {
            Time now = getTodaySqlTime();
            Date today = getTodaySqlDate();
            if (!isCheckInDone) {
                ccService.addCheckInTime(today, now);
                JOptionPane.showMessageDialog(null, "Bạn đã vào ca lúc: " + now.toString());
                isCheckInDone = true;
            } else if (!isCheckOutDone) {
                ccService.addCheckOutTime(today, now);
                JOptionPane.showMessageDialog(null, "Bạn đã kết ca lúc: " + now.toString());
                isCheckOutDone = true;
            } else {
                JOptionPane.showMessageDialog(null, "Bạn đã hoàn thành chấm công hôm nay!");
            }

            loadData(); 
        });
    }




}
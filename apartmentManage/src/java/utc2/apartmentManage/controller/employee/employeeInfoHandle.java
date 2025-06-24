package java.utc2.apartmentManage.controller.employee;

import utc2.apartmentManage.model.*;
import utc2.apartmentManage.repository.manager.employeeRepository;
import utc2.apartmentManage.view.employee.editEmployeeInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class employeeInfoHandle {
    private JLabel dob, employee_id, gender, idcard, mail, name, phone;
    private JButton editBtn;
    private Account acc;
    private Employee emp;
    private employeeRepository employeeRepo = new employeeRepository();

    public employeeInfoHandle(JLabel dob, JLabel employee_id, JLabel gender, JLabel idcard, JLabel mail, 
                            JLabel name, JLabel phone, JButton editBtn, Account acc) {
        this.dob = dob;
        this.employee_id = employee_id;
        this.gender = gender;
        this.idcard = idcard;
        this.mail = mail;
        this.name = name;
        this.phone = phone;
        this.editBtn = editBtn;
        this.acc = acc;
        this.emp = employeeRepo.getEmployeeByAccID(acc.getId());
        
        this.editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new editEmployeeInfo(emp.getInfoID(), emp.getId(), mail, phone, idcard).setVisible(true);
            }
        });
        
        loadData();
    }
    
    public void loadData() {
        employee_id.setText(String.valueOf(emp.getId()));
        name.setText(emp.getName());
        gender.setText(emp.getGender());
        idcard.setText(emp.getIdcard());
        mail.setText(emp.getEmail());
        phone.setText(emp.getPhoneNumber());
        dob.setText(emp.getDate());
    }
    
}


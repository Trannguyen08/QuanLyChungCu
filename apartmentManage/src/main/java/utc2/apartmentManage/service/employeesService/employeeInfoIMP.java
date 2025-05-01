package main.java.utc2.apartmentManage.service.employeesService;

import javax.swing.JOptionPane;
import main.java.utc2.apartmentManage.model.Employee;
import main.java.utc2.apartmentManage.repository.employeesRepository.employeeInfoRepository;

public class employeeInfoIMP {
    private final employeeInfoRepository ei = new employeeInfoRepository();
    public boolean isDuplicate(Employee emp) {
        
        int ans = ei.isDuplicateEmployee(emp);
        
        switch(ans) {
            case 1 -> {
                JOptionPane.showMessageDialog(null, "Email này đã tồn tại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            case 2 -> {
                JOptionPane.showMessageDialog(null, "Số điện thoại này đã tồn tại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            case 3 -> {
                JOptionPane.showMessageDialog(null, "CCCD này đã tồn tại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return false;            
            }
        }
        
        return true;
    }
    
    public boolean update(Employee emp) {
        return ei.updateInfo(emp);
    }
}

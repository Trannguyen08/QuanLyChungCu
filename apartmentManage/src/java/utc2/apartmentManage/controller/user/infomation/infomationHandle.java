package java.utc2.apartmentManage.controller.user.infomation;

import utc2.apartmentManage.model.*;
import utc2.apartmentManage.service.export.contractsExport;
import utc2.apartmentManage.service.implement.user.infoIMP;
import utc2.apartmentManage.view.user.editWindow.editResidentInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class infomationHandle {
    private Account acc;
    private JButton editBtn, detailContractBtn;
    private JLabel apartment_id, area, contract_id, contract_name, dob, endDate,
                     gender, idcard, index, interior, mail, name, phone,
                     resident_id, roomNum, startDate, type, wcNum;
    private int infoID;
    private Resident resident;
    private Contract contract;
    private final infoIMP infoService = new infoIMP();

    public infomationHandle(Account acc, JButton editBtn, JButton detailContractBtn,
                            JLabel apartment_id, JLabel area, JLabel contract_id,
                            JLabel contract_name, JLabel dob, JLabel endDate, JLabel gender,
                            JLabel idcard, JLabel index, JLabel interior, JLabel mail,
                            JLabel name, JLabel phone, JLabel resident_id,
                            JLabel roomNum, JLabel startDate, JLabel type, JLabel wcNum) {
        
        this.acc = acc;
        this.editBtn = editBtn;
        this.detailContractBtn = detailContractBtn;
        this.apartment_id = apartment_id;
        this.area = area;
        this.contract_id = contract_id;
        this.contract_name = contract_name;
        this.dob = dob;
        this.endDate = endDate;
        this.gender = gender;
        this.idcard = idcard;
        this.index = index;
        this.interior = interior;
        this.mail = mail;
        this.name = name;
        this.phone = phone;
        this.resident_id = resident_id;
        this.roomNum = roomNum;
        this.startDate = startDate;
        this.type = type;
        this.wcNum = wcNum;
        
        loadData();
        
        this.editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editResidentInfo editResidentInfo = new editResidentInfo(infoID, mail, phone, idcard);
                editResidentInfo.setVisible(true);
            }
        });
        
        this.detailContractBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailBtnClick();
            }
        });
    }
    
    private void detailBtnClick() {
        String dirPath = System.getProperty("user.dir") + File.separator + "Data";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fullFilePath = dirPath + File.separator + "contract_" + contract.getId() + ".pdf";

        // Xuất file PDF
        contractsExport.exportContractToPDF(fullFilePath, contract.getId());

        // Mở file nếu đã tạo thành công
        File pdfFile = new File(fullFilePath);
        if (pdfFile.exists()) {
            contractsExport.openPDF(fullFilePath);
        } else {
            JOptionPane.showMessageDialog(null, "Không thể tạo hoặc tìm thấy file: " + fullFilePath);
        }
    }
    
    private void loadData() {
        int accID = acc.getId();
        this.resident = infoService.getResidentByAccountID(accID);
        this.infoID = resident.getInfoID();
        Apartment apartment = infoService.getApartmentByResidentID(resident.getApartmentID());
        this.contract = infoService.getContractByResidentID(resident.getResidentID());
        

        // Thông tin cá nhân
        resident_id.setText(String.valueOf(resident.getResidentID()));
        name.setText(resident.getName());
        dob.setText(resident.getBirthDate());
        gender.setText(resident.getGender());
        phone.setText(resident.getPhoneNumber());
        mail.setText(resident.getEmail());
        idcard.setText(resident.getIdCard());

        // Thông tin căn hộ
        apartment_id.setText(String.valueOf(apartment.getId()));
        index.setText(contract.getApartmentIndex());
        roomNum.setText(String.valueOf(apartment.getNumRooms()));
        area.setText(apartment.getArea() + " m²");
        wcNum.setText(String.valueOf(apartment.getNumWc()));
        interior.setText(apartment.getInterior());

        // Thông tin hợp đồng
        contract_id.setText(String.valueOf(contract.getId()));
        contract_name.setText(contract.getOwnerName());
        type.setText(contract.getContractType());
        startDate.setText(contract.getStartDate());
        endDate.setText(contract.getEndDate());
           
    }

    
    

    
    
}

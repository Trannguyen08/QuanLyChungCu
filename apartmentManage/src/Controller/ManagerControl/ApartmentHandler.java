package Controller.ManagerControl;

import Model.ManagerDAO.Excel;
import View.ManagerUI.addButtonWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApartmentHandler {
    private JButton addBtn, editBtn, deleteBtn, excelBtn;
    private JTable table;
    private JPanel panel;
    private deleteButtonHandler deleteHandler;

    public ApartmentHandler(JButton addBtn, JButton deleteBtn, JButton editBtn, JButton excelBtn, JTable table, JPanel panel) {
        this.addBtn = addBtn;
        this.deleteBtn = deleteBtn;
        this.editBtn = editBtn;
        this.excelBtn = excelBtn;
        this.table = table;
        this.panel = panel;

        this.addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBtnClick();
            }
        });
        this.deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBtnClick();
            }
        });
        this.excelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excelBtnClick();
            }
        });
    }

    private void addBtnClick() {
        new addButtonWindow().setVisible(true);
    }

    private void deleteBtnClick() {
        if (deleteHandler == null) {
            deleteHandler = new deleteButtonHandler(deleteBtn, table, panel);
        }
    }

    private void excelBtnClick() {
        Excel.exportApartments("/Data/apartments.xlsx");
    }

}

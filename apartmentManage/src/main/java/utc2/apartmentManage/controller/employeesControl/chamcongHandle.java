package main.java.utc2.apartmentManage.controller.employeesControl;

import main.java.utc2.apartmentManage.service.employeesService.chamcongService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class chamcongHandle {
    private chamcongService service;

    public chamcongHandle() {
        this.service = new chamcongService();
    }

    public void checkIn(int employeeId, LocalDate date, LocalTime checkInTime) {
        service.saveAttendance(employeeId, date, checkInTime, null);
    }

    public void checkOut(int employeeId, LocalDate date, LocalTime checkInTime, LocalTime checkOutTime) {
        service.saveAttendance(employeeId, date, checkInTime, checkOutTime);
    }

    public List<String[]> getAttendanceRecords(int employeeId, int month, int year) {
        return service.getAttendanceRecords(employeeId, month, year);
    }
}
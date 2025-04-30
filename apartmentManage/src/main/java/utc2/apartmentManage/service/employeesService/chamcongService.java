package main.java.utc2.apartmentManage.service.employeesService;

import main.java.utc2.apartmentManage.repository.employeesRepository.chamcongRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class chamcongService {
    private chamcongRepository repository;  

    public chamcongService() {
        this.repository = new chamcongRepository();
    }

    public void saveAttendance(int employeeId, LocalDate date, LocalTime checkInTime, LocalTime checkOutTime) {
        repository.saveAttendance(employeeId, date, checkInTime, checkOutTime);
    }

    public List<String[]> getAttendanceRecords(int employeeId, int month, int year) {
        return repository.getAttendanceRecords(employeeId, month, year);
    }
}
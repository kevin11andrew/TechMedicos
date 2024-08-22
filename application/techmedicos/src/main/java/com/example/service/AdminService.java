package com.example.service;
import com.example.exception.ServiceException;
import com.example.models.Doctor;
import com.example.models.Patient;
import com.example.models.Schedule;
import com.example.models.User;

import java.time.LocalDate;
import java.util.List;

public interface AdminService {
    void importDoctors(String filePath) throws Exception;
    void registerDoctor(int id,String name,String speciality,int contact,String password) throws ServiceException;
    void updateDoctorSchedule(Schedule schedule) throws ServiceException;
    void cancelDoctorAppointment(int appointmentId, LocalDate date,int timeSlot) throws ServiceException;
    void addHospitalStaff(User user) throws ServiceException;
    Patient viewPatientReport(Long patientId) throws ServiceException;
    List<Doctor> getAllDoctors() throws ServiceException;
}

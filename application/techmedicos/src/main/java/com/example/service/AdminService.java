package com.example.service;
import com.example.exception.ServiceException;
import com.example.models.Doctor;
import com.example.models.Patient;
import com.example.models.Schedule;
import com.example.models.User;

import java.util.List;

public interface AdminService {
    void registerDoctor(Doctor doctor) throws ServiceException;
    void updateDoctorSchedule(Schedule schedule) throws ServiceException;
    void cancelDoctorAppointment(Long appointmentId) throws ServiceException;
    void addHospitalStaff(User user) throws ServiceException;
    Patient viewPatientReport(Long patientId) throws ServiceException;
    List<Doctor> getAllDoctors() throws ServiceException;
}

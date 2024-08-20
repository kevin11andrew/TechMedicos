package com.example.service;

import com.example.exception.ServiceException;
import com.example.models.Doctor;
import com.example.models.Patient;
import com.example.models.Schedule;
import com.example.models.User;
import com.example.repository.MedicalRepositoryFactory;
import java.util.List;

public class AdminServiceImpl implements AdminService{
    MedicalRepositoryFactory medicalRepositoryFactory;

    public AdminServiceImpl(MedicalRepositoryFactory medicalRepositoryFactory) {
        this.medicalRepositoryFactory = medicalRepositoryFactory;
    }

    public void registerDoctor(Doctor doctor) throws ServiceException {
        try {
            medicalRepositoryFactory.save(doctor);
        } catch (Exception e) {
            throw new ServiceException("Error registering doctor", e);
        }
    }

    @Override
    public void updateDoctorSchedule(Schedule schedule) throws ServiceException {
        try {
            medicalRepositoryFactory.updateSchedule(schedule);
        } catch (Exception e) {
            throw new ServiceException("Error updating doctor schedule", e);
        }
    }

    @Override
    public void cancelDoctorAppointment(Long appointmentId) throws ServiceException {
        try {
            medicalRepositoryFactory.delete(appointmentId);
        } catch (Exception e) {
            throw new ServiceException("Error canceling appointment", e);
        }
    }

    @Override
    public void addHospitalStaff(User user) throws ServiceException {
        try {
            medicalRepositoryFactory.save(user);
        } catch (Exception e) {
            throw new ServiceException("Error adding hospital staff", e);
        }
    }

    @Override
    public Patient viewPatientReport(Long patientId) throws ServiceException {
        try {
            return medicalRepositoryFactory.getReportById(patientId);
        } catch (Exception e) {
            throw new ServiceException("Error viewing patient report", e);
        }
    }

    @Override
    public List<Doctor> getAllDoctors() throws ServiceException {
        try {
            return medicalRepositoryFactory.findAll();
        } catch (Exception e) {
            throw new ServiceException("Error fetching all doctors", e);
        }
    }
}

package com.example.service;

import com.example.exception.ServiceException;
import com.example.models.Patient;
import com.example.models.Schedule;
import com.example.repository.MedicalRepositoryFactory;
import com.example.models.Appointment;
import java.util.List;

public class DoctorServiceImpl implements DoctorService{
    MedicalRepositoryFactory medicalRepositoryFactory;

    public DoctorServiceImpl(MedicalRepositoryFactory medicalRepositoryFactory) {
        this.medicalRepositoryFactory = medicalRepositoryFactory;
    }

    @Override
    public void addSchedule(Schedule schedule) throws ServiceException {
        try {
            medicalRepositoryFactory.save(schedule);
        } catch (Exception e) {
            throw new ServiceException("Error adding schedule", e);
        }
    }

    @Override
    public List<Appointment> viewAppointments(int doctorId) throws ServiceException {
        try {
            return medicalRepositoryFactory.findByDoctorId(doctorId);
        } catch (Exception e) {
            throw new ServiceException("Error viewing appointments", e);
        }
    }

    @Override
    public void cancelAppointment(int appointmentId) throws ServiceException {
        try {
            medicalRepositoryFactory.delete(appointmentId);
        } catch (Exception e) {
            throw new ServiceException("Error canceling appointment", e);
        }
    }

    @Override
    public Patient viewPatientReport(int patientId) throws ServiceException {
        try {
            return medicalRepositoryFactory.getReportById(patientId);
        } catch (Exception e) {
            throw new ServiceException("Error viewing patient report", e);
        }
    }
}

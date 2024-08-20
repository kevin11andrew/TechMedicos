package com.example.service;

import com.example.exception.ServiceException;
import com.example.models.Appointment;
import com.example.models.Doctor;
import com.example.models.Patient;
import com.example.repository.MedicalRepositoryFactory;

import java.util.List;

public class UserServiceImpl implements UserService {
    MedicalRepositoryFactory medicalRepositoryFactory;

    public UserServiceImpl(MedicalRepositoryFactory medicalRepositoryFactory) {
        this.medicalRepositoryFactory = medicalRepositoryFactory;
    }

    @Override
    public void registerPatient(Patient patient) throws ServiceException {
        try {
            medicalRepositoryFactory.save(patient);
        } catch (Exception e) {
            throw new ServiceException("Error registering patient", e);
        }
    }

    @Override
    public List<Appointment> viewPatientAppointments(Long patientId) throws ServiceException {
        try {
            return medicalRepositoryFactory.findByPatientId(patientId);
        } catch (Exception e) {
            throw new ServiceException("Error viewing patient appointments", e);
        }
    }

    @Override
    public void bookAppointment(Appointment appointment) throws ServiceException {
        try {
            medicalRepositoryFactory.save(appointment);
        } catch (Exception e) {
            throw new ServiceException("Error booking appointment", e);
        }
    }

    @Override
    public List<Doctor> viewAvailableDoctors() throws ServiceException { // New method implementation
        try {
            return medicalRepositoryFactory.findAvailableDoctors(); // Assumes a method in DoctorRepository
        } catch (Exception e) {
            throw new ServiceException("Error viewing available doctors", e);
        }
    }
}

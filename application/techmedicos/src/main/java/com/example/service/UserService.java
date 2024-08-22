package com.example.service;

import com.example.exception.ServiceException;
import com.example.models.Appointment;
import com.example.models.Doctor;
import com.example.models.Patient;

import java.util.List;

public interface UserService {
    void registerPatient(Patient patient) throws ServiceException;
    List<Appointment> viewPatientAppointments(int patientId) throws ServiceException;
    void bookAppointment(Appointment appointment) throws ServiceException;
    List<Doctor> viewAvailableDoctors() throws ServiceException; //
}

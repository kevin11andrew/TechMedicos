package com.example.service;

import com.example.models.Appointment;
import com.example.models.Patient;

public interface UserService {
    void registerPatient(Patient patient);
    void bookAppointment(int patientId,int doctorId,String appointmentDate);
    Appointment viewAppointment(int appointmentId);
}

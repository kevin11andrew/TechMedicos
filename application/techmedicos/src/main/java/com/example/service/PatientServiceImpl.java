package com.example.service;

import com.example.exception.DatabaseException;
import com.example.exception.ResourceNotFoundException;
import com.example.models.Appointment;
import com.example.models.Doctor;
import com.example.models.Patient;
import com.example.repository.AppointmentRepository;
import com.example.repository.DoctorRepository;
import com.example.repository.PatientRepository;

import java.sql.SQLException;

public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private AppointmentRepository appointmentRepository;

    public PatientServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void registerPatient(Patient patient) {
        try{
            patientRepository.addPatient(patient);
        }
        catch (SQLException e){
            throw new DatabaseException("Failed to register patient", e);
        }
    }

    @Override
    public void bookAppointment(int patientId, int doctorId, String appointmentDate) {
        Patient patient = patientRepository.findById(patientId);
        if (patient == null) {
            throw new ResourceNotFoundException("Patient not found with ID: " + patientId);
        }
        Doctor doctor = doctorRepository.findById(doctorId);
        if (doctor == null) {
            throw new ResourceNotFoundException("Doctor not found with ID: " + doctorId);
        }
        Appointment appointment = new Appointment(null, doctor, patient, appointmentDate);
        try {
            appointmentRepository.addAppointment(appointment);
        } catch (SQLException e) {
            throw new DatabaseException("Failed to book appointment.", e);
        }
    }

    @Override
    public Appointment viewAppointment(int appointmentId) {
        try {
            return appointmentRepository.findById(appointmentId);
        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch appointment.", e);
        }
    }
}

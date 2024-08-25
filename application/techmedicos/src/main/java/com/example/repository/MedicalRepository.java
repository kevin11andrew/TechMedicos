package com.example.repository;

import com.example.exception.AppointmentDoesNotExistException;
import com.example.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface MedicalRepository {
    ArrayList <Admin> getAdmins();
    ArrayList <Doctor> getDoctors(); //Returns all doctors
    ArrayList <User> getUsers(); //Returns all users
//    ArrayList <Appointment> getAppointment(); //Returns all appointments
    ArrayList <Patient> getPatients(); //Returns all Patients
    int getNextId();
//    ArrayList <> getAllEmployeeIDs();
    Employee employeeValidation(int id, String password);
    void deleteAppointment(int doctorId, int timeSlot, LocalDate date) throws AppointmentDoesNotExistException;
    void deleteAppointmentByID(int appointmentId) throws AppointmentDoesNotExistException;
    boolean registerPatient(Patient patient);
}

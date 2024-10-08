package com.example.repository;

import com.example.exception.AppointmentDoesNotExistException;
import com.example.models.*;

import java.time.LocalDate;
import java.util.ArrayList;

public interface MedicalRepository {
    ArrayList <Admin> getAdmins();
    ArrayList <Doctor> getDoctors(); //Returns all doctors
    ArrayList <User> getUsers(); //Returns all users
//    ArrayList <Appointment> getAppointment(); //Returns all appointments
    ArrayList <Patient> getPatients(); //Returns all Patients
    int getNextId();
//    ArrayList <> getAllEmployeeIDs();

    Admin getAdminById(int id);
    Doctor getDoctorById(int id);
    User getUserById(int id);
    Patient getPatientById(int id);
    Appointment getAppointmentById(int id);
    Employee employeeValidation(int id, String password);
    void deleteAppointment(int doctorId, int timeSlot, LocalDate date) throws AppointmentDoesNotExistException;
    void deleteAppointmentByID(int appointmentId) throws AppointmentDoesNotExistException;
    boolean registerDoctor(Doctor doctor);
    boolean registerPatient(Patient patient);
    boolean isAvailable(int doctorId, int timeSlot, LocalDate date);
    void createAppointment(int userId, int doctorId, int patientId, LocalDate date, int timeSlot, String summary);
    ArrayList<Appointment> getAppointmentsByDate(int doctorId, LocalDate start, LocalDate end);
    void doctorSetSchedule(int doctor_id, LocalDate date, int timeSlot); //To reserve a timeslot -> doctor has to add it to schedule.
    String getReport(int appointment_id);
    void setReport(int appointment_id, String report);
}

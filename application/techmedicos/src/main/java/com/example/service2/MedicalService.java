package com.example.service2;

import com.example.exception.AppointmentDoesNotExistException;
import com.example.models.Appointment;
import com.example.exception.*;
import com.example.models.Doctor;
import com.example.models.Employee;
import com.example.models.Patient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public interface MedicalService {
    Employee login(int id, String password);
    //Save to database - Must Allow user to store file, extract path of that file and load the data in doctor Array and Send it to database.
//    ArrayList <String> showDoctors();
    int registerDoctor(String speciality, String name, long contactNo, String password);
    ArrayList <Doctor> showDoctors();
    void deleteAppointment(int doctorId, int timeSlot, LocalDate date) throws AppointmentDoesNotExistException;
    void deleteAppointmentByID(int appointmentId) throws AppointmentDoesNotExistException;
    ArrayList<Patient> getPatients();
    boolean registerPatient(int userId, String name, int age, long contactNo) throws UserNotFoundException, ServiceException;
    boolean makeAppointment(int userId, int doctorId, int patientId, LocalDate date, int timeSlot, String summary) throws UserNotFoundException, PatientNotFoundException, DoctorNotFoundException, ServiceException;
    ArrayList<Appointment> getAppointmentsByDate(int doctorId, LocalDate start, LocalDate end);
    void doctorSetSchedule(int doctor_id, LocalDate date, int timeSlot);
    String getReport(int appointment_id);
    void setReport(int appointment_id, String report);
}

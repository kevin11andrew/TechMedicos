package com.example.service2;

import com.example.exception.AppointmentDoesNotExistException;
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
    ArrayList <Doctor> showDoctors();
    void deleteAppointment(int doctorId, int timeSlot, LocalDate date) throws AppointmentDoesNotExistException;
    void deleteAppointmentByID(int appointmentId) throws AppointmentDoesNotExistException;
    ArrayList<Patient> getPatients();
    public boolean registerPatient(int userId, String name, int age, long contactNo);
}

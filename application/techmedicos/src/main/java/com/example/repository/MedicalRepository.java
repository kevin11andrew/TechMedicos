package com.example.repository;

import com.example.models.Appointment;
import com.example.models.Doctor;
import com.example.models.Patient;
import com.example.models.User;

import java.util.ArrayList;
import java.util.List;

public interface MedicalRepository {
    ArrayList <Doctor> getDoctors(); //Returns all doctors
    ArrayList <User> getUsers(); //Returns all users
    ArrayList <Appointment> getAppointment(); //Returns all appointments
    ArrayList <Patient> getPatient(); //Returns all Patients
}

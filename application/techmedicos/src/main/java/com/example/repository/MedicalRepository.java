package com.example.repository;

import com.example.models.*;

import java.util.ArrayList;
import java.util.List;

public interface MedicalRepository {
    ArrayList <Admin> getAdmins();
    ArrayList <Doctor> getDoctors(); //Returns all doctors
    ArrayList <User> getUsers(); //Returns all users
//    ArrayList <Appointment> getAppointment(); //Returns all appointments
    ArrayList <Patient> getPatients(); //Returns all Patients
    int getNextId();
}

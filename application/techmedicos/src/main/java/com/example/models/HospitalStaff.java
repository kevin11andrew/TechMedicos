package com.example.models;

import java.util.List;

public class HospitalStaff extends User {

    // Constructor
    public HospitalStaff(int userId, String username, String password) {
        super(userId, username, password, "USER");
    }

    @Override
    public void login() {
        System.out.println("Hospital Staff " + getUsername() + " logged in successfully.");
    }

    // Hospital staff-specific features
    public void registerPatient(Patient patient) {
        System.out.println("Patient Registered: " + patient.getName());
        // LogIC
    }

    public void bookAppointment(Appointment appointment) {
        System.out.println("Appointment booked for Patient: " + appointment.getPatient().getName());
        // Logic to book appointment in the database
    }

    public void viewAppointments(List<Appointment> appointments) {
        System.out.println("Appointments:");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    // FUTURE WORK
}

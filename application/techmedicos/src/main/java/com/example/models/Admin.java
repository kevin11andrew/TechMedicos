package com.example.models;

import java.util.List;

public class Admin extends User {

    // Constructor
    public Admin(int userId, String username, String password) {
        super(userId, username, password, "ADMIN");
    }

    @Override
    public void login() {
        System.out.println("Admin " + getUsername() + " logged in successfully.");
    }

    // Method to import doctors into the system
    public void importDoctors(List<Doctor> doctors) {
        for (Doctor doctor : doctors) {
            System.out.println("Importing Doctor: " + doctor.getUsername());
            // Logic
        }
    }

    // Method to manage doctor schedules
    public void manageDoctorSchedule(Doctor doctor, Schedule schedule) {
        if (doctor != null && schedule != null) {
            doctor.setupSchedule(schedule); // This adds the schedule to the doctor’s list of schedules
            System.out.println("Schedule updated for Doctor: " + doctor.getUsername());
        } else {
            System.out.println("Invalid doctor or schedule provided.");
        }
    }

    // Method to cancel appointments
    public void cancelAppointment(Appointment appointment) {
        if (appointment != null) {
            appointment.setStatus("CANCELLED");
            System.out.println("Appointment cancelled for Patient: " + appointment.getPatient().getName());
        } else {
            System.out.println("Invalid appointment provided.");
        }
    }

    // FUTURE WORK
}
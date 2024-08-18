package com.example.models;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends User {
    private String specialization;
    private List<Schedule> schedules;

    // Constructor
    public Doctor(int userId, String username, String password, String specialization) {
        super(userId, username, password, "DOCTOR");
        this.specialization = specialization;
        this.schedules = new ArrayList<>();
    }

    @Override
    public void login() {
        System.out.println("Doctor " + getUsername() + " logged in successfully.");
    }

    // Doctor features
    public void setupSchedule(Schedule schedule) {
        schedules.add(schedule);
        System.out.println("Schedule added for Doctor: " + getUsername());
    }

    public void viewAppointments(List<Appointment> appointments) {
        System.out.println("Appointments for Doctor: " + getUsername());
        for (Appointment appointment : appointments) {
            if (appointment.getDoctor().equals(this)) {
                System.out.println(appointment);
            }
        }
    }

    public void suggestMedicalTest(Patient patient, String test) {
        System.out.println("Suggested Test for Patient: " + patient.getName() + " - " + test);
    }

    public void suggestMedicines(Patient patient, String medicine) {
        System.out.println("Suggested Medicine for Patient: " + patient.getName() + " - " + medicine);
    }

    // Getters and Setters
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
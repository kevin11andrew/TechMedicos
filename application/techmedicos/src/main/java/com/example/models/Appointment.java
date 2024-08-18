package com.example.models;

public class Appointment {
    private int appointmentId;
    private Patient patient;
    private Doctor doctor;
    private Schedule schedule;
    private String status; // "BOOKED", "CANCELLED", "PENDING"

    // Constructor
    public Appointment(int appointmentId, Patient patient, Doctor doctor, Schedule schedule, String status) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.schedule = schedule;
        this.status = status;
    }

    // Getters and Setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment [appointmentId=" + appointmentId + ", patient=" + patient.getName() +
                ", doctor=" + doctor.getUsername() + ", schedule=" + schedule.getDateTime() +
                ", status=" + status + "]";
    }
}

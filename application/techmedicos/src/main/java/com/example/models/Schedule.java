package com.example.models;

import java.time.LocalDate;

public class Schedule {
    private int doctorId;
    private int scheduleId;
    private LocalDate date;
    private int timeSlot;

    // Constructor
    public Schedule(int doctorId, int scheduleId, LocalDate date, int timeSlot) {
        this.doctorId = doctorId;
        this.scheduleId = scheduleId;
        this.date = date;
        this.timeSlot = timeSlot;
    }

    // Getters and Setters
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(int timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "doctorId=" + doctorId +
                ", scheduleId=" + scheduleId +
                ", date=" + date +
                ", timeSlot='" + timeSlot + '\'' +
                '}';
    }
}

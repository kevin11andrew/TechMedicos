package com.example.model;

import java.util.Date;

public class Schedule {
    private int doctorId;
    private int scheduleId;
    private Date date;
    private String timeSlot;

    public Schedule(int doctorId, int scheduleId, Date date, String timeSlot) {
        this.doctorId = doctorId;
        this.scheduleId = scheduleId;
        this.date = date;
        this.timeSlot = timeSlot;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
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


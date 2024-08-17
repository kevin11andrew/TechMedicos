package com.example.model;

public class Appointment {
    private int appointmentId;
    private int patientId;
    private int doctorId;
    private int userId;
    private int scheduleId;
    private String summary;
    private String report;

    public Appointment(int appointmentId, int patientId, int doctorId, int userId, int scheduleId, String summary, String report) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.summary = summary;
        this.report = report;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", userId=" + userId +
                ", scheduleId=" + scheduleId +
                ", summary='" + summary + '\'' +
                ", report='" + report + '\'' +
                '}';
    }
}


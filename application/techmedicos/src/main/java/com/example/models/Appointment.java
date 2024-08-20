package com.example.models;

public class Appointment {
    private int appointmentId;
    private int doctorId;
    private int patientId;
    private int userId;
    private int scheduleId;
//    not viable at this stage:
//    private AppointmentStatus status;
    private String summary;
    private String report;

    // Constructor
    public Appointment(int appointmentId, int doctorId, int patientId, int userId, int scheduleId, AppointmentStatus status, String summary, String report) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.userId = userId;
        this.scheduleId = scheduleId;
//        this.status = status;
        this.summary = summary;
        this.report = report;
    }

    // Getters and Setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
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

//    public AppointmentStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(AppointmentStatus status) {
//        this.status = status;
//    }

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
                ", doctorId=" + doctorId +
                ", patientId=" + patientId +
                ", userId=" + userId +
                ", scheduleId=" + scheduleId +
//                ", status=" + status +
                ", summary='" + summary + '\'' +
                ", report='" + report + '\'' +
                '}';
    }
}

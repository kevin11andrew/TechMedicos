package com.example.model;

public class Patient {
    private int patientId;
    private String name;
    private String contact;

    public Patient(int patientId, String name, String contact) {
        this.patientId = patientId;
        this.name = name;
        this.contact = contact;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}

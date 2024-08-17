package com.example.model;

public class Doctor {
    private int doctorId;
    private String name;
    private String contact;

    public Doctor(int doctorId, String name, String contact) {
        this.doctorId = doctorId;
        this.name = name;
        this.contact = contact;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}


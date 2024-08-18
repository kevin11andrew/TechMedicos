package com.example.models;

public class Doctor {
    private int doctorId;
    private Speciality speciality;
    private String name;
    private String contactNo;
    private String password;

    // Constructor
    public Doctor(int doctorId, Speciality speciality, String name, String contactNo, String password) {
        this.doctorId = doctorId;
        this.speciality = speciality;
        this.name = name;
        this.contactNo = contactNo;
        this.password = password;
    }

    // Getters and Setters
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", speciality=" + speciality +
                ", name='" + name + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

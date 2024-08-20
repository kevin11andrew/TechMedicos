package com.example.models;

public class Doctor {
    private int doctorId;
//    private Speciality speciality; let's add this in version 2
    private String speciality;
    private String name;
    private long contactNo;
    private String password;

    // Constructor
    public Doctor(int doctorId, String speciality, String name, long contactNo, String password) {
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
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

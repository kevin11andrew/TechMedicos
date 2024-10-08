package com.example.models;

public class Patient {
    private int userId;
    private int patientId;
    private String name;
    private int age;
    private long contactNo;

    // Constructor
    public Patient(int userId, String name, int age, long contactNo) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.contactNo = contactNo;
    }
    public Patient(int userId, int patientId, String name, int age, long contactNo) {
        this.userId = userId;
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.contactNo = contactNo;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "userId=" + userId +
                ", patientId=" + patientId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", contactNo='" + contactNo + '\'' +
                '}';
    }
}

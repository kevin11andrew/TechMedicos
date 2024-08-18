package com.example.models;

public class User {
    private long contactNo;
    private int userId;
    private String name;
    private String password;

    // Constructor
    public User(int userId, String name, long contactNo, String password) {
        this.userId = userId;
        this.name = name;
        this.contactNo = contactNo;
        this.password = password;
    }

    // Getters and Setters
    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "contactNo='" + contactNo + '\'' +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

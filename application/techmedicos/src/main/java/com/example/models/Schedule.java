package com.example.models;

import java.time.LocalDateTime;

public class Schedule {
    private int scheduleId;
    private LocalDateTime dateTime;
    private boolean isAvailable;

    // Constructor
    public Schedule(int scheduleId, LocalDateTime dateTime, boolean isAvailable) {
        this.scheduleId = scheduleId;
        this.dateTime = dateTime;
        this.isAvailable = isAvailable;
    }

    // Getters and Setters
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleId +
                ", dateTime=" + dateTime +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
package com.example.service;
import com.example.models.Doctor;
import com.example.models.Patient;
import com.example.models.Schedule;

import java.util.List;

public interface AdminService {
    void registerDoctor(Doctor doctor);
    void updateDoctorSchedule(int doctorId, List<Schedule> schedule);
    void cancelDoctorAppointment(int doctorId);
    Patient pullPatientReports(int patientId);
}

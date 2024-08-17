package com.example.service;
import com.example.models.Appointment;
import com.example.models.Schedule;

import java.util.List;

public interface DoctorService {
    void addSchedule(int doctorId,List<Schedule> schedule);
    List<Appointment> viewAppointments(int DoctorId);
    void cancelAppointment(int appointmentId);
//    void suggestTreatment(int appointmentId,String treatment);
}

package com.example.service;
import com.example.exception.ServiceException;
import com.example.models.Appointment;
import com.example.models.Patient;
import com.example.models.Schedule;

import java.util.List;

public interface DoctorService {
    void addSchedule(Schedule schedule) throws ServiceException;
    List<Appointment> viewAppointments(int doctorId) throws ServiceException;
    void cancelAppointment(int appointmentId) throws ServiceException;
    Patient viewPatientReport(int patientId) throws ServiceException;
}

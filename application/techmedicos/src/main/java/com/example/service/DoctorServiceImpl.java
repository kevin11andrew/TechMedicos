package com.example.service;

import com.example.exception.DatabaseException;
import com.example.models.Appointment;
import com.example.models.Schedule;
import com.example.repository.AppointmentRepository;
import com.example.repository.ScheduleRepository;

import java.sql.SQLException;
import java.util.List;

public class DoctorServiceImpl implements DoctorService{
    private ScheduleRepository scheduleRepository;
    private AppointmentRepository appointmentRepository;

    public DoctorServiceImpl(ScheduleRepository scheduleRepository, AppointmentRepository appointmentRepository) {
        this.scheduleRepository = scheduleRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void addSchedule(int doctorId, List<Schedule> schedule) {
        try{
            scheduleRepository.updateScheduleForDoctor(doctorId,schedule);
        }
        catch (SQLException e){
            throw new DatabaseException("Failed to update doctor details",e);
        }
    }

    @Override
    public List<Appointment> viewAppointments(int doctorId) {
        try{
            return appointmentRepository.findById(doctorId);
        }
        catch (SQLException e){
            throw new DatabaseException("Failed to fetch Appointments",e);
        }
    }

    @Override
    public void cancelAppointment(int appointmentId) {
        try{
            appointmentRepository.deleteAppointment(appointmentId);
        }
        catch (SQLException e){
            throw new DatabaseException("Failed to cancel appointment" ,e);
        }
    }
}

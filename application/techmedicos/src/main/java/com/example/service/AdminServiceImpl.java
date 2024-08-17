package com.example.service;

import com.example.exception.DatabaseException;
import com.example.exception.ResourceNotFoundException;
import com.example.models.Doctor;
import com.example.models.Patient;
import com.example.models.Schedule;
import com.example.repository.DoctorRepository;
import com.example.repository.PatientRepository;
import com.example.repository.ScheduleRepository;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService{
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private ScheduleRepository scheduleRepository;

    public AdminServiceImpl(DoctorRepository doctorRepository, ScheduleRepository scheduleRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.scheduleRepository = scheduleRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public void registerDoctor(Doctor doctor) {
        try{
            doctorRepository.addDoctor(doctor);
        }
        catch (SQLException e){
            throw new DatabaseException("Failed to load doctor" ,e);
        }
    }

    @Override
    public void updateDoctorSchedule(int doctorId, List<Schedule> schedule) {
        Doctor doctor = doctorRepository.findById(doctorId);
        if(doctor == null){
            throw new ResourceNotFoundException("Doctor not found with id :" + doctorId);
        }
        try{
            scheduleRepository.updateScheduleForDoctor(doctorId,schedule);
        }
        catch (SQLException e){
            throw new DatabaseException("Failed to update doctor schedule",e);
        }
    }

    @Override
    public void cancelDoctorAppointment(int doctorId) {
        try{
            doctorRepository.cancelAppointments(doctorId);
        }
        catch (SQLException e){
            throw new DatabaseException("Failed to cancel doctor Appointment",e);
        }
    }

    @Override
    public Patient pullPatientReports(int patientId) {
        Patient patient = patientRepository.findById(patientId);
        if(patient == null){
            throw new ResourceNotFoundException("Patient not found with id : " + patientId);
        }
        return patient;
    }
}

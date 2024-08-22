package com.example.service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.exception.ServiceException;
import com.example.models.Doctor;
import com.example.models.Patient;
import com.example.models.Schedule;
import com.example.models.User;
import com.example.repository.MedicalRepositoryFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService{
    MedicalRepositoryFactory medicalRepositoryFactory;

    public AdminServiceImpl(MedicalRepositoryFactory medicalRepositoryFactory) {
        this.medicalRepositoryFactory = medicalRepositoryFactory;
    }
    @Override
    public void importDoctors(String filePath) throws Exception {
        //if data is imported from csv file
        List<Doctor> doctors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Doctor doctor = new Doctor(Integer.parseInt(parts[0]),parts[1],parts[2],Integer.parseInt(parts[3]),parts[4]);
                    doctors.add(doctor);
                }
            }
        }
        //if data is imported using json file
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);

        // Parse the JSON file into a list of Doctor objects
        List<Doctor> doctors = objectMapper.readValue(file, new TypeReference<List<Doctor>>() {});

        for (Doctor doctor : doctors) {
            medicalRepositoryFactory.saveDoctor(doctor);
        }

    }

    public void registerDoctor(int id,String name,String speciality,int contact,String password) throws ServiceException {
        try {
            Doctor doctor = new Doctor(id,name,speciality,contact,password);
            medicalRepositoryFactory.save(doctor);
        } catch (Exception e) {
            throw new ServiceException("Error registering doctor", e);
        }
    }

    @Override
    public void updateDoctorSchedule(Schedule schedule) throws ServiceException {
        try {
            medicalRepositoryFactory.updateSchedule(schedule);
        } catch (Exception e) {
            throw new ServiceException("Error updating doctor schedule", e);
        }
    }

    @Override
    public void cancelDoctorAppointment(int appointmentId, LocalDate date,int timeSlot) throws ServiceException {
        try {
            medicalRepositoryFactory.delete(appointmentId,date,timeSlot);
        } catch (Exception e) {
            throw new ServiceException("Error canceling appointment", e);
        }
    }

    @Override
    public void addHospitalStaff(User user) throws ServiceException {
        try {
            medicalRepositoryFactory.save(user);
        } catch (Exception e) {
            throw new ServiceException("Error adding hospital staff", e);
        }
    }

    @Override
    public Patient viewPatientReport(Long patientId) throws ServiceException {
        try {
            return medicalRepositoryFactory.getReportById(patientId);
        } catch (Exception e) {
            throw new ServiceException("Error viewing patient report", e);
        }
    }

    @Override
    public List<Doctor> getAllDoctors() throws ServiceException {
        try {
            return medicalRepositoryFactory.findAll();
        } catch (Exception e) {
            throw new ServiceException("Error fetching all doctors", e);
        }
    }
}

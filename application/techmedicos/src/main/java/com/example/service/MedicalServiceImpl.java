package com.example.service;

import com.example.exception.AppointmentDoesNotExistException;
import com.example.models.Appointment;
import com.example.exception.*;
import com.example.models.Doctor;
import com.example.models.Employee;
import com.example.models.Patient;
import com.example.models.User;
import com.example.repository.MedicalRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicalServiceImpl implements MedicalService{
    private MedicalRepository medicalRepository;
    public MedicalServiceImpl(MedicalRepository medicalRepository){
        this.medicalRepository= medicalRepository;
    }


    @Override
    public Employee login(int id, String password) {
        return medicalRepository.employeeValidation(id, password);
    }

    @Override
    public int registerDoctor(String speciality, String name, long contactNo, String password) {
        //validation required
        int doctorId=medicalRepository.getNextId();
        Doctor doctor = new Doctor(doctorId,speciality,name,contactNo,password);
        medicalRepository.registerDoctor(doctor);
        return doctorId;
    }

//    Should we convert to string array or send it as a list of models???

//    @Override
//    public ArrayList <String> showDoctors() {
//        ArrayList <Doctor> doctors = medicalRepository.getDoctors();
//        ArrayList <String> doctors1 = new ArrayList<>();
//        for(Doctor doctor: doctors){
//            doctors1.add(doctor.toString());
//        }
//        return doctors1;
//    }
    @Override
    public ArrayList <Doctor> showDoctors() {
        return medicalRepository.getDoctors();
    }

    @Override
    public void deleteAppointment(int doctorId, int timeSlot, LocalDate date) throws AppointmentDoesNotExistException {
        medicalRepository.deleteAppointment(doctorId, timeSlot, date);
    }

    @Override
    public void deleteAppointmentByID(int appointmentId) throws AppointmentDoesNotExistException{
        medicalRepository.deleteAppointmentByID(appointmentId);
    }

    @Override
    public ArrayList<Patient> getPatients(){
        return medicalRepository.getPatients();
    }

    @Override
    public boolean registerPatient(int userId, String name, int age, long contactNo) throws UserNotFoundException, ServiceException {
        //Check if User Exists, verify contact No is 10 digits or throw Exception
        User user = medicalRepository.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }

        // Verify contact No is 10 digits
        if (String.valueOf(contactNo).length() != 10) {
            throw new ServiceException("Contact number must be 10 digits.");
        }

        // Create and return the Patient object
        Patient patient = new Patient(userId, name, age, contactNo);
        return medicalRepository.registerPatient(patient);
    }

    @Override
    public boolean makeAppointment(int userId, int doctorId, int patientId, LocalDate date, int timeSlot, String summary) throws Exception {
//        check if patient exists and timeslot is within 0-16
//        verify if doctor, user and patient exists
//        if yes, add to schedule and appointment
        User user = medicalRepository.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }

        // Verify if patient exists
        Patient patient = medicalRepository.getPatientById(patientId);
        if (patient == null) {
            throw new PatientNotFoundException("Patient with ID " + patientId + " not found.");
        }

        // Verify if doctor exists
        Doctor doctor = medicalRepository.getDoctorById(doctorId);
        if (doctor == null) {
            throw new DoctorNotFoundException("Doctor with ID " + doctorId + " not found.");
        }

        // Check if timeSlot is within 0-16
        if (timeSlot < 0 || timeSlot > 16) {
            throw new ServiceException("Time slot must be between 0 and 16.");
        }

        // Check if doctor is available
        boolean isAvailable = medicalRepository.isAvailable(doctorId, timeSlot, date);
        if (isAvailable) {
             medicalRepository.createAppointment(userId, doctorId, patientId, date, timeSlot, summary);
        }
        return false;

    }

    @Override
    public ArrayList<Appointment> getAppointmentsByDate(int doctor_id, LocalDate start, LocalDate end) throws DoctorNotFoundException, ServiceException {
        Doctor doctor = medicalRepository.getDoctorById(doctor_id);
        if (doctor == null) {
            throw new DoctorNotFoundException("Doctor ID does not exist.");
        }
        // Validation: Ensure start and end dates are not null and valid
        if (start == null || end == null) {
            throw new ServiceException("Start and end dates must not be null.");
        }
        if (end.isBefore(start)) {
            throw new ServiceException("End date must not be before the start date.");
        }

        return medicalRepository.getAppointmentsByDate(doctor_id, start, end);
    }

    @Override
    public void doctorSetSchedule(int doctor_id, LocalDate date, int timeSlot) throws DoctorNotFoundException, ServiceException {
        Doctor doctor = medicalRepository.getDoctorById(doctor_id);
        if (doctor == null) {
            throw new DoctorNotFoundException("Doctor ID does not exist.");
        }
        // Validation: Ensure date is not null and timeSlot is valid
        if (date == null) {
            throw new ServiceException("Date must not be null.");
        }
        if (timeSlot < 0 || timeSlot > 16) { // Assuming timeSlot is an hour of the day (0-23)
            throw new ServiceException("Time slot must be between 0 and 23.");
        }

        medicalRepository.doctorSetSchedule(doctor_id, date, timeSlot);

    }

    @Override
    public String getReport(int appointment_id) {
        Appointment appointment = medicalRepository.getAppointmentById(appointment_id);
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment ID does not exist.");
        }

        return medicalRepository.getReport(appointment_id);
    }

    @Override
    public void setReport(int appointment_id, String report) {
        Appointment appointment = medicalRepository.getAppointmentById(appointment_id);
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment ID does not exist.");
        }
        // Validation: Ensure report is not null or empty
        if (report == null || report.trim().isEmpty()) {
            throw new IllegalArgumentException("Report must not be null or empty.");
        }

        medicalRepository.setReport(appointment_id, report);
    }
    @Override
    public void importDoctors(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);

        // Parse the JSON file into a list of Doctor objects
        List<Doctor> doctors = objectMapper.readValue(file, new TypeReference<List<Doctor>>() {});

        for (Doctor doctor : doctors) {
            medicalRepository.registerDoctor(doctor);
        }
    }

}

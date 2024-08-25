package com.example.service2;

import com.example.exception.*;
import com.example.models.Doctor;
import com.example.models.Employee;
import com.example.models.Patient;
import com.example.models.User;
import com.example.repository.MedicalRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
        List<User> users = medicalRepository.getUsers();

        // Check if the user exists
        boolean userExists = false;
        for (User user : users) {
            if (user.getUserId() == userId) {
                userExists = true;
                break;
            }
        }

        if (!userExists) {
            throw new UserNotFoundException("User with ID " + userId + " does not exist.");
        }

        // Verify contact number is 10 digits
        if (String.valueOf(contactNo).length() != 10) {
            throw new ServiceException("Contact number must be exactly 10 digits.");
        }

        Patient patient=new Patient(userId, name, age, contactNo);
        return medicalRepository.registerPatient(patient);
    }

    @Override
    public boolean makeAppointment(int userId, int doctorId, int patientId, LocalDate date, int timeSlot, String summary) throws UserNotFoundException, PatientNotFoundException, DoctorNotFoundException, ServiceException {
//        check if patient exists and timeslot is within 0-16
//        verify if doctor, user and patient exists
//        check if doctor is available
//        if yes, add to schedule and appointment
        List<User> users = medicalRepository.getUsers();
        List<Patient> patients = medicalRepository.getPatients();
        List<Doctor> doctors = medicalRepository.getDoctors();

        // Verify if the user exists
        boolean userExists = users.stream().anyMatch(user -> user.getUserId() == userId);
        if (!userExists) {
            throw new UserNotFoundException("User with ID " + userId + " does not exist.");
        }

        // Verify if the patient exists
        boolean patientExists = patients.stream().anyMatch(patient -> patient.getPatientId() == patientId);
        if (!patientExists) {
            throw new PatientNotFoundException("Patient with ID " + patientId + " does not exist.");
        }

        // Verify if the doctor exists
        boolean doctorExists = doctors.stream().anyMatch(doctor -> doctor.getDoctorId() == doctorId);
        if (!doctorExists) {
            throw new DoctorNotFoundException("Doctor with ID " + doctorId + " does not exist.");
        }

        // Verify if the time slot is within the valid range (0-16)
        if (timeSlot < 0 || timeSlot > 16) {
            throw new ServiceException("Invalid time slot. Time slot must be between 0 and 16.");
        }

        boolean isAvailable=medicalRepository.isAvailable(doctorId,timeSlot,date);
        if(isAvailable){
            medicalRepository.createAppointment(userId, doctorId, patientId, date, timeSlot, summary);
        }
        return false;


    }


}

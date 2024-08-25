package com.example;

import com.example.exception.*;
import com.example.models.Doctor;
import com.example.models.Patient;
import com.example.repository.MedicalRepository;
import com.example.repository.MedicalRepositoryFactory;
import com.example.service.MedicalService;
import com.example.service.MedicalServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
//        MySqlConnectionFactory mySqlConnectionFactory = new MySqlConnectionFactory();
//        try {
//            Connection connection = getConnection();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }


        MedicalRepository medicalRepository = MedicalRepositoryFactory.getMedicalRepository("mysql");
        MedicalService medicalService = new MedicalServiceImpl(medicalRepository);
//        medicalRepository.getDoctors();
//        medicalRepository.getUsers();
//        medicalRepository.getPatients();
//        medicalRepository.getNextId();

//      LOGIN:
        int id =20000007;
        String pwd="docpass7";
        System.out.println(medicalService.login(id,pwd)); // Prints the enum of employee type that has logged in, else returns null

//      ---------------------------------------------------------------------------------------------------------------------------

//      UPLOAD XML/JSON FILE OF DOCTORS

//      ---------------------------------------------------------------------------------------------------------------------------
//      CANCEL APPOINTMENTS:
//      GET ALL DOCTORS
//        ArrayList <String> doctors= medicalService.showDoctors();
//        for (String doctor: doctors){
//            System.out.println(doctor);
//        }
        ArrayList <Doctor> doctors = medicalService.showDoctors();
        for(Doctor doctor:doctors){
            System.out.println(doctor);
        }
        LocalDate localDate = LocalDate.of(2024,8,20);
        System.out.println(localDate);
        try {
            medicalService.deleteAppointment(20000006,11,localDate);
        } catch (AppointmentDoesNotExistException e) {
            e.printStackTrace();
        }
        try{
           medicalService.deleteAppointmentByID(5);
        } catch (AppointmentDoesNotExistException e) {
            e.printStackTrace();
        }

//      ---------------------------------------------------------------------------------------------------------------------------
//      GET PATIENT DATA AND ALLOW FILTERS
        ArrayList<Patient> patients = medicalService.getPatients();
        for(Patient patient: patients){
            System.out.println(patient);
        }
//      HOW TO ENABLE FILTERS?
//      ---------------------------------------------------------------------------------------------------------------------------
//      USER SHOULD BE ABLE TO ADD PATIENT
//      PASS userId and patient details
        try {
            medicalService.registerPatient(10000001,"temp",45, 1234567890);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

//      ---------------------------------------------------------------------------------------------------------------------------
//        CREATE APPOINTMENT:
        localDate = LocalDate.of(2024,8,19);
        try {
            medicalService.makeAppointment(10000001,20000003,1,localDate,10, "HE IS SICK");
        } catch (UserNotFoundException e) {
            System.out.println("Invalid User");;
        } catch (PatientNotFoundException e) {
            System.out.println("Invalid Patient");;
        } catch (DoctorNotFoundException e) {
            System.out.println("Invalid Doctor");;
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//      ---------------------------------------------------------------------------------------------------------------------------

//        GET ADMIN BY ID
        System.out.println(medicalRepository.getAdminById(30000001));
        System.out.println(medicalRepository.getDoctorById(20000006));
        System.out.println(medicalRepository.getUserById(10000006));
        System.out.println(medicalRepository.getPatientById(1));
        System.out.println(medicalRepository.getAppointmentById(0));
//      ---------------------------------------------------------------------------------------------------------------------------
//        VIEW APPOINTMENTS
        try {
            System.out.println(medicalService.getAppointmentsByDate(20000003, LocalDate.of(2024,1,1),LocalDate.of(2025,1,1)));
        } catch (DoctorNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
//      ---------------------------------------------------------------------------------------------------------------------------
//        ADD SCHEDULE
        try {
            medicalService.doctorSetSchedule(20000003,LocalDate.of(2025,1,1),5);
        } catch (DoctorNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
//      --------------------------------------------------------------------------------------------------------------------------
//        FILE REPORT -> SUBMIT MEDICINES AND MEDICAL TESTS
        System.out.println(medicalService.getReport(3));
        medicalService.setReport(8,"take crocin");
//      --------------------------------------------------------------------------------------------------------------------------
//        Register a doctor
        System.out.println(medicalService.registerDoctor("Surgeon","Vishnu", 9874651320L,"fefjyfwbky2"));
    }
}
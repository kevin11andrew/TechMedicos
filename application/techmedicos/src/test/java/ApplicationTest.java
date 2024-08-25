//package com.example;

import com.example.exception.AppointmentDoesNotExistException;
import com.example.exception.ServiceException;
import com.example.exception.UserNotFoundException;
import com.example.models.Appointment;
import com.example.models.Doctor;
import com.example.models.Employee;
import com.example.models.Patient;
import com.example.repository.MedicalRepository;
import com.example.repository.MedicalRepositoryFactory;
import com.example.service.MedicalService;
import com.example.service.MedicalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ApplicationTest {
    private MedicalRepository medicalRepository;
    private MedicalService medicalService;

    @BeforeEach
    void setUp() {
        medicalRepository = MedicalRepositoryFactory.getMedicalRepository("mysql");
        medicalService = new MedicalServiceImpl(medicalRepository);
    }

    @Test
    void testLogin() {
        int id = 20000007;
        String pwd = "docpass7";
        assertEquals(Employee.DOCTOR, medicalService.login(id, pwd));
    }

    @Test
    void testShowDoctors() {
        List<Doctor> doctors =medicalService.showDoctors();
        assertEquals(8, doctors.size());
    }

    @Test
    void testDeleteAppointment() {
        LocalDate date = LocalDate.of(2024, 8, 19);
        try {
            medicalService.makeAppointment(10000001,20000004,5,date,5,"Headache");
            Appointment appointment=medicalService.getAppointmentsByDate(20000004,date,date).get(0);
            System.out.println(appointment);
            assertEquals(10000001,appointment.getUserId());
            assertEquals(20000004,appointment.getDoctorId());
            assertEquals(5,appointment.getPatientId());
            medicalService.deleteAppointment(20000004, 5, date);
            appointment=medicalRepository.getAppointmentById(9);
            assertEquals(null,appointment);
        } catch (AppointmentDoesNotExistException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void testGetPatients() {
        List<Patient> patients = medicalService.getPatients();
        assertEquals(41, patients.size());
    }

    @Test
    void testRegisterPatient() {
        try {
            medicalService.registerPatient(10000001,"Alex", 5, 1237418529);
            ArrayList <Patient> patients=medicalService.getPatients();
            Patient temp=null;
            for(Patient patient: patients){
                if(patient.getUserId()==10000001 && patient.getName().equals("Alex") && patient.getAge()==5 && patient.getContactNo()==1237418529){
                    temp=patient;
                }
            }
            assertEquals(10000001, temp.getUserId());
            assertEquals("Alex", temp.getName());
            assertEquals(5, temp.getAge());
            assertEquals(1237418529, temp.getContactNo());
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
}

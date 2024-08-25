//package com.example;

import com.example.exception.AppointmentDoesNotExistException;
import com.example.exception.ServiceException;
import com.example.exception.UserNotFoundException;
import com.example.models.Doctor;
import com.example.models.Employee;
import com.example.models.Patient;
import com.example.repository.MedicalRepository;
import com.example.repository.MedicalRepositoryFactory;
import com.example.service2.MedicalService;
import com.example.service2.MedicalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        List<Doctor> doctors = new ArrayList<>();
        medicalService.doctors.add(new Doctor(1, "Cardiology","John Doe", 7418529630L,"23rwefsdvx"));
        doctors.add(new Doctor(2,  "Neurology","Jane Doe", 8974561230L, "q2ewadsc"));

        List<Doctor> doctors = medicalService.showDoctors();
        assertEquals(2, doctors.size());
        assertEquals("John Doe", doctors.get(0).getName());
        assertEquals("Jane Doe", doctors.get(1).getName());

        verify(medicalRepository).getDoctors();
    }

    @Test
    void testDeleteAppointment() {
        LocalDate localDate = LocalDate.of(2024, 8, 20);

        try {
            doNothing().when(medicalRepository).deleteAppointment(20000006, 11, localDate);
        } catch (AppointmentDoesNotExistException e) {
            throw new RuntimeException(e);
        }

        assertDoesNotThrow(() -> medicalService.deleteAppointment(20000006, 11, localDate));

        try {
            verify(medicalRepository).deleteAppointment(20000006, 11, localDate);
        } catch (AppointmentDoesNotExistException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testDeleteAppointmentByID() {
        try {
            doNothing().when(medicalRepository).deleteAppointmentByID(5);
        } catch (AppointmentDoesNotExistException e) {
            throw new RuntimeException(e);
        }

        assertDoesNotThrow(() -> medicalService.deleteAppointmentByID(5));

        try {
            verify(medicalRepository).deleteAppointmentByID(5);
        } catch (AppointmentDoesNotExistException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetPatients() {
        List<Patient> mockPatients = new ArrayList<>();
        mockPatients.add(new Patient(1, "Alice", 30, 1234567890));
        mockPatients.add(new Patient(2, "Bob", 40, 1876543210));

//        when(medicalRepository.getPatients()).thenReturn(mockPatients);

        List<Patient> patients = medicalService.getPatients();
        assertEquals(2, patients.size());
        assertEquals("Alice", patients.get(0).getName());
        assertEquals("Bob", patients.get(1).getName());

        verify(medicalRepository).getPatients();
    }

    @Test
    void testRegisterPatient() {
        try {
            doNothing().when(medicalService).registerPatient(10000001, "temp", 45, 1234567890);
        } catch (UserNotFoundException e) {
            System.out.println("User not fount");;
        } catch (ServiceException e) {
            System.out.println("Service Layer Exception");
        }

        assertDoesNotThrow(() -> medicalService.registerPatient(10000001, "temp", 45, 1234567890));

        try {
            verify(medicalService).registerPatient(10000001, "temp", 45, 1234567890);
        } catch (UserNotFoundException e) {
            System.out.println("User not found");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}

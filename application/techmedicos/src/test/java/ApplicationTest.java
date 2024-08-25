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
            Appointment appointment=medicalRepository.getAppointmentById(3);
            assertEquals(3, appointment.getAppointmentId());
            medicalService.deleteAppointment(20000003, 11, date);
            appointment=medicalRepository.getAppointmentById(3);
            assertEquals(null,appointment);
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

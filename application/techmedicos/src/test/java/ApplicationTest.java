package com.example;

import com.example.exception.AppointmentDoesNotExistException;
import com.example.models.Doctor;
import com.example.models.Patient;
import com.example.repository.MedicalRepository;
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
        medicalRepository = mock(MedicalRepository.class);
        medicalService = new MedicalServiceImpl(medicalRepository);
    }

    @Test
    void testLogin() {
        int id = 20000007;
        String pwd = "docpass7";

        when(medicalRepository.login(id, pwd)).thenReturn("Doctor"); // Assume "Doctor" is returned for successful login
        assertEquals("Doctor", medicalService.login(id, pwd));
        verify(medicalRepository).login(id, pwd);
    }

    @Test
    void testShowDoctors() {
        List<Doctor> mockDoctors = new ArrayList<>();
        mockDoctors.add(new Doctor(1, "John Doe", "Cardiology"));
        mockDoctors.add(new Doctor(2, "Jane Doe", "Neurology"));

        when(medicalRepository.getDoctors()).thenReturn(mockDoctors);

        List<Doctor> doctors = medicalService.showDoctors();
        assertEquals(2, doctors.size());
        assertEquals("John Doe", doctors.get(0).getName());
        assertEquals("Jane Doe", doctors.get(1).getName());

        verify(medicalRepository).getDoctors();
    }

    @Test
    void testDeleteAppointment() {
        LocalDate localDate = LocalDate.of(2024, 8, 20);

        doNothing().when(medicalRepository).deleteAppointment(20000006, 11, localDate);

        assertDoesNotThrow(() -> medicalService.deleteAppointment(20000006, 11, localDate));

        verify(medicalRepository).deleteAppointment(20000006, 11, localDate);
    }

    @Test
    void testDeleteAppointmentByID() {
        doNothing().when(medicalRepository).deleteAppointmentByID(5);

        assertDoesNotThrow(() -> medicalService.deleteAppointmentByID(5));

        verify(medicalRepository).deleteAppointmentByID(5);
    }

    @Test
    void testGetPatients() {
        List<Patient> mockPatients = new ArrayList<>();
        mockPatients.add(new Patient(1, "Alice", 30, 1234567890));
        mockPatients.add(new Patient(2, "Bob", 40, 9876543210));

        when(medicalRepository.getPatients()).thenReturn(mockPatients);

        List<Patient> patients = medicalService.getPatients();
        assertEquals(2, patients.size());
        assertEquals("Alice", patients.get(0).getName());
        assertEquals("Bob", patients.get(1).getName());

        verify(medicalRepository).getPatients();
    }

    @Test
    void testRegisterPatient() {
        doNothing().when(medicalRepository).registerPatient(10000001, "temp", 45, 1234567890);

        assertDoesNotThrow(() -> medicalService.registerPatient(10000001, "temp", 45, 1234567890));

        verify(medicalRepository).registerPatient(10000001, "temp", 45, 1234567890);
    }
}

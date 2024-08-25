//package com.example;

import com.example.exception.AppointmentDoesNotExistException;
import com.example.exception.DoctorNotFoundException;
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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
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
            System.out.println("User not fount");
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
    @Test
    void testMakeAppointment() throws Exception {
        Patient mockPatient = new Patient(1, "Alice", 30, 1234567890L);
        Doctor mockDoctor = new Doctor(1, "Cardiology","John Doe", 7418529630L,"23rwefsdvx");


        when(medicalRepository.getPatientById(1)).thenReturn(mockPatient);
        when(medicalRepository.getDoctorById(1)).thenReturn(mockDoctor);
        when(medicalRepository.isAvailable(1, 10, LocalDate.of(2023, 8, 30))).thenReturn(true);

        boolean result = medicalService.makeAppointment(1, 1, 1, LocalDate.of(2023, 8, 30), 10, "Checkup");
        assertTrue(result);

        verify(medicalRepository).createAppointment(anyInt(), anyInt(), anyInt(), any(LocalDate.class), anyInt(), anyString());
    }
    @Test
    void testImportDoctors() throws IOException {
        String filePath = "doctors.json";
        List<Doctor> mockDoctors = new ArrayList<>();
        mockDoctors.add(new Doctor(1, "Cardiology","John Doe", 7418529630L,"23rwefsdvx"));
        mockDoctors.add(new Doctor(2,  "Neurology","Jane Doe", 8974561230L, "q2ewadsc"));

        ObjectMapper objectMapper = mock(ObjectMapper.class);
        when(objectMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(mockDoctors);

        medicalService.importDoctors(filePath);

        verify(medicalRepository, times(2)).registerDoctor(any(Doctor.class));
    }

    @Test
    void testGetAppointmentsByDate() throws DoctorNotFoundException, ServiceException {
        Doctor mockDoctor = new Doctor(1, "Cardiology","John Doe", 7418529630L,"23rwefsdvx");
        when(medicalRepository.getDoctorById(1)).thenReturn(mockDoctor);

        List<Appointment> mockAppointments = new ArrayList<>();
        mockAppointments.add(new Appointment(1, 1, 1,1,1, "patient is sick", "Checkup"));

        when(medicalRepository.getAppointmentsByDate(1, LocalDate.of(2023, 8, 28), LocalDate.of(2023, 8, 30))).thenReturn(mockAppointments);

        List<Appointment> appointments = medicalService.getAppointmentsByDate(1, LocalDate.of(2023, 8, 28), LocalDate.of(2023, 8, 30));
        assertEquals(1, appointments.size());
        assertEquals("Checkup", appointments.get(0).getSummary());

        verify(medicalRepository).getAppointmentsByDate(1, LocalDate.of(2023, 8, 28), LocalDate.of(2023, 8, 30));
    }
    @Test
    void testDoctorSetSchedule() throws DoctorNotFoundException, ServiceException {
        Doctor mockDoctor = new Doctor(1, "Cardiology","John Doe", 7418529630L,"23rwefsdvx");
        when(medicalRepository.getDoctorById(1)).thenReturn(mockDoctor);

        medicalService.doctorSetSchedule(1, LocalDate.of(2023, 8, 30), 10);
        verify(medicalRepository).doctorSetSchedule(1, LocalDate.of(2023, 8, 30), 10);
    }

    @Test
    void testGetReport() {
        Appointment mockAppointment = new Appointment(1, 1, 1,1,1, "patient is sick", "Checkup");
        when(medicalRepository.getAppointmentById(1)).thenReturn(mockAppointment);

        String report = "Patient is healthy.";
        when(medicalRepository.getReport(1)).thenReturn(report);

        assertEquals(report, medicalService.getReport(1));
        verify(medicalRepository).getReport(1);
    }

    @Test
    void testSetReport() {
        Appointment mockAppointment = new Appointment(1, 1, 1,1,1, "patient is sick", "Checkup");
        when(medicalRepository.getAppointmentById(1)).thenReturn(mockAppointment);

        String report = "Patient is healthy.";
        medicalService.setReport(1, report);
        verify(medicalRepository).setReport(1, report);
    }
}

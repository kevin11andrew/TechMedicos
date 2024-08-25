package com.example.service;

import com.example.exception.ServiceException;
import com.example.models.Patient;
import com.example.models.Schedule;
import com.example.models.Appointment;
import com.example.repository.MedicalRepositoryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // Use an in-memory database
@Transactional
class DoctorServiceImplTest {

    @Autowired
    private DoctorServiceImpl doctorService;

    @Autowired
    private MedicalRepositoryFactory medicalRepositoryFactory; // Assuming it's a Spring bean

    private int testDoctorId;
    private int testAppointmentId;
    private int testPatientId;

    @BeforeEach
    void setUp() throws ServiceException {
        // Initialize test data
        testDoctorId = 1; // This will be generated dynamically
        testPatientId = 1; // This will be generated dynamically
        testAppointmentId = 1; // This will be generated dynamically

        // Insert a doctor into the database
        Schedule schedule = new Schedule();
        schedule.setDoctorId(testDoctorId);
        // Set other properties of Schedule

        medicalRepositoryFactory.save(schedule);

        // Insert a patient into the database
        Patient patient = new Patient();
        patient.setId(testPatientId);
        // Set other properties of Patient

        medicalRepositoryFactory.save(patient);

        // Insert an appointment into the database
        Appointment appointment = new Appointment();
        appointment.setId(testAppointmentId);
        appointment.setDoctorId(testDoctorId);
        appointment.setPatientId(testPatientId);
        appointment.setScheduleId(schedule.getId());
        // Set other properties of Appointment

        medicalRepositoryFactory.save(appointment);
    }

    @Test
    void testAddSchedule() throws ServiceException {
        // Prepare
        Schedule schedule = new Schedule();
        schedule.setDoctorId(testDoctorId);
        // Set other properties of Schedule

        // Execute
        doctorService.addSchedule(schedule);

        // Verify
        Optional<Schedule> result = medicalRepositoryFactory.findScheduleById(schedule.getId());
        assertTrue(result.isPresent());
        assertEquals(schedule, result.get());
    }

    @Test
    void testViewAppointments() throws ServiceException {
        // Execute
        List<Appointment> result = doctorService.viewAppointments(testDoctorId);

        // Verify
        Optional<Appointment> appointmentResult = medicalRepositoryFactory.findAppointmentById(testAppointmentId);
        assertTrue(result.containsAll(List.of(appointmentResult.orElseThrow())));
        assertEquals(1, result.size());
    }

    @Test
    void testCancelAppointment() throws ServiceException {
        // Execute
        doctorService.cancelAppointment(testAppointmentId);

        // Verify
        Optional<Appointment> result = medicalRepositoryFactory.findAppointmentById(testAppointmentId);
        assertFalse(result.isPresent());
    }

    @Test
    void testViewPatientReport() throws ServiceException {
        // Execute
        Patient result = doctorService.viewPatientReport(testPatientId);

        // Verify
        Optional<Patient> patientResult = medicalRepositoryFactory.findPatientById(testPatientId);
        assertTrue(patientResult.isPresent());
        assertEquals(patientResult.get(), result);
    }
}

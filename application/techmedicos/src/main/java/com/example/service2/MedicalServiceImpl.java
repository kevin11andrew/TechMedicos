package com.example.service2;

import com.example.exception.AppointmentDoesNotExistException;
import com.example.models.Appointment;
import com.example.models.Doctor;
import com.example.models.Employee;
import com.example.models.Patient;
import com.example.repository.MedicalRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class MedicalServiceImpl implements MedicalService {
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
    public boolean registerPatient(int userId, String name, int age, long contactNo) {
        //Check if User Exists, verify contact No is 10 digits or throw Exception
        Patient patient=new Patient(userId, name, age, contactNo);
        return medicalRepository.registerPatient(patient);
    }

    @Override
    public boolean makeAppointment(int userId, int doctorId, int patientId, LocalDate date, int timeSlot, String summary) {
//        check if patient exists and timeslot is within 0-16
//        verify if doctor, user and patient exists
//        if yes, add to schedule and appointment
        boolean isAvailable=medicalRepository.isAvailable(doctorId,timeSlot,date);
        if(isAvailable){
            medicalRepository.createAppointment(userId, doctorId, patientId, date, timeSlot, summary);
        }
        return false;


    }

    @Override
    public ArrayList<Appointment> getAppointmentsByDate(int doctorId, LocalDate start, LocalDate end) {
        return medicalRepository.getAppointmentsByDate(doctorId,start,end);
    }

    @Override
    public void doctorSetSchedule(int doctor_id, LocalDate date, int timeSlot) {
        medicalRepository.doctorSetSchedule(doctor_id,date,timeSlot);
    }

    @Override
    public String getReport(int appointment_id) {
        return medicalRepository.getReport(appointment_id);
    }

    @Override
    public void setReport(int appointment_id, String report) {
        medicalRepository.setReport(appointment_id,report);
    }


}

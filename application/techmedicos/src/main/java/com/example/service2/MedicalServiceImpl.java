package com.example.service2;

import com.example.exception.AppointmentDoesNotExistException;
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


}

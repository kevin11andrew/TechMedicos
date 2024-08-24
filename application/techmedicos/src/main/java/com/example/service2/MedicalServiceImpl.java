package com.example.service2;

import com.example.models.Doctor;
import com.example.models.Employee;
import com.example.repository.MedicalRepository;

import java.util.ArrayList;

public class MedicalServiceImpl implements MedicalService {
    private MedicalRepository medicalRepository;
    public MedicalServiceImpl(MedicalRepository medicalRepository){
        this.medicalRepository= medicalRepository;
    }


    @Override
    public Employee login(int id, String password) {
        return medicalRepository.employeeValidation(id, password);
    }

    @Override
    public ArrayList <String> showDoctors() {
        ArrayList <Doctor> doctors = medicalRepository.getDoctors();
        ArrayList <String> doctors1 = new ArrayList<>();
        for(Doctor doctor: doctors){
            doctors1.add(doctor.toString());
        }
        return doctors1;
    }
}

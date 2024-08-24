package com.example;

import com.example.database.MySqlConnectionFactory;
import com.example.models.Admin;
import com.example.repository.MedicalRepository;
import com.example.repository.MySqlMedicalRepository;
import com.example.service2.MedicalService;
import com.example.service2.MedicalServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.example.database.MySqlConnectionFactory.getConnection;

public class Application {
    public static void main(String[] args) {
//        MySqlConnectionFactory mySqlConnectionFactory = new MySqlConnectionFactory();
//        try {
//            Connection connection = getConnection();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }


        MedicalRepository medicalRepository = new MySqlMedicalRepository();
        MedicalService medicalService = new MedicalServiceImpl(medicalRepository);
//        medicalRepository.getDoctors();
//        medicalRepository.getUsers();
//        medicalRepository.getPatients();
//        medicalRepository.getNextId();

//      LOGIN:
        int id =20000007;
        String pwd="docpass7";
        System.out.println(medicalService.login(id,pwd)); // Prints the enum of employee type that has logged in, else returns null

//      UPLOAD XML/JSON FILE OF DOCTORS

//      GET ALL DOCTORS
        ArrayList <String> doctors= medicalService.showDoctors();
        for (String doctor: doctors){
            System.out.println(doctor);
        }
    }
}
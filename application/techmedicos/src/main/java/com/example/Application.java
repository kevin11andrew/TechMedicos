package com.example;

import com.example.database.MySqlConnectionFactory;
import com.example.models.Admin;
import com.example.repository.MedicalRepository;
import com.example.repository.MySqlMedicalRepository;

import java.sql.Connection;
import java.sql.SQLException;

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
//        medicalRepository.getDoctors();
//        medicalRepository.getUsers();
//        medicalRepository.getPatients();
        medicalRepository.getNextId();
        //
    }
}
package com.example.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class MySqlConnectionFactory {
    private static Properties properties = new Properties();
    static {
        Connection connection = null;
        try {
            FileInputStream fis = new FileInputStream("/home/kevin1_1andrew/Desktop/HSBC TRAINING/CODE FURY/TechMedicos/application/techmedicos/src/main/resources/database.properties");
            properties.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            connection=getConnection();
            PreparedStatement statement;
            ArrayList<String> sql = new ArrayList<>();

            sql.add("CREATE TABLE IF NOT EXISTS users (user_id INT(8) PRIMARY KEY, name VARCHAR(20), password VARCHAR(16), contact_no BIGINT(10));");
            sql.add("CREATE TABLE IF NOT EXISTS doctors (doctor_id INT(8) PRIMARY KEY, name VARCHAR(20), password VARCHAR(16), contact_no BIGINT(10), speciality varchar(20));");
            sql.add("CREATE TABLE IF NOT EXISTS admin (admin_id INT(8) PRIMARY KEY, password VARCHAR(16));");
            sql.add("CREATE TABLE IF NOT EXISTS patients (patient_id INT(8) PRIMARY KEY, name VARCHAR(20), age INT, contact_no BIGINT(10), user_id INT(8), " +
                    "FOREIGN KEY (user_id) REFERENCES users(user_id)" +
                    ");"); //user_id is set to null if user(i.e. hospital staff) is deleted
            sql.add("CREATE TABLE IF NOT EXISTS schedule (schedule_id INT(8) PRIMARY KEY, date DATE, timeslot INT, doctor_id INT(8), " +
                    "FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");");
            sql.add("CREATE TABLE IF NOT EXISTS appointments (appointment_id INT(8) PRIMARY KEY, patient_id INT(8), doctor_id INT(8), user_id INT(8), schedule_id INT(8), summary VARCHAR(1000), report VARCHAR(1000)," +
                    "FOREIGN KEY(patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE ON UPDATE CASCADE," +
                    "FOREIGN KEY(doctor_id) REFERENCES doctors(doctor_id) ON DELETE CASCADE ON UPDATE CASCADE," +
                    "FOREIGN KEY(user_id) REFERENCES users(user_id)," +
                    "FOREIGN KEY(schedule_id) REFERENCES schedule(schedule_id) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");");

            for(String query: sql){
//                System.out.println(query);
                statement=connection.prepareStatement(query);
                statement.executeUpdate();
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = properties.getProperty("database.url");
        String user = properties.getProperty("database.user");
        String password = properties.getProperty("database.password");
        System.out.println("'"+url+"' '"+user+"' '"+password+"'");
        return DriverManager.getConnection(url, user, password);
    }
}

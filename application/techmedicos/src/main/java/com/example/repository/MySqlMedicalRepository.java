package com.example.repository;

import com.example.database.MySqlConnectionFactory;
import com.example.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MySqlMedicalRepository implements MedicalRepository {
    Connection connection=null;

    static{
        try {
            Class.forName("com.example.database.MySqlConnectionFactory");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e); // Class not found
        }
    }
    @Override
    public ArrayList<Admin> getAdmins() {
        ArrayList<Admin> admins = new ArrayList<>();
        try {
            connection= MySqlConnectionFactory.getConnection();
            String sql= "SELECT * FROM admin;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                admins.add(new Admin(resultSet.getInt("admin_id"), resultSet.getString("password")));
            }
            return admins;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Doctor> getDoctors() {
        ArrayList<Doctor> doctors = new ArrayList<>();
        try {
            connection= MySqlConnectionFactory.getConnection();
            String sql= "SELECT * FROM doctors;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                doctors.add(new Doctor(resultSet.getInt("doctor_id"), resultSet.getString("speciality"), resultSet.getString("name"), resultSet.getLong("contact_no"), resultSet.getString("password")));
            }
            return doctors;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            connection= MySqlConnectionFactory.getConnection();
            String sql= "SELECT * FROM users;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                users.add(new User(resultSet.getInt("user_id"), resultSet.getString("name"), resultSet.getLong("contact_no"), resultSet.getString("password")));
            }
            System.out.println(users);
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Patient> getPatients() {
        ArrayList<Patient> patients = new ArrayList<>();
        try {
            connection= MySqlConnectionFactory.getConnection();
            String sql= "SELECT * FROM patients;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                patients.add(new Patient(resultSet.getInt("user_id"), resultSet.getInt("patient_id"), resultSet.getString("name"), resultSet.getInt("age"),resultSet.getLong("contact_no")));
            }
            System.out.println(patients);
            return patients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getNextId() {
        ArrayList<Integer> employees = new ArrayList<>();
        int id=1000_0001;
        try {
            connection= MySqlConnectionFactory.getConnection();

             String []sql= {"SELECT doctor_id FROM doctors;",
                     "SELECT user_id FROM users;",
                     "SELECT admin_id FROM admin;"};

            PreparedStatement statement;
            for(String query:sql) {
                statement=connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    employees.add(resultSet.getInt(1));
                }
            }
            Collections.sort(employees);
            int size=employees.size();
            for(int i=0;i<size;i++){
                if(employees.get(i)!=id){
//                    System.out.println(id);
                    return id;
                }
                id++;
            }
//            System.out.println(id);
            return id;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

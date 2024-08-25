package com.example.repository;

import com.example.database.MySqlConnectionFactory;
import com.example.exception.AppointmentDoesNotExistException;
import com.example.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

    @Override
    public Employee employeeValidation(int id, String password) {
        String []sql= {"SELECT password FROM doctors where doctor_id= ?;",
                "SELECT password FROM users where user_id= ?;",
                "SELECT password FROM admin where admin_id= ?;"};

        try {
            connection = MySqlConnectionFactory.getConnection();
            PreparedStatement statement;
            for (int i=0;i<sql.length;i++) {
                statement = connection.prepareStatement(sql[i]);
                statement.setInt(1,id);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    if(resultSet.getString(1).equals(password)){
                        switch (i){
                            case 0: return Employee.DOCTOR;
                            case 1: return Employee.USER;
                            case 2: return Employee.ADMIN;
                        }
                    }
                }
            }
            return null;
        }catch (SQLException e) {
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
    public void deleteAppointment(int doctorId, int timeSlot, LocalDate date)throws AppointmentDoesNotExistException {
        String sql= "DELETE FROM schedule WHERE date=? AND timeSlot=? AND doctor_id=?;";
        try {
            connection = MySqlConnectionFactory.getConnection();
            PreparedStatement statement;
            statement = connection.prepareStatement(sql);
            statement.setString(1,date.toString());
            statement.setInt(2,timeSlot);
            statement.setInt(3,doctorId);
            int rows = statement.executeUpdate();
            if(rows==0)
                throw new AppointmentDoesNotExistException("Invalid Appointment Details Provided");
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteAppointmentByID(int appointmentId) throws AppointmentDoesNotExistException{
        String sql= "DELETE FROM schedule WHERE schedule.schedule_id IN(SELECT schedule_id from appointments where appointment_id=?);";
        try {
            connection = MySqlConnectionFactory.getConnection();
            PreparedStatement statement;
            statement = connection.prepareStatement(sql);
            statement.setInt(1,appointmentId);
            System.out.println(statement);
            int rows = statement.executeUpdate();
            if(rows==0)
                throw new AppointmentDoesNotExistException("Invalid Appointment ID Provided");
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

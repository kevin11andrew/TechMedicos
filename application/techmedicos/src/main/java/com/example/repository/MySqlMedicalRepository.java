package com.example.repository;

import com.example.database.MySqlConnectionFactory;
import com.example.exception.AppointmentDoesNotExistException;
import com.example.models.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MySqlMedicalRepository implements MedicalRepository {
    Connection connection = null;

    static {
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
            connection = MySqlConnectionFactory.getConnection();
            String sql = "SELECT * FROM admin;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
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
            connection = MySqlConnectionFactory.getConnection();
            String sql = "SELECT * FROM doctors;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
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
            connection = MySqlConnectionFactory.getConnection();
            String sql = "SELECT * FROM users;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
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
            connection = MySqlConnectionFactory.getConnection();
            String sql = "SELECT * FROM patients;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                patients.add(new Patient(resultSet.getInt("user_id"), resultSet.getInt("patient_id"), resultSet.getString("name"), resultSet.getInt("age"), resultSet.getLong("contact_no")));
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
        int id = 1000_0001;
        try {
            connection = MySqlConnectionFactory.getConnection();

            String[] sql = {"SELECT doctor_id FROM doctors;",
                    "SELECT user_id FROM users;",
                    "SELECT admin_id FROM admin;"};

            PreparedStatement statement;
            for (String query : sql) {
                statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    employees.add(resultSet.getInt(1));
                }
            }
            Collections.sort(employees);
            int size = employees.size();
            for (int i = 0; i < size; i++) {
                if (employees.get(i) != id) {
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
    public Admin getAdminById(int id) {
        Admin admin;
        try {
            connection = MySqlConnectionFactory.getConnection();
            String sql = "SELECT * FROM admin WHERE admin_id=?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            admin=new Admin(resultSet.getInt("admin_id"), resultSet.getString("password"));
            System.out.println(admin);
            return admin;
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
    public Doctor getDoctorById(int id) {
        Doctor doctor;
        try {
            connection = MySqlConnectionFactory.getConnection();
            String sql = "SELECT * FROM doctors WHERE doctor_id=?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            doctor=new Doctor(resultSet.getInt("doctor_id"), resultSet.getString("speciality"), resultSet.getString("name"),resultSet.getLong("contact_no"),resultSet.getString("password"));
            System.out.println(doctor);
            return doctor;
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
    public User getUserById(int id) {
        User user;
        try {
            connection = MySqlConnectionFactory.getConnection();
            String sql = "SELECT * FROM users WHERE user_id=?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            user=new User(resultSet.getInt("user_id"), resultSet.getString("name"),resultSet.getLong("contact_no"),resultSet.getString("password"));
            System.out.println(user);
            return user;
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
        String[] sql = {"SELECT password FROM doctors where doctor_id= ?;",
                "SELECT password FROM users where user_id= ?;",
                "SELECT password FROM admin where admin_id= ?;"};

        try {
            connection = MySqlConnectionFactory.getConnection();
            PreparedStatement statement;
            for (int i = 0; i < sql.length; i++) {
                statement = connection.prepareStatement(sql[i]);
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    if (resultSet.getString(1).equals(password)) {
                        switch (i) {
                            case 0:
                                return Employee.DOCTOR;
                            case 1:
                                return Employee.USER;
                            case 2:
                                return Employee.ADMIN;
                        }
                    }
                }
            }
            return null;
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
    public void deleteAppointment(int doctorId, int timeSlot, LocalDate date) throws AppointmentDoesNotExistException {
        String sql = "DELETE FROM schedule WHERE date=? AND timeSlot=? AND doctor_id=?;";
        try {
            connection = MySqlConnectionFactory.getConnection();
            PreparedStatement statement;
            statement = connection.prepareStatement(sql);
            statement.setString(1, date.toString());
            statement.setInt(2, timeSlot);
            statement.setInt(3, doctorId);
            int rows = statement.executeUpdate();
            if (rows == 0)
                throw new AppointmentDoesNotExistException("Invalid Appointment Details Provided");
        } catch (SQLException e) {
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
    public void deleteAppointmentByID(int appointmentId) throws AppointmentDoesNotExistException {
        String sql = "DELETE FROM schedule WHERE schedule.schedule_id IN(SELECT schedule_id from appointments where appointment_id=?);";
        try {
            connection = MySqlConnectionFactory.getConnection();
            PreparedStatement statement;
            statement = connection.prepareStatement(sql);
            statement.setInt(1, appointmentId);
            System.out.println(statement);
            int rows = statement.executeUpdate();
            if (rows == 0)
                throw new AppointmentDoesNotExistException("Invalid Appointment ID Provided");
        } catch (SQLException e) {
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
    public boolean registerPatient(Patient patient) {
        String sql = "INSERT INTO patients (name, age, contact_no, user_id) VALUES (?, ?, ?, ?);";
        int rows=0;
        try {
            connection = MySqlConnectionFactory.getConnection();
            PreparedStatement statement;
            statement = connection.prepareStatement(sql);
            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setLong(3, patient.getContactNo());
            statement.setInt(4, patient.getUserId());
            System.out.println(statement);
            rows = statement.executeUpdate();
        }catch (SQLIntegrityConstraintViolationException e){
            return false;
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rows !=0;
    }

    @Override
    public boolean isAvailable(int doctorId, int timeSlot, LocalDate date) {
        String sql = "SELECT schedule_id FROM schedule WHERE doctor_id = ? AND timeslot = ? AND date= ?;";

        try {
            connection = MySqlConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,doctorId);
            statement.setInt(2,timeSlot);
            statement.setString(3,date.toString());
            ResultSet resultSet = statement.executeQuery();
            return !resultSet.next();
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
    public void createAppointment(int userId, int doctorId, int patientId, LocalDate date, int timeSlot, String summary) {
        String sql = "INSERT INTO schedule (date, timeslot, doctor_id) VALUES (?, ?, ?);";
        int rows=0;
        try {
            connection = MySqlConnectionFactory.getConnection();
            PreparedStatement statement;
            statement = connection.prepareStatement(sql);
            statement.setString(1, date.toString());
            statement.setInt(2, timeSlot);
            statement.setInt(3, doctorId);
            System.out.println(statement);
            rows = statement.executeUpdate();

            sql = "SELECT schedule_id from schedule where date=? AND timeslot=? AND doctor_id=?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, date.toString());
            statement.setInt(2, timeSlot);
            statement.setInt(3, doctorId);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int schedule_id=resultSet.getInt("schedule_id");
            System.out.println(schedule_id);

            sql = "INSERT INTO appointments (patient_id, doctor_id, user_id, schedule_id, summary) VALUES (?, ?, ?, ?, ?);";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, patientId);
            statement.setInt(2, doctorId);
            statement.setInt(3, userId);
            statement.setInt(4,schedule_id);
            statement.setString(5, summary);
            rows = statement.executeUpdate();
            System.out.println(rows);


        }catch (SQLIntegrityConstraintViolationException e){
//            return false;
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//        return rows !=0;
    }
}

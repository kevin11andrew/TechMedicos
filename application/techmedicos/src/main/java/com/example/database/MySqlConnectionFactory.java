package com.example.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

            String sql = "CREATE TABLE IF NOT EXISTS user3 (user_id int(8) primary key, name varchar(20), password varchar(16));";
            PreparedStatement statement=connection.prepareStatement(sql);
            int n = statement.executeUpdate();



            System.out.println(n);
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

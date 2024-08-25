import com.example.database.MySqlConnectionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class MySqlConnectionFactoryTest {

    private static Connection connection;

    @BeforeAll
    static void setUp() throws SQLException {
        // Initialize the connection before running the tests
        connection = MySqlConnectionFactory.getConnection();
    }

    @AfterAll
    static void tearDown() throws SQLException {
        // Close the connection after running the tests
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    void testGetConnection() {
        // Test if the connection is not null and valid
        assertNotNull(connection, "Connection should not be null");
        assertTrue(connection instanceof Connection, "Connection should be an instance of java.sql.Connection");
    }

    @Test
    void testDatabaseProperties() {
        // Test if the database properties are loaded correctly
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("/home/kevin1_1andrew/Desktop/HSBC TRAINING/CODE FURY/TechMedicos/application/techmedicos/src/main/resources/database.properties");
            properties.load(fis);
        } catch (FileNotFoundException e) {
            fail("Properties file not found: " + e.getMessage());
        } catch (IOException e) {
            fail("Failed to load properties file: " + e.getMessage());
        }

        String url = properties.getProperty("jdbc:mysql://localhost:3306/medical_database");
        String user = properties.getProperty("root");
        String password = properties.getProperty("root1234");

        assertNotNull(url, "Database URL should not be null");
        assertNotNull(user, "Database user should not be null");
        assertNotNull(password, "Database password should not be null");
    }

    @Test
    void testTableCreation() {
        // Test if the tables are created correctly
        String[] tables = {"users", "doctors", "admin", "patients", "schedule", "appointments"};
        try {
            DatabaseMetaData dbMetaData = connection.getMetaData();
            for (String table : tables) {
                ResultSet rs = dbMetaData.getTables(null, null, table, null);
                assertTrue(rs.next(), "Table " + table + " should exist in the database");
            }
        } catch (SQLException e) {
            fail("Failed to verify table creation: " + e.getMessage());
        }
    }

    @Test
    void testExceptionHandling() {
        // Test if the class handles file not found exception correctly
        try {
            new FileInputStream("non_existent_file.properties");
            fail("Expected FileNotFoundException was not thrown");
        } catch (FileNotFoundException e) {
            // Exception is expected
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        // Test if the class handles SQL exception correctly
        try {
            DriverManager.getConnection("invalid_url", "invalid_user", "invalid_password");
            fail("Expected SQLException was not thrown");
        } catch (SQLException e) {
            // Exception is expected
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
}

package AtvEmSala01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        String databaseUrl = "jdbc:mysql://localhost:3306";
        String username = "root";
        String password = System.getenv("DB_PASSWORD");

        Connection connection = null;

        try {
            // Attempt to establish a connection to the database
            connection = DriverManager.getConnection(databaseUrl, username, password);

            // If the connection is successful, print a success message
            System.out.println("Database connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Database connection failed! Error: " + e.getMessage());
        } finally {
            // Close the connection if it was opened
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.err.println("Error while closing the database connection: " + e.getMessage());
                }
            }
        }
    }
}

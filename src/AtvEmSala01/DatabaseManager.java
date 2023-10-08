package AtvEmSala01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");
    private static DatabaseManager instance;

    private Connection connection;

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Conexão falhou.");
        }
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Conexão não encerrou.");
            }
        }
    }
}

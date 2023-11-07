package lista02.model;

import java.sql.*;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/lista02";
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    private static DatabaseManager instance;
    private static Connection connection;

    private DatabaseManager() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados.");
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

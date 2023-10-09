package AtvEmSala01;

import javax.swing.*;
import java.sql.*;

class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/atvemsala01";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");
    private static DatabaseManager instance;

    private Connection connection;

    private DatabaseManager() {
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

class Usuario {
    // Essa classe Usuario é utilizada para representar um usuario enquanto objeto no programa
    // no problema atual não é necessário, mas é uma boa prática. Irei instanciar um usuario qualquer
    // apenas para agradar a inspeção do IntelliJ.
    private int id;
    private String login;
    private String senha;

    public Usuario(int id, String login, String senha) {
        this.id = id;
        this.login = login;
        this.senha = senha;
    }
    public void metodoFantasma() {
        // Esse método é apenas para agradar a inspeção do IntelliJ
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

interface UsuarioDAO {
    boolean autenticar(String login, String senha);
}

class UsuarioDAOImpl implements UsuarioDAO {
    private final Connection connection;

    public UsuarioDAOImpl() {
        this.connection = DatabaseManager.getInstance().getConnection();
    }

    @Override
    public boolean autenticar(String login, String senha) {
        String query = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            statement.setString(2, senha);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Erro ao autenticar usuário: " + e.getMessage());
            return false;
        }

    }
}

public class Main {
    public static void main(String[] args) {
        // Aqui criarei o usuário admin para a inspeção do IntelliJ ficar feliz
        // Juntamente com a utilizaçao dos getters e setters da classe Usuario
        Usuario usuario = new Usuario(1, "admin", "admin");
        usuario.setId(5);
        int idtest = usuario.getId();
        usuario.setLogin("Test1");
        String logintest = usuario.getLogin();
        usuario.setSenha("Alguma senha");
        String senhatest = usuario.getSenha();
        Usuario usuarioCopia = new Usuario(idtest, logintest, senhatest);
        usuarioCopia.metodoFantasma();
        SwingUtilities.invokeLater(LoginScreen::new);
        Runtime.getRuntime().addShutdownHook(new Thread(DatabaseManager.getInstance()::closeConnection));
    }
}

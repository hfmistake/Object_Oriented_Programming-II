package atvemsala01;

import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class MainScreen {
    protected JPanel mainPanel;
    protected JPanel welcomePanel;
    protected JLabel mainLabel;

    public MainScreen(String name) {
        mainLabel.setText("Seja Bem-Vindo " + name + "!");
        JFrame frame = new JFrame("Tela Principal");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            Logger logger = Logger.getLogger(atvemsala01.MainScreen.class.getName());
            logger.severe("An error occurred while setting the look and feel:");
            logger.severe(e.getMessage());
            logger.log(Level.SEVERE, "Exception details:", e);
        }
    }
}

class LoginScreen {
    protected JPanel mainPanel;
    protected JPanel loginPanel;
    protected JTextField loginField;
    protected JPasswordField passField;
    protected JLabel loginLabel;
    protected JLabel passLabel;
    protected JButton clearButton;
    protected JButton loginButton;
    protected JLabel ifgLogo;

    final UsuarioDAO usuarioDAO;

    void OnLoginClick() {
        // autenticate with DAO and show main screen
        String login = loginField.getText();
        String senha = new String(passField.getPassword());
        if (usuarioDAO.autenticar(login, senha)) {
            new MainScreen(login);
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.");
        }
    }

    public LoginScreen() {
        usuarioDAO = new UsuarioDAOmysql();
        JFrame frame = new JFrame("Tela de Login");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        loginButton.addActionListener(e -> OnLoginClick());
        clearButton.addActionListener(e -> {
            loginField.setText("");
            passField.setText("");
        });
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            Logger logger = Logger.getLogger(atvemsala01.LoginScreen.class.getName());
            logger.severe("An error occurred while setting the look and feel:");
            logger.severe(e.getMessage());
            logger.log(Level.SEVERE, "Exception details:", e);
        }
    }
}

class DatabaseManager {
    static final String DB_URL = "jdbc:mysql://localhost:3306/atvemsala01";
    static final String DB_USER = System.getenv("DB_USER");
    static final String DB_PASSWORD = System.getenv("DB_PASSWORD");
    private static DatabaseManager instance;

    private static Connection connection;

    private DatabaseManager() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Conexão falhou.");
        }
    }

    public static synchronized DatabaseManager getInstance() {
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
    int id;
    String login;
    String senha;

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

class UsuarioDAOmysql implements UsuarioDAO {
    final Connection connection;

    public UsuarioDAOmysql() {
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

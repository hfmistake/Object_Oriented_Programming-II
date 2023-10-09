package AtvEmSala01;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginScreen {
    private JPanel mainPanel;
    JPanel loginPanel;
    private JTextField loginField;
    private JPasswordField passField;
    JLabel loginLabel;
    JLabel passLabel;
    private JButton clearButton;
    private JButton loginButton;
    JLabel ifgLogo;

    private final UsuarioDAO usuarioDAO;

    private void OnLoginClick() {
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
        usuarioDAO = new UsuarioDAOImpl();
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
            Logger logger = Logger.getLogger(Lista01.Ex06.MainScreen.class.getName());
            logger.severe("An error occurred while setting the look and feel:");
            logger.severe(e.getMessage());
            logger.log(Level.SEVERE, "Exception details:", e);
        }
    }
}

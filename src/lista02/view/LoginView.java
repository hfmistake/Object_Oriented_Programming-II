package lista02.view;

import lista02.controller.*;

import javax.swing.*;

public class LoginView extends JFrame {
    public JPanel windowPanel;
    public JPanel mainPanel;
    public JLabel loginLabel;
    public JTextField loginField;
    public JLabel senhaLabel;
    public JPasswordField senhaField;
    public JPanel titlePanel;
    public JLabel titleLabel;
    public JButton loginButton;
    public JButton limparButton;
    public JPanel formPanel;
    public JLabel logoLabel;
    private final UserController userController = ControllerFactory.createUserController(this);

    public UserController getUserController() {
        return userController;
    }

    public LoginView() {
        setupUI();
        setupListeners();
    }

    private void setupUI() {
        this.setTitle("Login");
        this.setContentPane(windowPanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void clearFields() {
        loginField.setText("");
        senhaField.setText("");
    }

    private void setupListeners() {
        this.loginButton.addActionListener(e -> userController.loginRequest());
        this.limparButton.addActionListener(e -> clearFields());
    }
}

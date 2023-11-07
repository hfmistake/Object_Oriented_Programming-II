package lista02.view;

import javax.swing.*;

public class LoginView extends JFrame{
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

    public LoginView() {
        this.setTitle("Login");
        this.setContentPane(windowPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
}
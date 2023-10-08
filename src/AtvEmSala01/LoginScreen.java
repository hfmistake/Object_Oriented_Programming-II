package AtvEmSala01;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LoginScreen {
    private JPanel Main;
    private JPanel Login;
    private JTextField textField1;
    private JTextField textField2;
    private JButton acessarButton;
    private JButton limparButton;


    public LoginScreen() {
        DatabaseManager databaseManager = new DatabaseManager();
        System.out.println(databaseManager.getConnection());
        JFrame frame = new JFrame("LoginScreen");
        frame.setContentPane(Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600,400);
        Color corBorda = new Color(32,57,229);
        Border border = BorderFactory.createLineBorder(corBorda, 6);
        Login.setBorder(border);
    }

    public static void main(String[] args) {
        new LoginScreen();
    }

}

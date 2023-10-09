package AtvEmSala01;

import javax.swing.*;

public class MainScreen {
    private JPanel mainPanel;
    JPanel welcomePanel;
    private JLabel mainLabel;

    public MainScreen(String name) {
        mainLabel.setText("Seja Bem-Vindo " + name + "!");
        JFrame frame = new JFrame("Tela Principal");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}

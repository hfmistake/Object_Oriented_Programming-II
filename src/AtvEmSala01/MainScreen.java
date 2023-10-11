package AtvEmSala01;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            Logger logger = Logger.getLogger(AtvEmSala01.MainScreen.class.getName());
            logger.severe("An error occurred while setting the look and feel:");
            logger.severe(e.getMessage());
            logger.log(Level.SEVERE, "Exception details:", e);
        }
    }
}

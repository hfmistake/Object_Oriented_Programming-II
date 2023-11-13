package lista02;

import lista02.view.*;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            Logger logger = Logger.getLogger(Main.class.getName());
            logger.severe("An error occurred while setting the look and feel:");
            logger.severe(e.getMessage());
            logger.log(Level.SEVERE, "Exception details:", e);
        }
        SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView();
            loginView.getUserController().showLoginView();
        });
    }
}

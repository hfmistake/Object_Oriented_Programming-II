package lista02;

import lista02.model.DatabaseManager;
import lista02.view.ProjectManagementView;
import lista02.controller.ProjetoController;
import lista02.model.ProjetoDAOmySQL;
import lista02.view.LoginView;
import lista02.view.MainView;

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
        LoginView loginView = new LoginView();
        ProjetoDAOmySQL projetoDAO = new ProjetoDAOmySQL();
        ProjectManagementView view = new ProjectManagementView();
        MainView mainView = new MainView();
        ProjetoController controller = new ProjetoController(projetoDAO, view, loginView, mainView);
        controller.showLoginView();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> DatabaseManager.getInstance().closeConnection()));
    }
}

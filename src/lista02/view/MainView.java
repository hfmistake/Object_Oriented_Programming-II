package lista02.view;

import lista02.controller.ControllerFactory;
import lista02.controller.MainController;
import lista02.model.User;

import javax.swing.*;
import java.text.SimpleDateFormat;

public class MainView extends JFrame {
    public JPanel windowPanel;
    public JPanel mainPanel;
    public JLabel logoLabel;
    public JMenuBar menuBar;
    public JMenu gestaoMenu;
    public JMenu projetosMenu;
    public JMenuItem cadastrarItem;
    public JPanel infoPanel;
    public JLabel userLabel;
    public JLabel dataLabel;
    public JPanel userData;
    public JMenu userMenu;
    public JMenuItem logoutItem;

    private final MainController mainController;

    public MainController getMainController() {
        return mainController;
    }

    public MainView(User currentUser) {
        this.mainController = ControllerFactory.createMainController(currentUser, this);
        setupUI();
        setupListeners();
        setupUserInfo();
    }

    public void setupUI() {
        this.setTitle("Janela Principal");
        this.setContentPane(windowPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void setupUserInfo() {
        userLabel.setText("Usuário: " + mainController.getCurrentUser().login());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        dataLabel.setText("Data: " + formatter.format(System.currentTimeMillis()));
    }

    public void setupListeners() {
        cadastrarItem.addActionListener(e -> mainController.callProjectManagementView());
        logoutItem.addActionListener(e -> mainController.logout());
    }
}

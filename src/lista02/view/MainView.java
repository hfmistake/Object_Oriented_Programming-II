package lista02.view;

import lista02.controller.ControllerFactory;
import lista02.controller.MainController;

import javax.swing.*;

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

    private final MainController mainController = ControllerFactory.createMainController(this);

    public MainController getMainController() {
        return mainController;
    }

    public MainView() {
        setupUI();
        setupListeners();
    }

    public void setupUI() {
        this.setTitle("Janela Principal");
        this.setContentPane(windowPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void setupListeners() {
        cadastrarItem.addActionListener(e -> mainController.callProjectManagementView());
        logoutItem.addActionListener(e -> mainController.logout());
    }
}

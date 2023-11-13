package lista02.view;

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
        cadastrarItem.addActionListener(e -> {
            ProjectManagementView projectManagementView = new ProjectManagementView();
            projectManagementView.setVisible(true);
        });
        logoutItem.addActionListener(e -> {
            LoginView loginView = new LoginView();
            loginView.setVisible(true);
            this.dispose();
        });
    }
}

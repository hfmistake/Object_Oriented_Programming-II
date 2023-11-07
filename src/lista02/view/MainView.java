package lista02.view;

import javax.swing.*;

public class MainView extends JFrame{
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

    public MainView() {
        this.setTitle("Janela Principal");
        this.setContentPane(windowPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
}

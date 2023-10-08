package Lista01.Ex01;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame {

    private JLabel Title;
    private JTextField Montante;
    private JTextField Juros;
    private JTextField Meses;
    private JPanel Main;
    private JPanel Form;
    private JButton calcularButton;
    private JLabel result;

    public MainScreen() {
        JFrame frame = new JFrame("Poupex");
        Color corBorda = new Color(222, 224, 255);
        Border border = BorderFactory.createLineBorder(corBorda, 6);
        Form.setBorder(border);
        frame.setContentPane(Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == calcularButton) {
                    try {
                        double montante = Double.parseDouble(Montante.getText());
                        double juros = Double.parseDouble(Juros.getText());
                        int meses = Integer.parseInt(Meses.getText());
                        double valorFuturo = montante + juros * meses;
                        if (valorFuturo == (int) valorFuturo) {
                            result.setText(String.valueOf((int) valorFuturo));
                        } else {
                            result.setText(String.valueOf(valorFuturo));
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Dados inválidos");
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        new MainScreen();
    }

    public static class Calculadora {
    }
}

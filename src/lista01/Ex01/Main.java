package lista01.Ex01;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

class MainScreen {

    JLabel Title;
    JTextField Montante;
    JTextField Juros;
    JTextField Meses;
    JPanel Main;
    JPanel Form;
    JButton calcularButton;
    JLabel result;

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
        calcularButton.addActionListener(e -> {
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
        });
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainScreen::new);
    }
}

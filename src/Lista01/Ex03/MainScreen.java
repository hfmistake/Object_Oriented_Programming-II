package Lista01.Ex03;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen {
    private JLabel Title;
    private JTextField N1;
    private JTextField N2;
    private JButton Add;
    private JPanel ButtonPanel;
    private JPanel Main;
    private JPanel Tab;
    private JButton Sub;
    private JButton Mul;
    private JButton Div;
    private JButton ModoButton;
    private char Modo = '1';


    public MainScreen() {
        JFrame frame = new JFrame("Ex03");
        Color corBorda = new Color(222, 224, 255);
        Border border = BorderFactory.createLineBorder(corBorda, 6);
        Tab.setBorder(border);
        frame.setContentPane(Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        ActionListener botoes = e -> {
            try {
                Calculadora calc;
                double num1 = Double.parseDouble(N1.getText());
                double num2 = Double.parseDouble(N2.getText());
                if (e.getSource() == Add) {
                    calc = new Calculadora(num1, num2, '+');
                    calc.Resultado(calc.Calcular(), Modo);
                } else if (e.getSource() == Sub) {
                    calc = new Calculadora(num1, num2, '-');
                    calc.Resultado(calc.Calcular(), Modo);
                } else if (e.getSource() == Mul) {
                    calc = new Calculadora(num1, num2, '*');
                    calc.Resultado(calc.Calcular(), Modo);
                } else if (e.getSource() == Div) {
                    calc = new Calculadora(num1, num2, '/');
                    calc.Resultado(calc.Calcular(), Modo);
                }
            } catch (NumberFormatException er) {
                JOptionPane.showMessageDialog(null, "Números inválidos.");
            }
        };
        Add.addActionListener(botoes);
        Sub.addActionListener(botoes);
        Mul.addActionListener(botoes);
        Div.addActionListener(botoes);
        ModoButton.addActionListener(e -> {
            if (Modo == '1') {
                Modo = '2';
                ModoButton.setText("Modo 2");
            } else {
                Modo = '1';
                ModoButton.setText("Modo 1");
            }
        });
    }

    public static void main(String[] args) {
        new MainScreen();
    }
}

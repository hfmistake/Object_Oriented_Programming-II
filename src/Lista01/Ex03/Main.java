package Lista01.Ex03;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

class Calculadora {
    double n1;
    double n2;
    char operador;

    public Calculadora(double n1, double n2, char operador) {
        this.n1 = n1;
        this.n2 = n2;
        this.operador = operador;
    }

    public static Number FloatingPointHandler(double value) {
        if (value % 1.0 == 0.0) {
            return (int) value;
        } else {
            return value;
        }
    }

    public double Calcular() {
        if (operador == '+') {
            return n1 + n2;
        } else if (operador == '-') {
            return n1 - n2;
        } else if (operador == '*') {
            return n1 * n2;
        } else if (operador == '/') {
            if (n2 == 0.0) {
                JOptionPane.showMessageDialog(null, "Impossivel Dividir por zero.");
                return Double.NaN;
            } else {
                return n1 / n2;
            }
        } else {
            return Double.NaN;
        }
    }

    public void Resultado(double n, char modo) {
        if (Double.isNaN(n)) {
            return;
        }
        if (modo == '1') {
            System.out.println("Resultado: " + FloatingPointHandler(n));
        } else {
            JOptionPane.showMessageDialog(null, "O resultado é: " + FloatingPointHandler(n));
        }
    }
}

class MainScreen {
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
}

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            Logger logger = Logger.getLogger(Lista01.Ex03.MainScreen.class.getName());
            logger.severe("An error occurred while setting the look and feel:");
            logger.severe(e.getMessage());
            logger.log(Level.SEVERE, "Exception details:", e);
        }
        SwingUtilities.invokeLater(MainScreen::new);
    }
}

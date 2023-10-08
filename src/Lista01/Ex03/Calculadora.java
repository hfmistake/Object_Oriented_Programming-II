package Lista01.Ex03;

import javax.swing.*;

public class Calculadora {
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

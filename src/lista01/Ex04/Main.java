package lista01.Ex04;

import javax.swing.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class MainScreen {
    JPanel Main;
    JPanel NumPanel;
    JTextField NumField;
    JLabel NumLabel;
    JButton Limpar;
    JTextField MaxField;
    JTextField MinField;
    JTextField AvgField;
    JButton FinalButton;
    JButton cadastrarButton;
    JButton ViewArray;

    public boolean validateArray(ArrayList<Double> arr) {
        return arr.isEmpty();
    }

    public static Number FloatingPointHandler(double value) {
        if (value % 1.0 == 0.0) {
            return (int) value;
        } else {
            return value;
        }
    }


    public String ListToStr(ArrayList<Double> arr) {
        StringBuilder strArr = new StringBuilder("[");
        int indextrack = 0;
        if (arr.isEmpty()) {
            return "Array Vazio";
        }
        for (double n : arr) {
            strArr.append(FloatingPointHandler(n));
            if (indextrack != arr.size() - 1) {
                strArr.append(", ");
            }
            indextrack++;
        }
        strArr.append("]");
        return strArr.toString();
    }

    public double Max(ArrayList<Double> arr) {
        double maxtracker = Double.NEGATIVE_INFINITY;
        for (double n : arr) {
            if (n > maxtracker) {
                maxtracker = n;
            }
        }
        return maxtracker;
    }

    public double Min(ArrayList<Double> arr) {
        double mintracker = Double.POSITIVE_INFINITY;
        for (double n : arr) {
            if (n < mintracker) {
                mintracker = n;
            }
        }
        return mintracker;
    }

    public double Average(ArrayList<Double> arr) {
        double sum = 0;
        for (double n : arr) {
            sum += n;
        }
        return sum / arr.size();
    }

    public MainScreen() {
        Nums array = new Nums();
        JFrame frame = new JFrame("Array Manager");
        frame.setContentPane(Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        FinalButton.addActionListener(e -> {
            if (validateArray(array.nums)) {
                JOptionPane.showMessageDialog(null,
                        "Impossivel Calcular com o Array Vazio.");
                return;
            }
            MaxField.setText(String.valueOf(FloatingPointHandler(Max(array.nums))));
            MinField.setText(String.valueOf(FloatingPointHandler(Min(array.nums))));
            AvgField.setText(String.valueOf(FloatingPointHandler(Average(array.nums))));
        });
        cadastrarButton.addActionListener(e -> {
            try {
                array.nums.add(Double.parseDouble(NumField.getText()));
            } catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(null, "Número inválido.");
                NumField.setText("");
                return;
            }
            NumField.setText("");
        });
        ViewArray.addActionListener(e -> JOptionPane.showMessageDialog(null, ListToStr(array.nums)));
        Limpar.addActionListener(e -> array.nums.clear());
    }
}

class Nums {
    ArrayList<Double> nums = new ArrayList<>();
}

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            Logger logger = Logger.getLogger(lista01.Ex04.MainScreen.class.getName());
            logger.severe("An error occurred while setting the look and feel:");
            logger.severe(e.getMessage());
            logger.log(Level.SEVERE, "Exception details:", e);
        }
        SwingUtilities.invokeLater(lista01.Ex04.MainScreen::new);
    }
}

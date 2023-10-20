package lista01.Ex02;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

class MainScreen {

    JPanel Main;
    JPanel Client;
    JLabel LabelNome;
    JTextField Nome;
    JTextField Endereco;
    JLabel LabelEnd;
    JComboBox<String> CategoriaBox;
    JLabel LabelCat;
    JRadioButton masculinoRadioButton;
    JRadioButton femininoRadioButton;
    JList<String> ListaFilmes;
    JButton OKButton;
    JButton cancelButton;
    JLabel LabelFilme;
    JPanel ListPanel;

    public void Mostrar(String n, String e, String s, String c, String f) {
        String message = """
                Nome: %s
                Sexo: %s
                Endereço: %s
                Categoria: %s
                Filme: %s
                """.formatted(n, s, e, c, f);
        JOptionPane.showMessageDialog(null, message);
    }

    public MainScreen() {
        ButtonGroup SexoBotoes = new ButtonGroup();
        SexoBotoes.add(masculinoRadioButton);
        SexoBotoes.add(femininoRadioButton);
        JFrame frame = new JFrame("Dados do Cliente");
        Color corBorda = new Color(0, 0, 0);
        Border border = BorderFactory.createLineBorder(corBorda, 6);
        Client.setBorder(border);
        frame.setContentPane(Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        OKButton.addActionListener(e -> {
            String nome = Nome.getText();
            String endereco = Endereco.getText();
            Object selectedCategoria = CategoriaBox.getSelectedItem();
            Object selectedfilme = ListaFilmes.getSelectedValue();
            String categoria = selectedCategoria != null ? selectedCategoria.toString() : null;
            String filme = selectedfilme != null ? selectedfilme.toString() : null;
            String sexo;
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o nome.");
                return;
            }
            if (endereco.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o endereço.");
                return;
            }
            if (masculinoRadioButton.isSelected()) {
                sexo = "Masculino";
            } else if (femininoRadioButton.isSelected()) {
                sexo = "Feminino";
            } else {
                JOptionPane.showMessageDialog(null, "Selecione o Gênero.");
                return;
            }

            if (categoria == null) {
                JOptionPane.showMessageDialog(null, "Selecione a Categoria.");
                return;
            }

            if (filme == null) {
                JOptionPane.showMessageDialog(null, "Selecione o filme.");
                return;
            }
            Mostrar(nome, sexo, endereco, categoria, filme);
        });
        cancelButton.addActionListener(e -> System.exit(0));
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainScreen::new);
    }
}

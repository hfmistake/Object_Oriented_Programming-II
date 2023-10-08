package Lista01.Ex06;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FilmeEditor {
    private JPanel Editor;
    private JLabel NomeFilmeLabel;
    private JTextField NomeFilmeField;
    private JLabel Title;
    private JLabel DataLabel;
    private JTextField DataFilmeField;
    private JButton finalizarButton;
    private JPanel MainPanel;
    private JLabel TurnoFilmeLabel;
    private JComboBox<String> TurnoBox;
    private JTextField ValorIngressoField;
    private JLabel ValorIngressoLabel;
    private JTextField SalaField;
    private JLabel SalaLabel;

    public FilmeEditor(MainScreen mainScreen, Filme filme) {
        JFrame frame = new JFrame("Editor de filme");
        frame.setContentPane(MainPanel);
        Title.setText("Editando Filme: " + filme.getFilme());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        NomeFilmeField.setText(filme.getFilme());
        DataFilmeField.setText(filme.getDatadofilme().format(formatter));
        TurnoBox.setSelectedItem(filme.getTurno());
        SalaField.setText(String.valueOf(filme.getSala()));
        ValorIngressoField.setText(String.valueOf(filme.getValor()));
        finalizarButton.addActionListener(e -> {
            String novoNome = NomeFilmeField.getText();
            String novaDataTexto = DataFilmeField.getText();
            String novoTurno = (String) TurnoBox.getSelectedItem();
            String novaSalaTexto = SalaField.getText();
            String novoValorTexto = ValorIngressoField.getText();
            if (novoNome.isEmpty()) {
                throw new NumberFormatException();
            }
            try {
                LocalDate novaData = LocalDate.parse(novaDataTexto,formatter);
                int novaSala = Integer.parseInt(novaSalaTexto);
                double novoValor = Double.parseDouble(novoValorTexto);
                Filme novoFilme = new Filme(filme.getId(), novoNome, novaData, novoTurno, novaSala, novoValor);
                mainScreen.alterarFilme(novoFilme);
                frame.dispose();
            } catch (DateTimeParseException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Dados inválidos. Certifique-se de que todos os campos são preenchidos corretamente.");
            }
        });
    }

}

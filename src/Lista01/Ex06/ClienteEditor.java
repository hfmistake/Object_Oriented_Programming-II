package Lista01.Ex06;

import javax.swing.*;

public class ClienteEditor {
    private JPanel MainPanel;
    private JPanel Editor;
    private JTextField NomeField;
    private JLabel Title;
    private JTextField IdadeField;
    private JButton finalizarButton;
    private JLabel NomeLabel;
    private JLabel IdadeLabel;

    public ClienteEditor(MainScreen telaPrincipal, Cliente cliente) {
        JFrame frame = new JFrame("Editor de Cliente");
        frame.setContentPane(MainPanel);
        Title.setText("Editando Cliente:" + cliente.getNome());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        NomeField.setText(cliente.getNome());
        IdadeField.setText(String.valueOf(cliente.getIdade()));
        finalizarButton.addActionListener(e -> {
            String novoNome = NomeField.getText();
            int novaIdade;
            try {
                novaIdade = Integer.parseInt(IdadeField.getText());
            } catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(null, "Idade inválida.");
                return;
            }
            if (novoNome == null || novoNome.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nome inválido.");
                return;
            }
            Cliente novoCliente = new Cliente(novoNome, novaIdade, cliente.getId());
            telaPrincipal.alterarCliente(novoCliente);

            frame.dispose();
        });
    }
}

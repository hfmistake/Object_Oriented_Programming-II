package Lista01.Ex05;

import javax.swing.*;
import java.util.ArrayList;

public class LivroGUI {

    private JPanel Main;
    private JPanel Livros;
    private JTextField nomeField;
    private JTextField pageField;
    private JButton adicionarButton;
    private JButton alterarButton;
    private JButton excluirButton;
    private JList<Livro> BookList;
    private final ArrayList<Livro> livros = new ArrayList<>();
    private final DefaultListModel<Livro> listModel = new DefaultListModel<>();

    public void bookTrackerUpdater() {
        listModel.clear();
        for (Livro livro : livros) {
            listModel.addElement(livro);
        }
    }

    public LivroGUI() {
        BookList.setModel(listModel);
        JFrame frame = new JFrame("LivroGUI");
        frame.setContentPane(Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        adicionarButton.addActionListener(e -> {
            int pages = 0;
            if (nomeField.getText().isEmpty() ||
                    pageField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Preencha todos os dados para Adicionar.");
                return;
            }
            try {
                pages = Integer.parseInt(pageField.getText());
                if (pages <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(null,
                        "Número de páginas inválido.");
                return;
            }
            Livro livro = new Livro(nomeField.getText(), pages);
            livros.add(livro);
            bookTrackerUpdater();
            nomeField.setText("");
            pageField.setText("");
        });
        alterarButton.addActionListener(e -> {
            int qnt = 0;
            Livro target = BookList.getSelectedValue();
            if (target == null) {
                JOptionPane.showMessageDialog(null,
                        "Falha na alteração, Nenhum livro foi selecionado.");
                return;
            }
            int trackindex = livros.indexOf(target);
            String nome = JOptionPane.showInputDialog(null, "Digite o novo nome",
                    "Mudar nome", JOptionPane.PLAIN_MESSAGE);
            if (nome == null || nome.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Falha na alteração, Nome não foi fornecido.");
                return;
            }
            String pages = JOptionPane.showInputDialog(null,
                    "Digite a nova quantidade de páginas",
                    "Mudar páginas", JOptionPane.PLAIN_MESSAGE);
            if (pages == null || pages.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Falha na alteração, " +
                        "Quantidade de páginas não foi fornecido.");
                return;
            }
            try {
                qnt = Integer.parseInt(pages);
            } catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(null, "Falha na alteração, " +
                        "Quantidade de páginas inválido.");
                return;
            }

            Livro livro = new Livro(nome, qnt);
            livros.set(trackindex,livro);
            bookTrackerUpdater();
        });
        excluirButton.addActionListener(e -> {
            Livro target = BookList.getSelectedValue();
            livros.remove(target);
            bookTrackerUpdater();
        });
    }

    public static void main(String[] args) {
        new LivroGUI();
    }
}

package Lista01.Ex05;

import javax.swing.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class Livro {
    String title;
    int qntPaginas;

    public Livro(String t, int qnt) {
        this.title = t;
        this.qntPaginas = qnt;
    }

    public String toString() {
        return """
                %s (%d)""".formatted(title, qntPaginas);
    }
}

class LivroGUI {

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
            livros.set(trackindex, livro);
            bookTrackerUpdater();
        });
        excluirButton.addActionListener(e -> {
            Livro target = BookList.getSelectedValue();
            livros.remove(target);
            bookTrackerUpdater();
        });
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            Logger logger = Logger.getLogger(LivroGUI.class.getName());
            logger.severe("An error occurred while setting the look and feel:");
            logger.severe(e.getMessage());
            logger.log(Level.SEVERE, "Exception details:", e);
        }
        SwingUtilities.invokeLater(LivroGUI::new);
    }
}

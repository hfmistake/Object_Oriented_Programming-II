package Lista01.Ex06;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainScreen {
    private JPanel MainPanel;
    private JTabbedPane Panel;
    private JPanel ClientesPane;
    private JPanel CadClientePanel;
    private JTextField NomeClienteField;
    private JTextField IdadeField;
    private JPanel ClienteButtons;
    private JPanel ClientesDisplay;
    private JButton ClienteSalvarButton;
    private JButton ClienteAlterarButton;
    private JButton ClienteExcluirButton;
    private JList<Cliente> ClientesList;
    private JPanel FilmesPane;
    private JPanel IngressoPane;
    private JTextField SalaField;
    private JTextField IngressoField;
    private JPanel FilmesCadPane;
    private JPanel FilmesButtons;
    private JPanel FilmesDisplay;
    private JComboBox<String> TurnoBox;
    private JComboBox<String> FilmeSelectionBox;
    private JPanel IngressoFilmePane;
    private JButton ViewDataButton;
    private JPanel IngressoButton;
    private JPanel IngressoSelection;
    private JList<Filme> FilmesList;
    private JTextField NomeFilmeField;
    private JLabel DataFilmeLabel;
    private JLabel NomeFilmeLabel;
    private JTextField DataFilmeField;
    private JLabel TurnoLabel;
    private JLabel SalaLabel;
    private JLabel IngressoLabel;
    private JLabel FilmeSelectionLabel;
    private JLabel NomeClienteLabel;
    private JLabel IdadeLabel;
    private JButton FilmesSalvarButton;
    private JButton FilmesAlterarButton;
    private JButton FilmesExcluirButton;
    private int clientesTracker = 0;
    private int filmesTracker = 0;
    private final ArrayList<Cliente> clientes = new ArrayList<>();
    private final ArrayList<Filme> filmes = new ArrayList<>();
    private final DefaultListModel<Cliente> clienteModel = new DefaultListModel<>();
    private final DefaultListModel<Filme> filmeModel = new DefaultListModel<>();
    private final DefaultComboBoxModel<String> filmeComboModel = new DefaultComboBoxModel<>();


    public void clienteModelTracker() {
        clienteModel.clear();
        for (Cliente cliente : clientes) {
            clienteModel.addElement(cliente);
        }
    }
    public void filmesBoxModelTracker() {
    filmeComboModel.removeAllElements();
    for (Filme filme : filmes) {
        filmeComboModel.addElement(filme.getFilme()); // Adiciona os filmes ao ComboBox
    }
}


    public void filmesModelTracker() {
        filmeModel.clear();
        for (Filme filme : filmes) {
            filmeModel.addElement(filme);
        }
    }

    public void alterarCliente(Cliente novoCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == novoCliente.getId()) {
                cliente.setNome(novoCliente.getNome());
                cliente.setIdade(novoCliente.getIdade());
                break;
            }
        }
        clienteModelTracker();
    }

    private void salvarFilme() {
        String nomeFilme = NomeFilmeField.getText();
        String dataDoTextField = DataFilmeField.getText();
        String turno = (String) TurnoBox.getSelectedItem();
        String salaTexto = SalaField.getText();
        String valorTexto = IngressoField.getText();

        try {
            LocalDate dataFilme = LocalDate.parse(dataDoTextField, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            int sala = Integer.parseInt(salaTexto);
            double valor = Double.parseDouble(valorTexto);
            Filme filme = new Filme(filmesTracker, nomeFilme, dataFilme, turno, sala, valor);
            filmes.add(filme);
            filmesTracker++;
            filmesModelTracker();
            filmesBoxModelTracker();
            NomeFilmeField.setText("");
            DataFilmeField.setText("");
            SalaField.setText("");
            IngressoField.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o filme.\nCertifique-se de inserir dados válidos em todos os campos.");
        }
    }

    public void alterarFilme(Filme novoFilme) {
        for (Filme filme : filmes) {
            if (filme.getId() == novoFilme.getId()) {
                filme.setFilme(novoFilme.getFilme());
                filme.setDatadofilme(novoFilme.getDatadofilme());
                filme.setSala(novoFilme.getSala());
                filme.setTurno(novoFilme.getTurno());
                filme.setValor(novoFilme.getValor());
                break;
            }
        }
        filmesModelTracker();
        filmesBoxModelTracker();
    }

    public MainScreen() {
        FilmeSelectionBox.setModel(filmeComboModel);
        ClientesList.setModel(clienteModel);
        FilmesList.setModel(filmeModel);
        JFrame frame = new JFrame("Cinema");
        frame.setContentPane(MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        ClienteSalvarButton.addActionListener(e -> {
            String nome = "";
            int idade = 0;
            int id = clientesTracker;
            try {
                idade = Integer.parseInt(IdadeField.getText());
                nome = NomeClienteField.getText();
                if (nome.isEmpty()) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(null, "Dados inválidos.");
                return;
            }
            Cliente cliente = new Cliente(nome, idade, id);
            clientes.add(cliente);
            clientesTracker++;
            clienteModelTracker();
            NomeClienteField.setText("");
            IdadeField.setText("");
        });
        ClienteAlterarButton.addActionListener(e -> {
            Cliente cliente = ClientesList.getSelectedValue();
            if (cliente == null) {
                JOptionPane.showMessageDialog(null, "Nenhum cliente selecionado.");
                return;
            }
            new ClienteEditor(this, cliente);
        });
        ClienteExcluirButton.addActionListener(e -> {
            Cliente cliente = ClientesList.getSelectedValue();
            if (cliente == null) {
                JOptionPane.showMessageDialog(null, "Nenhum cliente selecionado.");
                return;
            }
            clientes.remove(cliente);
            clienteModelTracker();
        });
        FilmesSalvarButton.addActionListener(e -> salvarFilme());
        FilmesAlterarButton.addActionListener(e -> {
            Filme filme = FilmesList.getSelectedValue();
            if (filme == null) {
                JOptionPane.showMessageDialog(null, "Nenhum filme selecionado.");
                return;
            }
            new FilmeEditor(this, filme);
        });
        FilmesExcluirButton.addActionListener(e -> {
            Filme filme = FilmesList.getSelectedValue();
            if (filme == null) {
                JOptionPane.showMessageDialog(null, "Nenhum filme selecionado.");
                return;
            }
            filmes.remove(filme);
            filmesModelTracker();
            filmesBoxModelTracker();
        });
        ViewDataButton.addActionListener(e -> {
            String nome = (String) FilmeSelectionBox.getSelectedItem();
            Filme target = null;
            if (nome == null) {
                JOptionPane.showMessageDialog(null, "Nenhum filme selecionado.");
                return;
            }
            for (Filme filme : filmes) {
                if (nome.equals(filme.getFilme())) {
                    target = filme;
                }
            }
            if (target == null) {
                JOptionPane.showMessageDialog(null,"Erro inesperado.");
                return;
            }
            Ingresso ingresso = new Ingresso();
            JOptionPane.showMessageDialog(null, ingresso.apresentarValorIngressos(target));
        });
    }


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            Logger logger = Logger.getLogger(MainScreen.class.getName());
            logger.severe("An error occurred while setting the look and feel:");
            logger.severe(e.getMessage());
            logger.log(Level.SEVERE, "Exception details:", e);
        }
        new MainScreen();
    }


}

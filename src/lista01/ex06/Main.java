package lista01.ex06;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class MainScreen {
    protected JPanel MainPanel;
    protected JTabbedPane Panel;
    protected JPanel ClientesPane;
    protected JPanel CadClientePanel;
    protected JTextField NomeClienteField;
    protected JTextField IdadeField;
    protected JPanel ClienteButtons;
    protected JPanel ClientesDisplay;
    protected JButton ClienteSalvarButton;
    protected JButton ClienteAlterarButton;
    protected JButton ClienteExcluirButton;
    protected JList<Cliente> ClientesList;
    protected JPanel FilmesPane;
    protected JPanel IngressoPane;
    protected JTextField SalaField;
    protected JTextField IngressoField;
    protected JPanel FilmesCadPane;
    protected JPanel FilmesButtons;
    protected JPanel FilmesDisplay;
    protected JComboBox<String> TurnoBox;
    protected JComboBox<String> FilmeSelectionBox;
    protected JPanel IngressoFilmePane;
    protected JButton ViewDataButton;
    protected JPanel IngressoButton;
    protected JPanel IngressoSelection;
    protected JList<Filme> FilmesList;
    protected JTextField NomeFilmeField;
    protected JLabel DataFilmeLabel;
    protected JLabel NomeFilmeLabel;
    protected JTextField DataFilmeField;
    protected JLabel TurnoLabel;
    protected JLabel SalaLabel;
    protected JLabel IngressoLabel;
    protected JLabel FilmeSelectionLabel;
    protected JLabel NomeClienteLabel;
    protected JLabel IdadeLabel;
    protected JButton FilmesSalvarButton;
    protected JButton FilmesAlterarButton;
    protected JButton FilmesExcluirButton;
    int clientesTracker = 0;
    int filmesTracker = 0;
    final ArrayList<Cliente> clientes = new ArrayList<>();
    final ArrayList<Filme> filmes = new ArrayList<>();
    final DefaultListModel<Cliente> clienteModel = new DefaultListModel<>();
    final DefaultListModel<Filme> filmeModel = new DefaultListModel<>();
    final DefaultComboBoxModel<String> filmeComboModel = new DefaultComboBoxModel<>();


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

    void salvarFilme() {
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
            String nome;
            int idade;
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
                JOptionPane.showMessageDialog(null, "Erro inesperado.");
                return;
            }
            Ingresso ingresso = new Ingresso();
            JOptionPane.showMessageDialog(null, ingresso.apresentarValorIngressos(target));
        });
    }
}

class ClienteEditor {
    protected JPanel MainPanel;
    protected JPanel Editor;
    protected JTextField NomeField;
    protected JLabel Title;
    protected JTextField IdadeField;
    protected JButton finalizarButton;
    protected JLabel NomeLabel;
    protected JLabel IdadeLabel;

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

class FilmeEditor {
    protected JPanel Editor;
    protected JLabel NomeFilmeLabel;
    protected JTextField NomeFilmeField;
    protected JLabel Title;
    protected JLabel DataLabel;
    protected JTextField DataFilmeField;
    protected JButton finalizarButton;
    protected JPanel MainPanel;
    protected JLabel TurnoFilmeLabel;
    protected JComboBox<String> TurnoBox;
    protected JTextField ValorIngressoField;
    protected JLabel ValorIngressoLabel;
    protected JTextField SalaField;
    protected JLabel SalaLabel;

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
                LocalDate novaData = LocalDate.parse(novaDataTexto, formatter);
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

class Filme {
    int id;
    String filme;
    LocalDate datadofilme;
    String turno;
    int sala;
    double valor;

    public Filme(int id, String filme, LocalDate datadofilme, String turno, int sala, double valor) {
        this.id = id;
        this.filme = filme;
        this.datadofilme = datadofilme;
        this.turno = turno;
        this.sala = sala;
        this.valor = valor;
    }

    public String toString() {
        int ano;
        ano = datadofilme.getYear();
        String valorFormatado = String.format("%.2f", valor);
        return """
                %d- %s(%d) |Sala:%d|Turno:%s|Valor:%s R$|""".formatted(id, filme, ano, sala, turno, valorFormatado);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilme() {
        return filme;
    }

    public void setFilme(String filme) {
        this.filme = filme;
    }

    public LocalDate getDatadofilme() {
        return datadofilme;
    }

    public void setDatadofilme(LocalDate datadofilme) {
        this.datadofilme = datadofilme;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}

class Ingresso {
    public Ingresso() {
    }

    public String apresentarValorIngressos(Filme filme) {
        double valor = filme.getValor();
        double turno1 = (valor * 0.5) * 0.9;
        double turno2 = (valor * 0.6) * 0.9;
        double turno3 = (valor * 0.7) * 0.9;
        double turno4 = (valor * 0.8) * 0.9;
        return """
                Preços:
                Menores de 12 anos: R$ %.2f | Turno Vespertino: R$ %.2f
                Entre 12 e 15 anos: R$ %.2f | Turno Vespertino: R$ %.2f
                Entre 16 e 20 anos: R$ %.2f | Turno Vespertino: R$ %.2f
                Maiores de 20 anos: R$ %.2f | Turno Vespertino: R$ %.2f
                """.formatted(valor * 0.5, turno1,
                valor * 0.6, turno2,
                valor * 0.7, turno3,
                valor * 0.8, turno4);
    }
}

class Cliente {
    String nome;
    int idade;
    int id;

    public Cliente(String nome, int idade, int id) {
        this.nome = nome;
        this.idade = idade;
        this.id = id;
    }


    public String toString() {
        return """
                %d - %s (%d anos)""".formatted(id, nome, idade);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getIdade() {
        return idade;
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            Logger logger = Logger.getLogger(MainScreen.class.getName());
            logger.severe("An error occurred while setting the look and feel:");
            logger.severe(e.getMessage());
            logger.log(Level.SEVERE, "Exception details:", e);
        }
        SwingUtilities.invokeLater(MainScreen::new);
    }
}

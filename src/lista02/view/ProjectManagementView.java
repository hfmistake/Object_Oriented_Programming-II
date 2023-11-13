package lista02.view;

import lista02.controller.ControllerFactory;
import lista02.controller.ProjetoController;
import lista02.model.Projeto;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.swing.UIManager;

public class ProjectManagementView extends JFrame {
    public JPanel windowPanel;
    public JPanel mainPanel;
    public JScrollPane tableScrollPanel;
    public JPanel formPanel;
    public JTextArea tituloField;
    public JLabel tituloLabel;
    public JPanel titlePanel;
    public JLabel titleLabel;
    public JTextField eventoField;
    public JLabel eventoLabel;
    public JLabel coordenadorLabel;
    public JTextField coordenadorField;
    public JLabel campusLabel;
    public JTextField campusField;
    public JLabel estudanteLabel;
    public JTextField estudanteField;
    public JTextField matriculaField;
    public JTextField cpfField;
    public JLabel matriculaLabel;
    public JLabel cpfLabel;
    public JTextField bancoField;
    public JLabel bancoLabel;
    public JTextField emailField;
    public JLabel emailLabel;
    public JTextField contaField;
    public JLabel contaLabel;
    public JTextField celularField;
    public JLabel celularLabel;
    public JLabel agenciaLabel;
    public JTextField agenciaField;
    public JPanel formLeft;
    public JPanel formRight;
    public JPanel buttonPanel;
    public JButton novoButton;
    public JButton editarButton;
    public JButton excluirButton;
    public JButton salvarButton;
    public JButton cancelarButton;
    public JButton buscarButton;
    public JTable projectTable;
    public JLabel contextLabel;

    private final ProjetoController projetoController;

    public Map<Integer, Integer> rowToIdMap = new HashMap<>();
    static String[] colunas = {"Título", "Evento", "Coordenador", "Campus", "Estudante",
            "Matrícula", "CPF", "Banco", "Email", "Conta", "Celular", "Agência"};
    static DefaultTableModel tableModel = new DefaultTableModel(colunas, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public void updateTable(List<Projeto> projectList) {
        tableModel.setRowCount(0);
        int row = 0;
        for (Projeto p : projectList) {
            tableModel.addRow(new Object[]{p.titulo(), p.evento(), p.coordenador(), p.campus(),
                    p.estudante(), p.matricula(), p.cpf(), p.banco(), p.email(), p.conta(),
                    p.celular(), p.agencia()});
            rowToIdMap.put(row, p.id());
            row++;
        }
    }

    public List<JTextComponent> getFormFields() {
        return List.of(tituloField, eventoField, coordenadorField, campusField, estudanteField, matriculaField,
                cpfField, bancoField, emailField, contaField, celularField, agenciaField);
    }

    static class MultiLineTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            JTextArea textArea = new JTextArea();
            textArea.setText((String) value);
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setOpaque(true);
            textArea.setEditable(false);
            if (isSelected) {
                textArea.setBackground(UIManager.getColor("Table.selectionBackground"));
                textArea.setForeground(table.getSelectionForeground());
            } else {
                textArea.setBackground(table.getBackground());
                textArea.setForeground(table.getForeground());
            }
            textArea.setFont(UIManager.getFont("Label.font"));
            textArea.setBorder(UIManager.getBorder("Label.border"));
            return textArea;
        }
    }

    public ProjectManagementView() {
        this.projetoController = ControllerFactory.createProjetoController(this);
        setupComponents();
        projetoController.updateTableFromDatabase();
    }
    public void setupComponents() {
        setupListeners();
        setupUI();
        setupTable();
    }

    public void setupListeners() {
        novoButton.addActionListener(e -> projetoController.novoProjeto());
        editarButton.addActionListener(e -> projetoController.editarProjeto());
        excluirButton.addActionListener(e -> projetoController.excluirProjeto());
        salvarButton.addActionListener(e -> projetoController.salvarProjeto());
        cancelarButton.addActionListener(e -> projetoController.cancelarProjeto());
        buscarButton.addActionListener(e -> projetoController.buscarProjeto());
    }

    public void setupUI() {
        this.setTitle("Gerenciamento de Projetos");
        this.setContentPane(windowPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        contextLabel.setText("Contexto: Visualizando Projetos");
        setupTituloTextUI();
    }

    public void setupTituloTextUI() {
        tituloField.setBorder(UIManager.getBorder("TextField.border"));
        tituloField.addPropertyChangeListener(evt -> {
            boolean enabled = tituloField.isEnabled();
            tituloField.setOpaque(enabled);
        });
        tituloField.setOpaque(false);
    }

    public void setupTable() {
        projectTable.setModel(tableModel);
        projectTable.getTableHeader().setReorderingAllowed(false);
        projectTable.setRowHeight(80);
        projectTable.getColumnModel().getColumn(0).setCellRenderer(new MultiLineTableCellRenderer());
        projectTable.getColumnModel().getColumn(0).setPreferredWidth(300);
    }
}

package lista02.view;

import lista02.model.Projeto;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
public class ProjectManagementView extends JFrame{
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
    public JTextField campusFiield;
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

    public Map<Integer, Integer> idToRowMap = new HashMap<>();
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
            tableModel.addRow(new Object[]{p.getTitulo(), p.getEvento(), p.getCoordenador(), p.getCampus(),
                    p.getEstudante(), p.getMatricula(), p.getCpf(), p.getBanco(), p.getEmail(), p.getConta(),
                    p.getCelular(), p.getAgencia()});
            idToRowMap.put(row, p.getId());
            row++;
        }
    }

    public List<JTextComponent> getFormFields() {
        return List.of(tituloField, eventoField, coordenadorField, campusFiield, estudanteField, matriculaField,
                cpfField, bancoField, emailField, contaField, celularField, agenciaField);
    }

    static class MultiLineTableCellRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            JTextArea textArea = new JTextArea();
            textArea.setText((String) value);
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setOpaque(false);
            textArea.setEditable(false);
            textArea.setFocusable(false);
            textArea.setBackground(UIManager.getColor("Label.background"));
            textArea.setFont(UIManager.getFont("Label.font"));
            textArea.setBorder(UIManager.getBorder("Label.border"));
            return textArea;
        }
    }

    public ProjectManagementView() {
        contextLabel.setText("Contexto: Visualizando Projetos");
        tituloField.setBorder(UIManager.getBorder("TextField.border"));
        tituloField.addPropertyChangeListener(evt -> {
            boolean enabled = tituloField.isEnabled();
            tituloField.setOpaque(enabled);
        });
        projectTable.setModel(tableModel);
        projectTable.getTableHeader().setReorderingAllowed(false);
        projectTable.setRowHeight(80);
        projectTable.getColumnModel().getColumn(0).setCellRenderer(new MultiLineTableCellRenderer());
        projectTable.getColumnModel().getColumn(0).setPreferredWidth(300);
        this.setTitle("Gerenciamento de Projetos");
        this.setContentPane(windowPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}

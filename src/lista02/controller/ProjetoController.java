package lista02.controller;

import lista02.model.*;
import lista02.view.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import java.util.*;
import lista02.controller.ReportController;

public class ProjetoController {
    private final ProjetoDAO projetoDAO;
    private final ProjectManagementView projectManagementView;

    public ProjetoController(ProjetoDAO projetoDAO, ProjectManagementView projectManagementView) {
        this.projetoDAO = projetoDAO;
        this.projectManagementView = projectManagementView;
    }

    public void showProjectManagementView() {
        updateTableFromDatabase();
        projectManagementView.setLocationRelativeTo(null);
        projectManagementView.setVisible(true);
    }

    public void updateTableFromDatabase() {
        List<Projeto> projetos = projetoDAO.listar();
        projectManagementView.updateTable(projetos);
    }

    private Projeto fieldToObject(List<JTextComponent> formFields, int id) {
        return Projeto.criarProjeto(formFields, id);
    }

    public void novoProjeto() {
        List<JTextComponent> formfields = projectManagementView.getFormFields();
        for (JTextComponent field : formfields) {
            field.setEnabled(true);
            field.setText("");
        }
        projectManagementView.contextLabel.setText("Contexto: Criando Novo Projeto");
        updateTableFromDatabase();
    }

    public void editarProjeto() {
        int selectedRow = projectManagementView.projectTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null,
                    "Selecione um projeto para editar.");
            return;
        }
        int id = projectManagementView.rowToIdMap.get(selectedRow);
        DefaultTableModel model = (DefaultTableModel) projectManagementView.projectTable.getModel();
        List<JTextComponent> formFields = projectManagementView.getFormFields();
        for (int col = 0; col < formFields.size(); col++) {
            formFields.get(col).setText(model.getValueAt(selectedRow, col).toString());
        }
        formFields.forEach(field -> field.setEnabled(true));
        projectManagementView.contextLabel.setText("Contexto: Editando projeto " + id);
    }

    public void cancelarProjeto() {
        List<JTextComponent> formfields = projectManagementView.getFormFields();
        for (JTextComponent field : formfields) {
            field.setEnabled(false);
            field.setText("");
        }
        projectManagementView.contextLabel.setText("Contexto: Visualizando Projetos");
        updateTableFromDatabase();
    }

    public void salvarProjeto() {
        if (projectManagementView.contextLabel.getText().equals("Contexto: Criando Novo Projeto")) {
            List<JTextComponent> formFields = projectManagementView.getFormFields();
            int id = projetoDAO.trackId() + 1;
            for (JTextComponent field : formFields) {
                if (field.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Todos os campos devem ser preenchidos.");
                    return;
                }
            }
            Projeto projeto = fieldToObject(formFields, id);
            projetoDAO.adicionar(projeto);
            updateTableFromDatabase();
            formFields.forEach(field -> field.setText(""));
            formFields.forEach(field -> field.setEnabled(false));
            projectManagementView.contextLabel.setText("Contexto: Visualizando Projetos");
        } else if (projectManagementView.contextLabel.getText().contains("Editando projeto")) {
            String contexto = projectManagementView.contextLabel.getText();
            int id = Integer.parseInt(contexto.substring(contexto.lastIndexOf(" ") + 1));
            List<JTextComponent> formFields = projectManagementView.getFormFields();
            for (JTextComponent field : formFields) {
                if (field.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Todos os campos devem ser preenchidos.");
                    return;
                }
            }
            Projeto projeto = fieldToObject(formFields, id);
            projetoDAO.atualizar(id, projeto);
            projectManagementView.contextLabel.setText("Contexto: Visualizando Projetos");
            formFields.forEach(field -> field.setText(""));
            formFields.forEach(field -> field.setEnabled(false));
            updateTableFromDatabase();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Você não pode salvar neste contexto.");
        }
    }

    public void excluirProjeto() {
        int selectedRow = projectManagementView.projectTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null,
                    "Selecione um projeto para excluir.");
            return;
        }
        int id = projectManagementView.rowToIdMap.get(selectedRow);
        int opcao = JOptionPane.showOptionDialog(null,
                "Tem certeza que deseja excluir o projeto selecionado ?",
                "Projeto " + id,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Sim", "Não"},
                "Não");

        if (opcao == JOptionPane.YES_OPTION) {
            projetoDAO.remover(id);
            updateTableFromDatabase();
        }
    }

    public void buscarProjeto() {
        String titulo = JOptionPane.showInputDialog(null, "Digite o título do projeto: ",
                "Busca", JOptionPane.QUESTION_MESSAGE);
        if (titulo == null) {
            return;
        }
        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "O título do projeto não pode ser vazio.");
            return;
        }
        List<Projeto> projetos = projetoDAO.pesquisar(titulo);
        if (projetos == null) {
            JOptionPane.showMessageDialog(null,
                    "Projeto não encontrado.");
            return;
        }
        projectManagementView.updateTable(projetos);
        projectManagementView.contextLabel.setText("Contexto: Buscando por " + "\"" + titulo + "\"");
        List<JTextComponent> formFields = projectManagementView.getFormFields();
        formFields.forEach(field -> field.setText(""));
        formFields.forEach(field -> field.setEnabled(false));
    }

    public void callReport() {
        int selectedRow = projectManagementView.projectTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null,
                    "Selecione um projeto para gerar o relatório.");
            return;
        }
        int selectedid = projectManagementView.rowToIdMap.get(selectedRow);
        ReportController reportController = new ReportController();
        reportController.report(selectedid);
    }
}

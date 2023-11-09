package lista02.controller;

import lista02.model.ProjetoDAO;
import lista02.model.Projeto;
import lista02.view.LoginView;
import lista02.view.ProjectManagementView;
import lista02.view.MainView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProjetoController {
    private final ProjetoDAO projetoDAO;
    private final ProjectManagementView projectManagementView;
    private final LoginView loginView;
    private final MainView mainView;

    public ProjetoController(ProjetoDAO projetoDAO, ProjectManagementView projectManagementView
            , LoginView loginView, MainView mainView) {
        this.projetoDAO = projetoDAO;
        this.projectManagementView = projectManagementView;
        this.loginView = loginView;
        this.mainView = mainView;

        loginView.limparButton.addActionListener(e -> {
            loginView.loginField.setText("");
            loginView.senhaField.setText("");
        });

        loginView.loginButton.addActionListener(e -> {
            String login = loginView.loginField.getText();
            String senha = new String(loginView.senhaField.getPassword());
            if (projetoDAO.authenticate(login, senha)) {
                loginView.setVisible(false);
                mainView.userLabel.setText("User: " + login);
                mainView.dataLabel.setText("Data: " + SimpleDateFormat.getDateInstance().format(new Date()));
                showMainView();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Usuário ou senha incorretos.");
            }
        });



        mainView.cadastrarItem.addActionListener(e -> showProjectManagementView());

        projectManagementView.novoButton.addActionListener(e -> {
            List<JTextComponent> formfields = projectManagementView.getFormFields();
            for (JTextComponent field : formfields) {
                field.setEnabled(true);
                field.setText("");
            }
            projectManagementView.contextLabel.setText("Contexto: Criando Novo Projeto");
            updateTableFromDatabase();
        });
        projectManagementView.cancelarButton.addActionListener(e -> {
            List<JTextComponent> formfields = projectManagementView.getFormFields();
            for (JTextComponent field : formfields) {
                field.setEnabled(false);
                field.setText("");
            }
            projectManagementView.contextLabel.setText("Contexto: Visualizando Projetos");
            updateTableFromDatabase();
        });
        projectManagementView.editarButton.addActionListener(e -> {
            int selectedRow = projectManagementView.projectTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(null,
                        "Selecione um projeto para editar.");
                return;
            }
            int id = projectManagementView.idToRowMap.get(selectedRow);
            DefaultTableModel model = (DefaultTableModel) projectManagementView.projectTable.getModel();
            List<JTextComponent> formFields = projectManagementView.getFormFields();
            for (int col = 0; col < formFields.size(); col++) {
                formFields.get(col).setText(model.getValueAt(selectedRow, col).toString());
            }
            formFields.forEach(field -> field.setEnabled(true));
            projectManagementView.contextLabel.setText("Contexto: Editando projeto " + id);
        });
        projectManagementView.excluirButton.addActionListener(e -> {
            int selectedRow = projectManagementView.projectTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(null,
                        "Selecione um projeto para excluir.");
                return;
            }
            int id = projectManagementView.idToRowMap.get(selectedRow);
            int opcao = JOptionPane.showOptionDialog(null,
                    "Tem certeza que deseja excluir o projeto " + id + "?",
                    "Excluir projeto",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[]{"Sim", "Não"}, // Botões personalizados em português
                    "Não");

            if (opcao == JOptionPane.YES_OPTION) {
                projetoDAO.remover(id);
                updateTableFromDatabase();
            }
        });
        projectManagementView.salvarButton.addActionListener(e -> {
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
        });
        projectManagementView.buscarButton.addActionListener(e -> {
            // nome da option pane = busca
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
        });
    }

    public void showLoginView() {
        loginView.setVisible(true);
    }

    public void showMainView() {
        mainView.setVisible(true);
    }

    public void showProjectManagementView() {
        updateTableFromDatabase();
        projectManagementView.setLocationRelativeTo(null);
        projectManagementView.setVisible(true);
    }
    private void updateTableFromDatabase() {
        List<Projeto> projetos = projetoDAO.listar();
        projectManagementView.updateTable(projetos);
    }

    private Projeto fieldToObject(List<JTextComponent> formFields, int id) {
        return new Projeto(
                id,
                formFields.get(0).getText(),
                formFields.get(1).getText(),
                formFields.get(2).getText(),
                formFields.get(3).getText(),
                formFields.get(4).getText(),
                formFields.get(5).getText(),
                formFields.get(6).getText(),
                formFields.get(7).getText(),
                formFields.get(8).getText(),
                formFields.get(9).getText(),
                formFields.get(10).getText(),
                formFields.get(11).getText()
        );
    }
}

package lista02.controller;

import javax.swing.*;

import lista02.model.*;
import lista02.view.*;

import java.text.SimpleDateFormat;

public class UserController {
    private final UserDAO userDAO;
    private final LoginView loginView;

    public UserController(UserDAO userDAO, LoginView loginView) {
        this.userDAO = userDAO;
        this.loginView = loginView;
    }

    public void showLoginView() {
        loginView.setVisible(true);
    }

    public void closeLoginView() {
        loginView.dispose();
    }

    public void loginRequest() {
        String login = loginView.loginField.getText();
        String senha = new String(loginView.senhaField.getPassword());
        if (login.isBlank() || senha.isBlank()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
            return;
        }

        if (!userDAO.authenticate(login, senha)) {
            JOptionPane.showMessageDialog(null, "Login ou senha incorretos.");
            return;
        }
        JOptionPane.showMessageDialog(null, "Login efetuado com sucesso.");
        loginView.getUserController().closeLoginView();
        MainView mainView = new MainView();
        mainView.getMainController().showMainView();
        mainView.userLabel.setText("User: " + login);
        mainView.dataLabel.setText("Data: " + new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()));
    }
}
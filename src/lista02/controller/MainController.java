package lista02.controller;

import lista02.view.*;

public class MainController {
    private final MainView mainView;

    public MainController(MainView mainView) {
        this.mainView = mainView;
    }

    public void showMainView() {
        mainView.setVisible(true);
    }

    public void closeMainView() {
        mainView.dispose();
    }

    public void logout() {
        mainView.getMainController().closeMainView();
        LoginView loginView = new LoginView();
        loginView.getUserController().showLoginView();
    }

    public void callProjectManagementView() {
        ProjectManagementView projectManagementView = new ProjectManagementView();
        projectManagementView.getProjetoController().showProjectManagementView();
    }
}

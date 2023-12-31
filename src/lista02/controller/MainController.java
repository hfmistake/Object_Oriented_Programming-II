package lista02.controller;

import lista02.model.User;
import lista02.view.*;

public class MainController {
    private final MainView mainView;
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public MainController(User currentUser,MainView mainView) {
        this.mainView = mainView;
        setCurrentUser(currentUser);
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

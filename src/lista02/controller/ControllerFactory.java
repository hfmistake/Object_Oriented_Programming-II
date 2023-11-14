package lista02.controller;

import lista02.view.*;
import lista02.model.*;

public class ControllerFactory {
    public static UserController createUserController(LoginView loginView) {
        return new UserController(new UserDAOmySQL(), loginView);
    }

    public static ProjetoController createProjetoController(ProjectManagementView projectManagementView) {
        return new ProjetoController(new ProjetoDAOmySQL(), projectManagementView);
    }

    public static MainController createMainController(MainView mainView) {
        return new MainController(mainView);
    }
}

package lista02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOmySQL implements UserDAO {
    private final Connection connection = DatabaseManager.getInstance().getConnection();
    @Override
    public boolean authenticate(String login, String senha) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM usuario WHERE login = ? AND senha = ?");
            statement.setString(1, login);
            statement.setString(2, senha);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            System.out.println("Erro ao autenticar usuário no banco de dados.");
            return false;
        }
    }

    @Override
    public User getUser(String login) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM usuario WHERE login = ?");
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            result.next();
            return new User(result.getInt("id"), result.getString("login"), result.getString("senha"));
        } catch (SQLException e) {
            System.out.println("Erro ao obter usuário no banco de dados.");
            return null;
        }
    }
}

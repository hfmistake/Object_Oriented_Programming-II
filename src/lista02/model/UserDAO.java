package lista02.model;

public interface UserDAO {
    boolean authenticate(String login, String senha);

    User getUser(String login);
}

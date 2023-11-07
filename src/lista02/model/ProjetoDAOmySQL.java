package lista02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class ProjetoDAOmySQL implements ProjetoDAO {
    private static final Connection connection = DatabaseManager.getInstance().getConnection();

    @Override
    public void adicionar(Projeto projeto) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO projeto (titulo, evento, coordenador, campus, estudante, matricula, cpf, banco, email, conta, celular, agencia) VALUES (? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?)");
            setInfo(projeto, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar projeto no banco de dados.");
        }
    }

    @Override
    public void remover(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM projeto WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao remover projeto no banco de dados.");
        }
    }

    @Override
    public void atualizar(int id, Projeto projeto) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE projeto SET titulo = ?, evento = ?, coordenador = ?, campus = ?, estudante = ?, matricula = ?, cpf = ?, banco = ?, email = ?, conta = ?, celular = ?, agencia = ? WHERE id = ?");
            setInfo(projeto, statement);
            statement.setInt(13, projeto.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar projeto no banco de dados.");
        }
    }
    @Override
    public int trackId() {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT MAX(id) FROM projeto");
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getInt(1);
        } catch (SQLException e) {
            System.out.println("Erro ao obter id do projeto no banco de dados.");
            return -1;
        }
    }
    private void setInfo(Projeto projeto, PreparedStatement statement) throws SQLException {
        statement.setString(1, projeto.getTitulo());
        statement.setString(2, projeto.getEvento());
        statement.setString(3, projeto.getCoordenador());
        statement.setString(4, projeto.getCampus());
        statement.setString(5, projeto.getEstudante());
        statement.setString(6, projeto.getMatricula());
        statement.setString(7, projeto.getCpf());
        statement.setString(8, projeto.getBanco());
        statement.setString(9, projeto.getEmail());
        statement.setString(10, projeto.getConta());
        statement.setString(11, projeto.getCelular());
        statement.setString(12, projeto.getAgencia());
    }

    @Override
    public List<Projeto> pesquisar(String titulo) {
        List<Projeto> projetos = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM projeto WHERE titulo LIKE ?");
            statement.setString(1,  "%" + titulo + "%");
            return getProjetos(projetos, statement);
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar projeto no banco de dados.");
            return null;
        }
    }

    @Override
    public List<Projeto> listar() {
        List<Projeto> projetos = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM projeto");
            return getProjetos(projetos, statement);
        } catch (SQLException e) {
            System.out.println("Erro ao listar projetos no banco de dados.");
            return null;
        }
    }

    private List<Projeto> getProjetos(List<Projeto> projetos, PreparedStatement statement) throws SQLException {
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            Projeto projeto = new Projeto(
                    result.getInt("id"),
                    result.getString("titulo"),
                    result.getString("evento"),
                    result.getString("coordenador"),
                    result.getString("campus"),
                    result.getString("estudante"),
                    result.getString("matricula"),
                    result.getString("cpf"),
                    result.getString("banco"),
                    result.getString("email"),
                    result.getString("conta"),
                    result.getString("celular"),
                    result.getString("agencia")
            );
            projetos.add(projeto);
        }
        return projetos;
    }

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
}

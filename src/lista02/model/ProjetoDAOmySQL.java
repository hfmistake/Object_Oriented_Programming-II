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
            statement.setInt(13, projeto.id());
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
        statement.setString(1, projeto.titulo());
        statement.setString(2, projeto.evento());
        statement.setString(3, projeto.coordenador());
        statement.setString(4, projeto.campus());
        statement.setString(5, projeto.estudante());
        statement.setString(6, projeto.matricula());
        statement.setString(7, projeto.cpf());
        statement.setString(8, projeto.banco());
        statement.setString(9, projeto.email());
        statement.setString(10, projeto.conta());
        statement.setString(11, projeto.celular());
        statement.setString(12, projeto.agencia());
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
}

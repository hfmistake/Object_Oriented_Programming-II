package lista02.model;

import java.util.List;

public interface ProjetoDAO {
    void adicionar(Projeto projeto);
    void remover(int id);
    void atualizar(int id, Projeto projeto);
    List<Projeto> pesquisar(String titulo);
    List<Projeto> listar();
    int trackId();
    boolean authenticate(String login, String senha);
}

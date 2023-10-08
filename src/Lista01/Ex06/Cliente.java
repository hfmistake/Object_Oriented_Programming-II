package Lista01.Ex06;

public class Cliente {
    private String nome;
    private int idade;
    private int id;

    public Cliente(String nome, int idade, int id) {
        this.nome = nome;
        this.idade = idade;
        this.id = id;
    }


    public String toString() {
        return """
                %d - %s (%d anos)""".formatted(id,nome,idade);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getIdade() {
        return idade;
    }
}

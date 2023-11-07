package lista02.model;

public class Projeto {
    int id;
    String titulo;
    String evento;
    String coordenador;
    String campus;
    String estudante;
    String matricula;
    String cpf;
    String banco;
    String email;
    String conta;
    String celular;
    String agencia;

    public Projeto(int id, String titulo, String evento, String coordenador, String campus, String estudante, String matricula,
                   String cpf, String banco, String email, String conta, String celular, String agencia) {
        this.id = id;
        this.titulo = titulo;
        this.evento = evento;
        this.coordenador = coordenador;
        this.campus = campus;
        this.estudante = estudante;
        this.matricula = matricula;
        this.cpf = cpf;
        this.banco = banco;
        this.email = email;
        this.conta = conta;
        this.celular = celular;
        this.agencia = agencia;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEvento() {
        return evento;
    }

    public String getCoordenador() {
        return coordenador;
    }

    public String getCampus() {
        return campus;
    }

    public String getEstudante() {
        return estudante;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCpf() {
        return cpf;
    }

    public String getBanco() {
        return banco;
    }

    public String getEmail() {
        return email;
    }

    public String getConta() {
        return conta;
    }

    public String getCelular() {
        return celular;
    }

    public String getAgencia() {
        return agencia;
    }
}

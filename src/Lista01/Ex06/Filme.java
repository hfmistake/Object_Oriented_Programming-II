package Lista01.Ex06;

import java.time.LocalDate;


public class Filme {
    int id;
    String filme;
    LocalDate datadofilme;
    String turno;
    int sala;
    double valor;

    public Filme(int id, String filme, LocalDate datadofilme, String turno, int sala, double valor) {
        this.id = id;
        this.filme = filme;
        this.datadofilme = datadofilme;
        this.turno = turno;
        this.sala = sala;
        this.valor = valor;
    }

    public String toString() {
        int dia,mes,ano;
        dia = datadofilme.getDayOfMonth();
        mes = datadofilme.getMonthValue();
        ano = datadofilme.getYear();
        String valorFormatado = String.format("%.2f", valor);
        return """
                %d- %s(%d) |Sala:%d|Turno:%s|Valor:%s R$|""".formatted(id,filme,ano,sala,turno,valorFormatado);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilme() {
        return filme;
    }

    public void setFilme(String filme) {
        this.filme = filme;
    }

    public LocalDate getDatadofilme() {
        return datadofilme;
    }

    public void setDatadofilme(LocalDate datadofilme) {
        this.datadofilme = datadofilme;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}

package Lista01.Ex05;

public class Livro {
    String title;
    int qntPaginas;

    public Livro(String t, int qnt) {
        this.title = t;
        this.qntPaginas = qnt;
    }

    public String toString() {
        return """
                %s (%d)""".formatted(title,qntPaginas);
    }
}

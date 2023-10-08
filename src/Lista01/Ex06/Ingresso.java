package Lista01.Ex06;

public class Ingresso {
    public Ingresso() {
    }
    public String apresentarValorIngressos(Filme filme) {
        double valor = filme.getValor();
        double turno1 = (valor * 0.5) * 0.9;
        double turno2 = (valor * 0.6) * 0.9;
        double turno3 = (valor * 0.7) * 0.9;
        double turno4 = (valor * 0.8) * 0.9;
        return """
                Preços:
                Menores de 12 anos: R$ %.2f | Turno Vespertino: R$ %.2f
                Entre 12 e 15 anos: R$ %.2f | Turno Vespertino: R$ %.2f
                Entre 16 e 20 anos: R$ %.2f | Turno Vespertino: R$ %.2f
                Maiores de 20 anos: R$ %.2f | Turno Vespertino: R$ %.2f
                """.formatted( valor * 0.5, turno1,
                                valor * 0.6, turno2,
                                valor * 0.7, turno3,
                                valor * 0.8, turno4);
    }
}

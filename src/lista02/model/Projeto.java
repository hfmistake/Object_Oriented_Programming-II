package lista02.model;

import javax.swing.text.JTextComponent;
import java.util.List;

public record Projeto(int id, String titulo, String evento, String coordenador, String campus, String estudante,
                      String matricula, String cpf, String banco, String email, String conta, String celular,
                      String agencia) {
    public static Projeto criarProjeto(List<JTextComponent> formFields, int id) {
        return new Projeto(
                id,
                formFields.get(0).getText(),
                formFields.get(1).getText(),
                formFields.get(2).getText(),
                formFields.get(3).getText(),
                formFields.get(4).getText(),
                formFields.get(5).getText(),
                formFields.get(6).getText(),
                formFields.get(7).getText(),
                formFields.get(8).getText(),
                formFields.get(9).getText(),
                formFields.get(10).getText(),
                formFields.get(11).getText()
        );
    }
}

package app.view;

import app.exception.EntradaInvalidaException;
import app.exception.ListaVaziaException;
import app.exception.NumeroForaDoIntervaloException;
import app.model.dto.TrilhaMentoriaDTO;
import app.service.interfaces.ParticipanteService;
import app.util.ConsoleInput;

import java.util.InputMismatchException;

public class CadastroTrilha {

    private final ConsultaView consultaView;
    private final int LIMITE_SKILLS_TRILHA = 3;

    public CadastroTrilha(ConsultaView consultaView) {
        this.consultaView = consultaView;
    }

    public TrilhaMentoriaDTO cadastrarTrilha() {
        TrilhaMentoriaDTO dto = new TrilhaMentoriaDTO();

        if (!consultaView.existemParticipantesParaTrilha()) {
            System.out.println("""
                    \nNão há participantes suficientes cadastrados nesta sessão.
                    Para criar uma trilha, cadastre pelo menos 1 mentor e 1 mentorado
                    na opção "1- Cadastrar Participante" do menu principal.
                    """);
            return null;
        }
        try {
            consultaView.consultarMentoradosEmMemoria();
            int quantidadeDeMentorados = lerQuantidade("Informe a quantidade de mentorados na trilha: ");

            for (int i = 0; i < quantidadeDeMentorados; i++) {
                System.out.print("Digite o ID do mentorado " + (i + 1) + ": ");
                dto.addIdMentorado(ConsoleInput.lerId());
            }

            consultaView.consultarMentoresEmMemoria();
            System.out.print("Digite o ID do mentor: ");
            dto.setIdMentor(ConsoleInput.lerId());

            System.out.print("Digite o nome da trilha: ");
            dto.setNome(ConsoleInput.lerTexto());

            dto.setCicloEmMeses(lerQuantidade("Informe o ciclo da trilha em meses: "));

        } catch (ListaVaziaException e) {
            System.out.println("Não foi possível criar a trilha: " + e.getMessage());
        } catch (InputMismatchException e){
            System.out.println("Caractere inválido!");
        }
        return dto;
    }

    private int lerQuantidade(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return ConsoleInput.lerNumeroPositivo();
            } catch (NumeroForaDoIntervaloException | EntradaInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

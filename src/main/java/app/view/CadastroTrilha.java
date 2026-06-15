package app.view;

import app.exception.ListaVaziaException;
import app.model.dto.TrilhaMentoriaDTO;
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

        try {
            consultaView.consultarMentoradosEmMemoria();
            System.out.print("Informe a quantidade de mentorados na trilha: ");
            int quantidadeDeMentorados = ConsoleInput.lerNumeroPositivo();

            for (int i = 0; i < quantidadeDeMentorados; i++) {
                System.out.print("Digite o ID do mentorado " + (i + 1) + ": ");
                dto.addIdMentorado(ConsoleInput.lerId());
            }

            consultaView.consultarMentoresEmMemoria();
            System.out.print("Digite o ID do mentor: ");
            dto.setIdMentor(ConsoleInput.lerId());

            System.out.print("Digite o nome da trilha: ");
            dto.setNome(ConsoleInput.lerTexto());

            System.out.print("Informe o ciclo da trilha em meses: ");
            dto.setCicloEmMeses(ConsoleInput.lerNumeroPositivo());

        } catch (ListaVaziaException e) {
            System.out.println("Não foi possível criar a trilha: " + e.getMessage());
        } catch (InputMismatchException e){
            System.out.println("Caractere inválido!");
        }
        return dto;
    }
}

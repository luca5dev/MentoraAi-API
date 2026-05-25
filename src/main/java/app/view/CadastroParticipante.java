package app.view;


import app.model.dto.UsuarioCadastroDTO;
import app.model.enums.NivelSenioridade;
import app.service.useCase.SkillUseCase;
import app.util.ConsoleInput;

public class CadastroParticipante {

    public static UsuarioCadastroDTO cadastrarParticipante() {
            UsuarioCadastroDTO dto = new UsuarioCadastroDTO();
            System.out.println("1- Mentor/2- Mentorado");
            dto.setOpcao(ConsoleInput.lerNumero());

            System.out.println("Nome: ");
            dto.setNome(ConsoleInput.lerTexto());

            System.out.println("Nivel de Senioridade (1- Junior 2- Pleno 3- Senior 4- Especialista): ");
            dto.setNivelSenioridade(NivelSenioridade.buscaId(ConsoleInput.lerNumero()));

            System.out.println("Qual o valor do pagamento em horas?");
            dto.setValorHora(ConsoleInput.lerValor());

            return dto;
    }

}

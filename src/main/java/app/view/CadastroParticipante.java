package app.view;


import app.exception.CampoVazioException;
import app.exception.ValorDaHoraInvalidoException;
import app.model.dto.UsuarioCadastroDTO;
import app.model.enums.NivelSenioridade;
import app.service.useCase.SenioridadeUseCase;
import app.service.useCase.SkillUseCase;
import app.util.ConsoleInput;

public class CadastroParticipante {

    public static UsuarioCadastroDTO cadastrarParticipante() {

            UsuarioCadastroDTO dto = new UsuarioCadastroDTO();
            SenioridadeUseCase senioridadeUseCase = new SenioridadeUseCase();

            System.out.print("1- Mentor/2- Mentorado: ");
            dto.setOpcao(ConsoleInput.lerNumero());

            System.out.print("Nome: ");
            dto.setNome(ConsoleInput.lerTexto());

            System.out.print("Nivel de Senioridade (1- Junior 2- Pleno 3- Senior 4- Especialista): ");
            dto.setNivelSenioridade(senioridadeUseCase.buscaSenioridade(ConsoleInput.lerNumero()));

            double valorHora;

            while (true) {
                    try {
                            System.out.print("Qual o valor do pagamento em horas? ");
                            valorHora = ConsoleInput.lerValor();
                            break;
                    } catch (ValorDaHoraInvalidoException | CampoVazioException e) {
                            System.out.println(e.getMessage());
                    }
            }
            dto.setValorHora(valorHora);

            return dto;
    }
}

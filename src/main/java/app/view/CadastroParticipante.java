package app.view;

import app.exception.CampoVazioException;
import app.exception.EntradaInvalidaException;
import app.exception.ValorDaHoraInvalidoException;
import app.model.dto.UsuarioCadastroDTO;
import app.model.enums.NivelSenioridade;
import app.service.useCase.SenioridadeUseCase;
import app.util.ConsoleInput;

public class CadastroParticipante {

    public static UsuarioCadastroDTO coletarDadosParticipante() {

            UsuarioCadastroDTO dto = new UsuarioCadastroDTO();
            SenioridadeUseCase senioridadeUseCase = new SenioridadeUseCase();

            while (true) {
                    try {
                            System.out.print("1- Mentor | 2- Mentorado: ");
                            int opcao = ConsoleInput.lerNumero();

                            if (opcao != 1 && opcao != 2) {
                                    System.out.println("\nOpção inválida! Por favor, escolha 1 para Mentor ou 2 para Mentorado.\n");
                                    continue;
                            }
                            dto.setOpcao(opcao);
                            break;
                    } catch (EntradaInvalidaException e) {
                            System.out.println(e.getMessage());
                    }
            }

            while (true) {
                    try {
                            System.out.print("Nome: ");
                            dto.setNome(ConsoleInput.lerTexto());
                            break;
                    } catch (CampoVazioException e) {
                            System.out.println(e.getMessage());
                    }
            }

            while (true) {
                    try {
                            System.out.print("Nivel de Senioridade (1- Junior, 2- Pleno, 3- Senior, 4- Especialista): ");
                            dto.setNivelSenioridade(senioridadeUseCase.buscaSenioridade(ConsoleInput.lerNumero()));
                            break;
                    } catch (CampoVazioException | EntradaInvalidaException e) {
                            System.out.println(e.getMessage());
                    }
            }

            while (true) {
                    try {
                            System.out.print("Qual o valor da hora do participante? ");
                            dto.setValorHora(ConsoleInput.lerValor());
                            break;
                    } catch (ValorDaHoraInvalidoException | CampoVazioException | EntradaInvalidaException e) {
                            System.out.println(e.getMessage());
                    }
            }

            if (dto.getOpcao() == 2) {
                    while (true) {
                            try {
                                    System.out.print("Horas dedicadas mensais do mentorado: ");
                                    dto.setHorasDedicadas(ConsoleInput.lerValor());
                                    break;
                            } catch (ValorDaHoraInvalidoException | CampoVazioException | EntradaInvalidaException e) {
                                    System.out.println(e.getMessage());
                            }
                    }
            }
            return dto;
    }
}

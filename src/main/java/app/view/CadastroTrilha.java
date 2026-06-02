package app.view;

import app.exception.LimiteSkillsUltrapassadoException;
import app.exception.ListaVaziaException;
import app.model.dto.TrilhaMentoriaDTO;
import app.service.useCase.SkillUseCase;
import app.util.ConsoleInput;

import java.util.InputMismatchException;

public class CadastroTrilha {

    private final ConsultaView consultaView;
    private final SkillUseCase skillUseCase = new SkillUseCase();

    public CadastroTrilha(ConsultaView consultaView) {
        this.consultaView = consultaView;
    }

    public TrilhaMentoriaDTO cadastrarTrilha() {
        TrilhaMentoriaDTO dto = new TrilhaMentoriaDTO();
        try {
            consultaView.consultarMentoresEmMemoria();
            System.out.print("ID do mentor: ");
            dto.setIdMentor(ConsoleInput.lerId());

            System.out.print("Digite o nome da trilha: ");
            dto.setNome(ConsoleInput.lerTexto());


            System.out.print("Duração em horas: ");
            dto.setDuracaoHoras(ConsoleInput.lerNumeroPositivo());

            //falta criar metodo pra adicionar skills na lista
            // (limte: 3 Skills por trilha)
            System.out.println("Quantidade de Skills (limite: 3)");
            int limite = ConsoleInput.lerNumero();
            if (limite < 0 || limite > 3){
                throw new LimiteSkillsUltrapassadoException("Erro: O minimo de skills é 0 e o máximo deve ser 3");
            } else {
                for (int i = 0; i < limite; i++) {
                    System.out.println("Quais skills a trilha vai ter?");
                    System.out.println("Skills: ");
                    System.out.print("1- Java 2- Spring 3- SQL\n");
                    System.out.print("4- Git 5- Docker 6- AWS\n");
                    System.out.print("7- Angular 8- React 9- Postgresql\n");
                    System.out.print("10- HTML 11- CSS\n");
                    skillUseCase.adicionarSkillTrilhaMentoria(dto, skillUseCase.buscaSkill(ConsoleInput.lerNumero()));
                }
            }
        } catch (ListaVaziaException e){
            System.out.println("Não foi possível criar a trilha: " + e.getMessage());
        } catch (InputMismatchException e){
            System.out.println("Caractere inválido!");
        }


        return dto;
    }
    }

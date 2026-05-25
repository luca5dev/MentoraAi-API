package app.view;

import app.exception.LimiteSkillsUltrapassadoException;
import app.model.dto.TrilhaMentoriaDTO;
import app.model.enums.Skill;
import app.service.useCase.SkillUseCase;
import app.util.ConfirmarContinuar;
import app.util.ConsoleInput;

public class CadastroTrilha {
    public static TrilhaMentoriaDTO cadastrarTrilha() {
        SkillUseCase skillUseCase = new SkillUseCase();
        // To do
        //criar uma condicional de lista de mentores e mentorados,
        // se estiver vazia não da pra criar a trilha
        TrilhaMentoriaDTO dto = new TrilhaMentoriaDTO();

        //ListarMentores
        //Ler o id do Mentor
        System.out.println("Mentor: ");
        dto.setIdMentor(ConsoleInput.lerNumero());

        System.out.print("Digite da trilha: ");
        dto.setNome(ConsoleInput.lerTexto());


        System.out.print("Duração em horas: ");
        dto.setDuracaoHoras(ConsoleInput.lerNumero());

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
                dto.addSkill(skillUseCase.buscaSkill(ConsoleInput.lerNumero()));
            }
        }

        return dto;
    }
    }

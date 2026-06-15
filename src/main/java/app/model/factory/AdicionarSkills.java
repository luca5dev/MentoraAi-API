package app.model.factory;

import app.exception.EntradaInvalidaException;
import app.exception.SkillDuplicadaException;
import app.model.dto.UsuarioCadastroDTO;
import app.model.enums.Skill;
import app.service.useCase.SkillUseCase;
import app.util.ConfirmarContinuar;
import app.util.ConsoleInput;

public class AdicionarSkills {

    SkillUseCase skillUseCase = new SkillUseCase();

    public UsuarioCadastroDTO skillBase(UsuarioCadastroDTO dto) {
        boolean cadastro = true;

        while (cadastro) {
            try {
                System.out.println();
                skillUseCase.listarSkills().forEach(System.out::println);
                System.out.print("Qual skill deseja adicionar? ");

                int opcao = ConsoleInput.lerNumero();
                Skill skillSelecionada = skillUseCase.buscaSkill(opcao);

                dto.adicionarSkill(skillSelecionada);
                cadastro = ConfirmarContinuar.confirmar();

            } catch (EntradaInvalidaException | SkillDuplicadaException e) {
                System.out.println(e.getMessage());
            }
        }
        return dto;
    }

    public UsuarioCadastroDTO skillsParaMentorados(UsuarioCadastroDTO dto) {
        boolean cadastro = true;
        while (cadastro) {
            try {
                System.out.println();
                skillUseCase.listarSkills().forEach(System.out::println);
                System.out.print("Qual Skill o mentorado deseja aprender? ");

                int opcao = ConsoleInput.lerNumero();
                Skill skillSelecionada = skillUseCase.buscaSkill(opcao);

                dto.adicionarSkillDesejada(skillSelecionada);
                cadastro = ConfirmarContinuar.confirmar();
            } catch (EntradaInvalidaException | SkillDuplicadaException e) {
                System.out.println(e.getMessage());
            }
        }
        return dto;
    }
}

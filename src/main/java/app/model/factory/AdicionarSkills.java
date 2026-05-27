package app.model.factory;

import app.model.dto.UsuarioCadastroDTO;
import app.model.enums.Skill;
import app.service.useCase.SkillUseCase;
import app.util.ConfirmarContinuar;
import app.util.ConsoleInput;

public class AdicionarSkills {

    SkillUseCase skillUseCase = new SkillUseCase();

    public UsuarioCadastroDTO skillBase(UsuarioCadastroDTO dto){
        boolean cadastro = true;

        while (cadastro == true){
            System.out.println(skillUseCase.listarSkills());
            System.out.println("Qual deseja adicionar?");
            Skill skillSelecionada = skillUseCase.buscaSkill(ConsoleInput.lerNumero());
            dto.adicionarSkill(skillSelecionada);
            cadastro = ConfirmarContinuar.confirmar();
        }
        return dto;
    }

    public UsuarioCadastroDTO skillsParaMentorados(UsuarioCadastroDTO dto){
        boolean cadastro = true;

        while (cadastro == true){
            skillUseCase.listarSkills();
            System.out.println("Qual o mentorado deseja aprender?");
            Skill skillSelecionada = skillUseCase.buscaSkill(ConsoleInput.lerNumero());
            dto.adicionarSkillDesejada(skillSelecionada);
            cadastro = ConfirmarContinuar.confirmar();
        }
        return dto;
    }
}

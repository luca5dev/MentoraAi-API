package app.service.useCase;

import app.model.entity.ParticipantePrograma;
import app.model.enums.Skill;
import app.service.interfaces.SkillService;

public class SkillUseCase implements SkillService {

    @Override
    public Skill buscaSkill(int id){
        for (Skill skill: Skill.values()){
            if (skill.getId() == id){
                return skill;
            }
        }
        throw new IllegalArgumentException("Id inválido");
    }

    @Override
    public void listarSkills(){
            int i=0;
            for (Skill skills : Skill.values()){
                System.out.print(i+=1);
                System.out.print(": " + skills.name() + " ");
                System.out.println(" ");
            }
    }

    @Override
    public void AdicionarSkill(ParticipantePrograma participantePrograma, int id) {
        Skill skill = buscaSkill(id);
        participantePrograma.adicionarSkill(skill);
    }
}

package app.service.useCase;

import app.model.enums.Skill;
import app.service.interfaces.BuscaSkillsService;

public class BuscaSkillUseCase implements BuscaSkillsService {
    @Override
    public Skill buscaSkill(int id) {
       return Skill.buscaSkill(id);
    }
}

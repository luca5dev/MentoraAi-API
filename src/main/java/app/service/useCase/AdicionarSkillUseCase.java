package app.service.useCase;

import app.model.entity.ParticipantePrograma;
import app.model.enums.Skill;
import app.service.interfaces.AdicionarSkillService;

public class AdicionarSkillUseCase implements AdicionarSkillService {

    private final BuscaSkillUseCase buscaSkillUseCase = new BuscaSkillUseCase();


    @Override
    public void AdicionarSkill(ParticipantePrograma participantePrograma, int id) {
        Skill skill = buscaSkillUseCase.buscaSkill(id);
        participantePrograma.adicionarSkill(skill);
    }

}

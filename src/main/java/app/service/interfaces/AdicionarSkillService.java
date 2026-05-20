package app.service.interfaces;

import app.model.entity.ParticipantePrograma;
import app.model.enums.Skill;

public interface AdicionarSkillService {

    void AdicionarSkill(ParticipantePrograma participantePrograma, int id);
}

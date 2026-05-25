package app.service.interfaces;

import app.model.entity.ParticipantePrograma;
import app.model.enums.Skill;

public interface SkillService {

    Skill buscaSkill(int id);

    void listarSkills();

    void AdicionarSkill(ParticipantePrograma participantePrograma, int id);
}

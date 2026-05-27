package app.service.interfaces;

import app.model.dto.TrilhaMentoriaDTO;
import app.model.entity.ParticipantePrograma;
import app.model.enums.Skill;

import java.util.List;

public interface SkillService {

    Skill buscaSkill(int id);

    List<Skill> listarSkills();

    void AdicionarSkill(ParticipantePrograma participantePrograma, int id);

    void adicionarSkillTrilhaMentoria(TrilhaMentoriaDTO trilhaMentoriaDTO, Skill skill);
}

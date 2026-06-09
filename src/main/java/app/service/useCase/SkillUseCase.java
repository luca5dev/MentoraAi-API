package app.service.useCase;

import app.exception.EntradaInvalidaException;
import app.model.dto.TrilhaMentoriaDTO;
import app.model.entity.ParticipantePrograma;
import app.model.enums.Skill;
import app.service.interfaces.SkillService;

import java.util.List;

public class SkillUseCase implements SkillService {

    @Override
    public Skill buscaSkill(int id) {
        for (Skill skill: Skill.values()){
            if (skill.getId() == id){
                return skill;
            }
        }
        throw new EntradaInvalidaException("Id inválido");
    }

    @Override
    public List<Skill> listarSkills() {
        return List.of(Skill.values());
    }

    @Override
    public void adicionarSkill(ParticipantePrograma participantePrograma, int id) {
        Skill skill = buscaSkill(id);
        participantePrograma.adicionarSkill(skill);
    }

    @Override
    public void adicionarSkillTrilhaMentoria(TrilhaMentoriaDTO trilhaMentoriaDTO, Skill skill) {
        trilhaMentoriaDTO.getSkills().add(skill);
    }
}

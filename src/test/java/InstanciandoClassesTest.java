import app.model.entity.Mentor;
import app.model.entity.ParticipantePrograma;
import app.model.enums.NivelSenioridade;
import app.model.enums.Skill;
import app.service.useCase.SenioridadeUseCase;
import app.service.useCase.SkillUseCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class InstanciandoClassesTest {

    @Test
    public void InstanciandoClasse() {
        List<Skill> skillsParticipante = new ArrayList<>();
        SenioridadeUseCase buscaSenioridadeUseCase = new SenioridadeUseCase();
        SkillUseCase buscaSkillUseCase = new SkillUseCase();

        NivelSenioridade nivel = buscaSenioridadeUseCase.buscaSenioridade(3);
        Skill skill =  buscaSkillUseCase.buscaSkill(1);
        Skill skill2= buscaSkillUseCase.buscaSkill(2);
        skillsParticipante.addAll(Arrays.asList(skill, skill2));

        ParticipantePrograma p = new Mentor("Marcos", nivel, skillsParticipante, 20.0, 2.0);

        p.toString();
    }
}

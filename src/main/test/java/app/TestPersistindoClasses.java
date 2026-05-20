package app;

import app.dao.Persistence;
import app.model.entity.Mentor;
import app.model.entity.ParticipantePrograma;
import app.model.enums.NivelSenioridade;
import app.service.useCase.AdicionarSkillUseCase;
import app.service.useCase.BuscaSenioridadeUseCase;
import org.junit.jupiter.api.Test;

public class TestPersistindoClasses {

    @Test
    public void persistirDados(){
        ParticipantePrograma p = new Mentor();
        Persistence jpa = new Persistence();
        BuscaSenioridadeUseCase buscaSenioridadeUseCase = new BuscaSenioridadeUseCase();
        AdicionarSkillUseCase adicionarSkillUseCase = new AdicionarSkillUseCase();
        p.setNome("Jorge");
        NivelSenioridade nivel = buscaSenioridadeUseCase.buscaSenioridade(2);
        p.setNivelSenioridade(nivel);
        adicionarSkillUseCase.AdicionarSkill(p, 2);
        adicionarSkillUseCase.AdicionarSkill(p, 3);
        adicionarSkillUseCase.AdicionarSkill(p, 4);

        jpa.persist(p);
    }
}

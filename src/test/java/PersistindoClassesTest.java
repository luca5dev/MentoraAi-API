import app.model.entity.Mentor;
import app.model.entity.ParticipantePrograma;
import app.model.enums.NivelSenioridade;
import app.service.useCase.SenioridadeUseCase;
import app.service.useCase.SkillUseCase;
import org.junit.jupiter.api.Test;

public class PersistindoClassesTest {

    @Test
    public void persistirDados(){
        ParticipantePrograma p = new Mentor();
        SenioridadeUseCase buscaSenioridadeUseCase = new SenioridadeUseCase();
        SkillUseCase skillUseCase = new SkillUseCase();
        p.setNome("Jorge");
        NivelSenioridade nivel = buscaSenioridadeUseCase.buscaSenioridade(2);
        p.setNivelSenioridade(nivel);
        skillUseCase.adicionarSkill(p, 2);
        skillUseCase.adicionarSkill(p, 3);
        skillUseCase.adicionarSkill(p, 4);

    }
}

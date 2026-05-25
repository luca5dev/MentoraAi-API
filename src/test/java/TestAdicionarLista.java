import app.model.entity.Mentor;
import app.model.entity.ParticipantePrograma;
import app.service.useCase.SkillUseCase;
import org.junit.jupiter.api.Test;

public class TestAdicionarLista {
    SkillUseCase skill = new SkillUseCase();

    @Test
    public void adicionaLista(){
        AdicionarSkillUseCase adicionarSkillUseCase = new AdicionarSkillUseCase();
        ParticipantePrograma p = new Mentor();
        skill.AdicionarSkill(p,2);
    }
}

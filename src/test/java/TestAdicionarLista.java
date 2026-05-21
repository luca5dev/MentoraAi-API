import app.model.entity.Mentor;
import app.model.entity.ParticipantePrograma;
import app.service.useCase.AdicionarSkillUseCase;
import org.junit.jupiter.api.Test;

public class TestAdicionarLista {

    @Test
    public void adicionaLista(){
        AdicionarSkillUseCase adicionarSkillUseCase = new AdicionarSkillUseCase();
        ParticipantePrograma p = new Mentor();
        adicionarSkillUseCase.AdicionarSkill(p,2);
    }
}

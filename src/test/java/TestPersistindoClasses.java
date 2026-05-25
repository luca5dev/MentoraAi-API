import app.dao.ParticipanteDAO;
import app.model.entity.Mentor;
import app.model.entity.ParticipantePrograma;
import app.model.enums.NivelSenioridade;
import app.service.useCase.SenioridadeUseCase;
import app.service.useCase.SkillUseCase;
import org.junit.jupiter.api.Test;

public class TestPersistindoClasses {

    @Test
    public void persistirDados(){
        ParticipantePrograma p = new Mentor();
        ParticipanteDAO jpa = new ParticipanteDAO();
        SenioridadeUseCase buscaSenioridadeUseCase = new SenioridadeUseCase();
        SkillUseCase skillUseCase = new SkillUseCase();
        p.setNome("Jorge");
        NivelSenioridade nivel = buscaSenioridadeUseCase.buscaSenioridade(2);
        p.setNivelSenioridade(nivel);
        skillUseCase.AdicionarSkill(p, 2);
        skillUseCase.AdicionarSkill(p, 3);
        skillUseCase.AdicionarSkill(p, 4);

        jpa.persist(p);
    }
}

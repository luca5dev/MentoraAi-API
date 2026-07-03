package app.domain.validator;

import app.domain.model.Mentor;
import app.domain.model.Mentorado;
import app.domain.model.NivelSenioridade;
import app.domain.model.Skill;
import app.domain.model.TrilhaMentoria;
import app.domain.exception.CargaHorariaExcedidaException;
import app.domain.exception.MaximoMentoradosAtingidosException;
import app.domain.exception.NivelDesproporcionalException;
import app.domain.exception.NumeroForaDoIntervaloException;
import app.domain.exception.SkillIncompativelException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidadorTrilhaDomainTest {

    private final ValidadorTrilhaDomain validador = new ValidadorTrilhaDomain();

    @Test
    void deveValidarTudoQuandoTrilhaEhValida() {
        TrilhaMentoria trilha = trilhaValida();

        assertDoesNotThrow(() -> validador.validarTudo(trilha));
    }

    @Test
    void deveLancarQuandoCargaHorariaExcede() {
        TrilhaMentoria trilha = trilhaValida();
        trilha.getMentorados().forEach(m -> m.setHorasDedicadas(15.0));

        assertThrows(CargaHorariaExcedidaException.class, () -> validador.validarTudo(trilha));
    }

    @Test
    void deveLancarQuandoDuracaoInvalida() {
        TrilhaMentoria trilha = trilhaValida();
        trilha.setCicloEmMeses(0);

        assertThrows(NumeroForaDoIntervaloException.class, () -> validador.validarTudo(trilha));
    }

    @Test
    void deveLancarQuandoSenioridadeInvalida() {
        TrilhaMentoria trilha = trilhaValida();
        trilha.getMentor().setNivelSenioridade(NivelSenioridade.PLENO);

        assertThrows(NivelDesproporcionalException.class, () -> validador.validarTudo(trilha));
    }

    @Test
    void deveLancarQuandoSkillIncompativel() {
        TrilhaMentoria trilha = trilhaValida();
        Mentorado mentorado = trilha.getMentorados().get(0);
        mentorado.getSkillsDesejadas().clear();
        mentorado.adicionarSkillDesejada(Skill.AWS);
        mentorado.adicionarSkillDesejada(Skill.ANGULAR);

        assertThrows(SkillIncompativelException.class, () -> validador.validarTudo(trilha));
    }

    @Test
    void deveLancarQuandoMaximoMentoradosExcedido() {
        TrilhaMentoria trilha = trilhaValida();
        trilha.getMentor().setMaximoMentorados(1);

        assertThrows(MaximoMentoradosAtingidosException.class, () -> validador.validarTudo(trilha));
    }

    private TrilhaMentoria trilhaValida() {
        Mentor mentor = new Mentor("Mentor 1", NivelSenioridade.SENIOR,
                new ArrayList<>(List.of(Skill.JAVA, Skill.SPRING, Skill.SQL, Skill.GIT)), 150.0);

        Mentorado mentorado1 = new Mentorado("Mentorado 1", NivelSenioridade.JUNIOR,
                new ArrayList<>(List.of(Skill.JAVA)), 50.0, 5.0,
                new ArrayList<>(List.of(Skill.JAVA, Skill.SPRING, Skill.SQL)));

        Mentorado mentorado2 = new Mentorado("Mentorado 2", NivelSenioridade.PLENO,
                new ArrayList<>(List.of(Skill.SQL)), 70.0, 4.0,
                new ArrayList<>(List.of(Skill.JAVA, Skill.SQL, Skill.GIT)));

        TrilhaMentoria trilha = new TrilhaMentoria();
        trilha.setNomeDaTrilha("Trilha Java");
        trilha.setCicloEmMeses(3);
        trilha.setMentor(mentor);
        trilha.setMentorados(new ArrayList<>(List.of(mentorado1, mentorado2)));
        trilha.setSkillsDaTrilha(new ArrayList<>(List.of(Skill.JAVA, Skill.SPRING, Skill.SQL)));
        return trilha;
    }
}


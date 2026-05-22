package app.model.validator;

import app.model.entity.Mentor;
import app.model.entity.Mentorado;
import app.model.entity.TrilhaMentoria;
import app.model.enums.NivelSenioridade;
import app.model.enums.Skill;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidacaoTrilhaTest {

    @Test
    void deveCriarMentorComDadosValidos() {
        Mentor mentor = new Mentor("João", NivelSenioridade.ESPECIALISTA,
                List.of(Skill.JAVA, Skill.ANGULAR, Skill.AWS, Skill.GIT),
        30.0, 20.0);

        assertEquals("João", mentor.getNome());
        assertEquals(NivelSenioridade.ESPECIALISTA, mentor.getNivelSenioridade());
        assertTrue(mentor.getSkills().contains(Skill.JAVA));
        assertTrue(mentor.getSkills().contains(Skill.ANGULAR));
        assertTrue(mentor.getSkills().contains(Skill.AWS));
        assertTrue(mentor.getSkills().contains(Skill.GIT));
        assertEquals(30.0, mentor.getValorHora(),0.0001);
        assertEquals(20.0, mentor.getHorasDedicadas(),0.0001);
    }

    @Test
    void deveCriarMentoradoComDadosValidos() {
        Mentorado mentorado = new Mentorado("Maria", NivelSenioridade.JUNIOR,
                List.of(Skill.HTML, Skill.CSS, Skill.SQL),
                11.36, 5.0, List.of(Skill.JAVA, Skill.SPRING, Skill.SQL));
        assertEquals("Maria", mentorado.getNome());
        assertEquals(NivelSenioridade.JUNIOR, mentorado.getNivelSenioridade());
        assertTrue(mentorado.getSkills().contains(Skill.HTML));
        assertTrue(mentorado.getSkills().contains(Skill.CSS));
        assertTrue(mentorado.getSkills().contains(Skill.SQL));
        assertEquals(11.36, mentorado.getValorHora(),0.0001);
        assertEquals(5.0, mentorado.getHorasDedicadas(),0.0001);
        assertTrue(mentorado.getSkillsDesejadas().contains(Skill.JAVA));
        assertTrue(mentorado.getSkillsDesejadas().contains(Skill.SPRING));
        assertTrue(mentorado.getSkillsDesejadas().contains(Skill.SQL));
    }

    @Test
    void deveCriarUmaTrilhaComDadosValidos() {
        TrilhaMentoria trilha = new TrilhaMentoria("Trilha Java com Angular", 2,
                new Mentor("Osvaldo", NivelSenioridade.SENIOR, List.of(Skill.JAVA, Skill.ANGULAR), 36.0, 20.0),
                List.of(new Mentorado("Ana", NivelSenioridade.JUNIOR, List.of(Skill.HTML, Skill.JAVA),15.0, 5.0, List.of(Skill.JAVA, Skill.ANGULAR))),
                List.of());
    }

    @Test
    void deveBloquearCargaHorariaExcedida() {

    }
}

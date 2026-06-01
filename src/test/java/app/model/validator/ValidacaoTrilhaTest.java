package app.model.validator;

import app.exception.CargaHorariaExcedidaException;
import app.exception.NivelDesproporcionalException;
import app.exception.SkillIncompativelException;
import app.model.entity.Mentor;
import app.model.entity.Mentorado;
import app.model.entity.TrilhaMentoria;
import app.model.enums.NivelSenioridade;
import app.model.enums.Skill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ValidacaoTrilhaTest {

    //Testes de entidade e criação
    @Test
    void deveCriarMentorComDadosValidos() {
        Mentor mentor = new Mentor("João", NivelSenioridade.ESPECIALISTA,
                List.of(Skill.JAVA, Skill.ANGULAR, Skill.AWS, Skill.GIT),
        30.0);

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
                new Mentor("Osvaldo", NivelSenioridade.SENIOR, List.of(Skill.JAVA, Skill.ANGULAR), 36.0 ),
                List.of(new Mentorado("Davi", NivelSenioridade.JUNIOR, List.of(Skill.HTML, Skill.JAVA),15.0, 5.0, List.of(Skill.JAVA, Skill.ANGULAR)),
                        new Mentorado("Ana", NivelSenioridade.PLENO, List.of(Skill.JAVA, Skill.ANGULAR, Skill.GIT, Skill.AWS), 10.0, 6.0,List.of(Skill.JAVA, Skill.ANGULAR))),
                List.of(Skill.JAVA, Skill.ANGULAR));

        assertEquals("Trilha Java com Angular", trilha.getNomeDaTrilha());
        assertEquals(2, trilha.getDuracaoMeses(), 0.0001);

        assertEquals("Osvaldo", trilha.getMentor().getNome());
        assertEquals(NivelSenioridade.SENIOR, trilha.getMentor().getNivelSenioridade());
        assertTrue(trilha.getMentor().getSkills().contains(Skill.JAVA));
        assertTrue(trilha.getMentor().getSkills().contains(Skill.ANGULAR));
        assertEquals(36.0, trilha.getMentor().getValorHora(),0.0001);
        assertEquals(20.0, trilha.getMentor().getHorasDedicadas(), 0.0001);

        assertEquals("Davi", trilha.getMentorados().getFirst().getNome());
        assertEquals(NivelSenioridade.JUNIOR, trilha.getMentorados().getFirst().getNivelSenioridade());
        assertTrue(trilha.getMentorados().getFirst().getSkills().contains(Skill.HTML));
        assertTrue(trilha.getMentorados().getFirst().getSkills().contains(Skill.JAVA));
        assertEquals(15.0, trilha.getMentorados().getFirst().getValorHora(),0.0001);
        assertEquals(5.0, trilha.getMentorados().getFirst().getHorasDedicadas(),0.0001);
        assertTrue(trilha.getMentorados().getFirst().getSkillsDesejadas().contains(Skill.JAVA));
        assertTrue(trilha.getMentorados().getFirst().getSkillsDesejadas().contains(Skill.ANGULAR));
        assertTrue(trilha.getSkillsDaTrilha().contains(Skill.JAVA));
        assertTrue(trilha.getSkillsDaTrilha().contains(Skill.ANGULAR));

        assertEquals("Ana", trilha.getMentorados().get(1).getNome());
        assertEquals(NivelSenioridade.PLENO, trilha.getMentorados().get(1).getNivelSenioridade());
        assertTrue(trilha.getMentorados().get(1).getSkills().contains(Skill.JAVA));
        assertTrue(trilha.getMentorados().get(1).getSkills().contains(Skill.ANGULAR));
        assertTrue(trilha.getMentorados().get(1).getSkills().contains(Skill.GIT));
        assertTrue(trilha.getMentorados().get(1).getSkills().contains(Skill.AWS));
        assertEquals(10.0, trilha.getMentorados().get(1).getValorHora(),0.0001);
        assertEquals(6.0, trilha.getMentorados().get(1).getHorasDedicadas(),0.0001);
        assertTrue(trilha.getMentorados().get(1).getSkillsDesejadas().contains(Skill.JAVA));
        assertTrue(trilha.getMentorados().get(1).getSkillsDesejadas().contains(Skill.ANGULAR));

        assertTrue(trilha.getSkillsDaTrilha().contains(Skill.JAVA));
        assertTrue(trilha.getSkillsDaTrilha().contains(Skill.ANGULAR));
    }

    //Testes de fluxo
    @Test
    void deveCriarTrilhaCompletaComSucessoPassandoEmTodasValidacoes() {
        ValidacaoTrilha validacaoTrilha = new ValidacaoTrilha();
        TrilhaMentoria trilha = new TrilhaMentoria("Trilha Java com Angular", 2,
                new Mentor("Osvaldo", NivelSenioridade.SENIOR, List.of(Skill.JAVA, Skill.ANGULAR), 36.0),
                List.of(new Mentorado("Davi", NivelSenioridade.JUNIOR, List.of(Skill.HTML, Skill.JAVA),15.0, 5.0, List.of(Skill.JAVA, Skill.ANGULAR)),
                        new Mentorado("Ana", NivelSenioridade.PLENO, List.of(Skill.JAVA, Skill.ANGULAR, Skill.GIT, Skill.AWS), 10.0, 6.0,List.of(Skill.JAVA, Skill.ANGULAR)),
                        new Mentorado("Carla", NivelSenioridade.JUNIOR, List.of(Skill.HTML, Skill.CSS), 15.0, 5.0, List.of(Skill.JAVA, Skill.ANGULAR))),
                List.of(Skill.JAVA, Skill.ANGULAR));

        assertDoesNotThrow(() -> {
            validacaoTrilha.validarCargaHoraria(trilha);
            validacaoTrilha.validarSenioridade(trilha);
            validacaoTrilha.validarSkills(trilha);
        });
    }

    @Test
    void deveLancarCargaHorariaExcedidaException() {
        ValidacaoTrilha validacaoTrilha = new ValidacaoTrilha();
        TrilhaMentoria trilha = new TrilhaMentoria("Trilha Java com Angular", 2,
                new Mentor("Osvaldo", NivelSenioridade.SENIOR, List.of(Skill.JAVA, Skill.ANGULAR), 36.0),
                List.of(new Mentorado("Davi", NivelSenioridade.JUNIOR, List.of(Skill.HTML, Skill.JAVA),15.0, 10.0, List.of(Skill.JAVA, Skill.ANGULAR)),
                        new Mentorado("Ana", NivelSenioridade.PLENO, List.of(Skill.JAVA, Skill.ANGULAR, Skill.GIT, Skill.AWS), 10.0, 6.0,List.of(Skill.JAVA, Skill.ANGULAR)),
                        new Mentorado("Carla", NivelSenioridade.JUNIOR, List.of(Skill.HTML, Skill.CSS), 15.0, 5.0, List.of(Skill.JAVA, Skill.ANGULAR))),
                List.of(Skill.JAVA, Skill.ANGULAR));

        CargaHorariaExcedidaException erroCapturado = assertThrows(CargaHorariaExcedidaException.class, () -> validacaoTrilha.validarCargaHoraria(trilha));
        assertEquals("Carga horária do mentor excedida!", erroCapturado.getMessage());
    }

    @Test
    void deveLancarSkillIncompativelException() {
        ValidacaoTrilha validacaoTrilha = new ValidacaoTrilha();
        TrilhaMentoria trilha = new TrilhaMentoria("Trilha Java com Angular", 2,
                new Mentor("Osvaldo", NivelSenioridade.SENIOR, List.of(Skill.ANGULAR), 36.0),
                List.of(new Mentorado("Davi", NivelSenioridade.JUNIOR, List.of(Skill.HTML, Skill.JAVA),15.0, 5.0, List.of(Skill.JAVA, Skill.ANGULAR)),
                        new Mentorado("Ana", NivelSenioridade.PLENO, List.of(Skill.JAVA, Skill.ANGULAR, Skill.GIT, Skill.AWS), 10.0, 6.0,List.of(Skill.JAVA, Skill.ANGULAR)),
                        new Mentorado("Carla", NivelSenioridade.JUNIOR, List.of(Skill.HTML, Skill.CSS), 15.0, 5.0, List.of(Skill.JAVA, Skill.ANGULAR))),
                List.of(Skill.JAVA, Skill.ANGULAR));
        SkillIncompativelException erroCapturado = assertThrows(SkillIncompativelException.class, () -> validacaoTrilha.validarSkills(trilha));
        assertEquals("Compatibilidade de skills menor que 70%", erroCapturado.getMessage());
    }

    @Test
    void deveLancarNivelDesproporcionalException() { //Quando o nível do mentor for igual ou menor que do mentorado
        ValidacaoTrilha validacaoTrilha = new ValidacaoTrilha();
        TrilhaMentoria trilha = new TrilhaMentoria("Trilha Java com Angular", 2,
                new Mentor("Osvaldo", NivelSenioridade.JUNIOR, List.of(Skill.JAVA, Skill.ANGULAR), 36.0),
                List.of(new Mentorado("Davi", NivelSenioridade.JUNIOR, List.of(Skill.HTML, Skill.JAVA),15.0, 5.0, List.of(Skill.JAVA, Skill.ANGULAR)),
                        new Mentorado("Ana", NivelSenioridade.PLENO, List.of(Skill.JAVA, Skill.ANGULAR, Skill.GIT, Skill.AWS), 10.0, 6.0,List.of(Skill.JAVA, Skill.ANGULAR)),
                        new Mentorado("Carla", NivelSenioridade.JUNIOR, List.of(Skill.HTML, Skill.CSS), 15.0, 5.0, List.of(Skill.JAVA, Skill.ANGULAR))),
                List.of(Skill.JAVA, Skill.ANGULAR));
        NivelDesproporcionalException erroCapturado = assertThrows(NivelDesproporcionalException.class, () -> validacaoTrilha.validarSenioridade(trilha));
        assertEquals("O mentor deve ter senioridade superior a todos os mentorados.", erroCapturado.getMessage());
    }

    //Testes de polimorfismo
    @Test
    void deveCalcularCustoOportunidadeMensalDoMentor() {}

    @Test
    void deveCalcularCustoOportunidadeMensalDoMentorado() {}

    //Testes de carga horária
    @Test
    void devePermitirCargaHorariaDentroDoLimite() {}

    //Testes de quantidade de mentorados
    @Test
    void devePermitirQuantidadeValidaDeMentorados() {}

    @Test
    void deveBloquearQuandoUltrapassarLimiteDeMentorados() {} //Valida a quantidade e não horas - Criar uma exception customizada pra isso

    //Testes de skills e match de 70%
    @Test
    void devePermitirPareamentoComSkillsCompativeis() {}

    //Testes de senioridade
    @Test
    void devePermitirMentorComSenioridadeSuperiorATodosMentorados() {}

    //Testes de Stream API
    @Test
    void deveSomarHorasDosMentoradosCorretamente() {}

    @Test
    void deveContarSkillsCompativeisCorretamente() {} //?? ver se tiro esse teste

    @Test
    void deveValidarSenioridadeUsandoStream() {} // da pra usar esse de modelo pras outras validações que tbm usam stream

    //Testes de regras de negócio gerais
    @Test
    void deveImpedirCriacaoDeTrilhaSemMentor() {} //Não aceitar Null

    @Test
    void deveImpedirCriacaoDeTrilhaSemMentorado() {} //Não aceitar Null - VER SE COLOQUEI ESSA VALIDAÇÃO NO MODEL!!

    @Test
    void deveImpedirMentorSemSkills() {} //Não aceitar Null ou lista vazia - VER SE COLOQUEI ESSA VALIDAÇÃO NO MODEL!!

    @Test
    void deveImpedirMentodadoSemSkillsDesejadas() {} //Não aceitar Null ou lista vazia - VER SE COLOQUEI ESSA VALIDAÇÃO NO MODEL!!
}

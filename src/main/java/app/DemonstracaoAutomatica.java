package app;

import app.exception.CargaHorariaExcedidaException;
import app.exception.MaximoMentoradosAtingidosException;
import app.exception.NivelDesproporcionalException;
import app.exception.SkillIncompativelException;
import app.model.entity.Mentor;
import app.model.entity.Mentorado;
import app.model.entity.TrilhaMentoria;
import app.model.enums.NivelSenioridade;
import app.model.enums.Skill;
import app.model.validator.ValidacaoTrilha;
import app.service.interfaces.TrilhaService;

import java.util.ArrayList;
import java.util.List;

/*
 * AC6: roteiro de apresentação. Demonstra automaticamente:
 * 1) Tentativa de criar uma trilha INVÁLIDA (cada um dos 3 motores quebrando)
 * 2) Ajuste dos dados
 * 3) Commit bem-sucedido via JPA
 * Não usa o fluxo do menu — monta os objetos diretamente para a Demo.
 */
public class DemonstracaoAutomatica {

    private final TrilhaService trilhaService;
    private final ValidacaoTrilha validador = new ValidacaoTrilha();

    public DemonstracaoAutomatica(TrilhaService trilhaService) {
        this.trilhaService = trilhaService;
    }

    public void executar() {
        System.out.println("========== AC6: DEMONSTRAÇÃO AUTOMÁTICA ==========\n");

        cenario1_cargaHorariaExcedida();
        cenario2_skillsIncompativeis();
        cenario3_nivelDesproporcional();
        cenario4_trilhaValidaPersistida();

        System.out.println("\n========== FIM DA DEMONSTRAÇÃO AUTOMÁTICA ==========");
    }

    //Cenário 1: AC2 - Trava de Carga Horária
    private void cenario1_cargaHorariaExcedida() {
        System.out.println(">> Cenário 1: Carga horária excedida (limite 20h/mês)");
        Mentor mentor = novoMentor("Ana", NivelSenioridade.SENIOR, List.of(Skill.JAVA, Skill.SPRING));
        Mentorado mentorado1 = novoMentorado("Bruno", NivelSenioridade.JUNIOR, 5.0, List.of(Skill.JAVA));
        Mentorado mentorado2 = novoMentorado("Carla", NivelSenioridade.JUNIOR, 5.0, List.of(Skill.JAVA));
        Mentorado mentorado3 = novoMentorado("Daniel", NivelSenioridade.JUNIOR, 5.0, List.of(Skill.JAVA));
        Mentorado mentorado4 = novoMentorado("Erica", NivelSenioridade.JUNIOR, 6.0, List.of(Skill.JAVA));

        TrilhaMentoria trilha = new TrilhaMentoria("Trilha Sobrecarregada",
                3, mentor,
                new ArrayList<>(List.of(mentorado1, mentorado2, mentorado3, mentorado4)),
                new ArrayList<>(List.of(Skill.JAVA)));
        tentarValidar(trilha);
    }

    //Cenário 2: AC3 - Auditoria de Pareamento Técnico
    private void cenario2_skillsIncompativeis() {
        System.out.println(">> Cenário 2: Skills incompatíveis (mentor não tem 70% das skills desejadas)");
        Mentor mentor = novoMentor("Ana", NivelSenioridade.SENIOR, List.of(Skill.JAVA));
        Mentorado mentorado1 = novoMentorado("Bruno", NivelSenioridade.JUNIOR, 5.0,
                List.of(Skill.JAVA, Skill.ANGULAR, Skill.REACT, Skill.DOCKER));

        TrilhaMentoria trilha = new TrilhaMentoria(
                "Trilha Skills", 3, mentor,
                new ArrayList<>(List.of(mentorado1)),
                new ArrayList<>(List.of(Skill.JAVA, Skill.ANGULAR)));
        tentarValidar(trilha);
    }

    //Cenário 3: AC4 - Nivelamento de Senioridade
    private void cenario3_nivelDesproporcional() {
        System.out.println(">> Cenário 3: Nível desproporcional (PLENO, mentorando PLENO)");
        Mentor mentor = novoMentor("Diego", NivelSenioridade.PLENO, List.of(Skill.JAVA, Skill.SPRING));
        Mentorado mentorado1 = novoMentorado("Eva", NivelSenioridade.PLENO, 5.0, List.of(Skill.JAVA, Skill.SPRING));

        TrilhaMentoria trilha = new TrilhaMentoria(
                "Trilha Nível", 3, mentor,
                new ArrayList<>(List.of(mentorado1)),
                new ArrayList<>(List.of(Skill.JAVA)));
        tentarValidar(trilha);
    }

    //Cenário 4: Trilha válida + persistência no banco
    private void cenario4_trilhaValidaPersistida() {
        System.out.println(">> Cenário 4: Trilha válida e persistida no banco");
        Mentor mentor = novoMentor("Fabio", NivelSenioridade.SENIOR,
                List.of(Skill.JAVA, Skill.SPRING, Skill.SQL));

        Mentorado mentorado1 = novoMentorado("Gabi", NivelSenioridade.JUNIOR, 5.0,
                List.of(Skill.JAVA, Skill.SPRING));

        Mentorado mentorado2 = novoMentorado("Helena", NivelSenioridade.JUNIOR, 5.0,
                List.of(Skill.JAVA, Skill.SQL));

        TrilhaMentoria trilha = new TrilhaMentoria(
                "Trilha Java Backend", 3, mentor,
                new ArrayList<>(List.of(mentorado1, mentorado2)),
                new ArrayList<>(List.of(Skill.JAVA, Skill.SPRING, Skill.SQL)));

        try {
            validador.validarCargaHoraria(trilha);
            validador.validarQuantidadeMentorados(trilha);
            validador.validarSenioridade(trilha);
            validador.validarSkills(trilha);

            System.out.println("Validações passaram. Acionando JPA...");
            System.out.println("Ciclo da trilha: " + trilha.getCicloEmMeses() + " meses");
            System.out.println("Carga mensal total prevista: "
            + trilha.getMentorados().stream().mapToDouble(Mentorado::getHorasDedicadas).sum() + "h");
            System.out.println("Custo mensal total da trilha: R$" + trilha.calcularCustoMensalTotal());
            System.out.println("Custo total do ciclo: R$" + trilha.calcularCustoTotalDoCiclo());
            trilhaService.persistirJaValidada(trilha);
        } catch (RuntimeException e) {
            System.out.println("Falha inesperada: " +  e.getMessage() + "\n");
        }
    }

    private void tentarValidar(TrilhaMentoria trilha) {
        try {
            validador.validarCargaHoraria(trilha);
            validador.validarQuantidadeMentorados(trilha);
            validador.validarSenioridade(trilha);
            validador.validarSkills(trilha);
            System.out.println("    (Inesperado) Trilha passou nas validações.\n");
        } catch (CargaHorariaExcedidaException | SkillIncompativelException | NivelDesproporcionalException |
                 MaximoMentoradosAtingidosException e) {
            System.out.println(" Exceção tratada: "
            + e.getClass().getSimpleName() + " -> " + e.getMessage() + "\n");
        }
    }

    private Mentor novoMentor(String nome, NivelSenioridade nivelSenioridade, List<Skill> skills) {
        return new Mentor(nome, nivelSenioridade, new ArrayList<>(skills), 150.0);
    }

    private Mentorado novoMentorado(String nome, NivelSenioridade nivelSenioridade, double horasDedicadas,
                                    List<Skill> skillsDesejadas) {

        return new Mentorado(nome, nivelSenioridade,new ArrayList<>(),80.0, horasDedicadas, new ArrayList<>(skillsDesejadas));
    }
}

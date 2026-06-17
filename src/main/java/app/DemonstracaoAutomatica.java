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
import app.util.ConsoleUI;

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

        TrilhaMentoria trilhaInvalida = cenario1_cargaHorariaExcedida();
        cenario2_skillsIncompativeis();
        cenario3_nivelDesproporcional();
        cenario4_trilhaValidaPersistida(trilhaInvalida);

        System.out.println("\n========== FIM DA DEMONSTRAÇÃO AUTOMÁTICA ==========");
    }

    //Cenário 1: AC2 - Trava de Carga Horária
    private TrilhaMentoria cenario1_cargaHorariaExcedida() {
        System.out.println(">> Cenário 1: Carga horária excedida (limite 20h/mês)");
        Mentor mentor = novoMentor("Ana", NivelSenioridade.SENIOR, List.of(Skill.JAVA, Skill.SPRING, Skill.SQL));
        Mentorado mentorado1 = novoMentorado("Bruno", NivelSenioridade.JUNIOR, 5.0, List.of(Skill.JAVA));
        Mentorado mentorado2 = novoMentorado("Carla", NivelSenioridade.JUNIOR, 5.0, List.of(Skill.JAVA));
        Mentorado mentorado3 = novoMentorado("Daniel", NivelSenioridade.JUNIOR, 5.0, List.of(Skill.JAVA));
        Mentorado mentorado4 = novoMentorado("Erica", NivelSenioridade.JUNIOR, 6.0, List.of(Skill.JAVA));

        TrilhaMentoria trilha = new TrilhaMentoria("Trilha Sobrecarregada",
                3, mentor,
                new ArrayList<>(List.of(mentorado1, mentorado2, mentorado3, mentorado4)),
                new ArrayList<>(List.of(Skill.JAVA)));
        tentarValidar(trilha);
        return trilha;
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

    //Cenário 4: Trilha válida. Pega a trilha que falhou no cenário 1, ajusta e persiste via JPA
    private void cenario4_trilhaValidaPersistida(TrilhaMentoria trilhaInvalida) {
        System.out.println(">> Cenário 4: Ajuste dos dados da trilha inválida e persistência");

        double cargaHorariaAntes = trilhaInvalida.getMentorados().stream()
                .mapToDouble(Mentorado::getHorasDedicadas).sum();
        System.out.println("Carga mensal ANTES do ajuste: " + ConsoleUI.horas(cargaHorariaAntes) + " (acima do limite de 20h)");

        List<Mentorado> mentorados = trilhaInvalida.getMentorados();
        while (mentorados.stream().mapToDouble(Mentorado::getHorasDedicadas).sum() > 20.0
            && !mentorados.isEmpty()) {
    Mentorado removido = mentorados.remove(mentorados.size() -1);
            System.out.println("Ajustando: removendo mentorado \"" + removido.getNome()
            + "\"(" + ConsoleUI.horas(removido.getHorasDedicadas()) +")");
            }
        double cargaHorariaDepois = mentorados.stream()
                .mapToDouble(Mentorado::getHorasDedicadas).sum();
        System.out.println("Carga mensal DEPOIS do ajuste: " + ConsoleUI.horas(cargaHorariaDepois) + " (dentro do limite de 20h)");

        try {
            validador.validarCargaHoraria(trilhaInvalida);
            validador.validarQuantidadeMentorados(trilhaInvalida);
            validador.validarSenioridade(trilhaInvalida);
            validador.validarSkills(trilhaInvalida);

            System.out.println("Validações passaram após o ajuste. Acionando JPA...");
            ConsoleUI.cabecalho("Dados da trilha:");
            System.out.println("Nome: " + trilhaInvalida.getNomeDaTrilha());
            System.out.println("Duração: " + trilhaInvalida.getCicloEmMeses() + " meses.");
            System.out.println("Skills da ensinadas: " + trilhaInvalida.getSkillsDaTrilha());
            System.out.println("Mentor: " + trilhaInvalida.getMentor().getNome() + "-> " + trilhaInvalida.getMentor().getNivelSenioridade());
            System.out.println("Carga horária mensal prevista: " + ConsoleUI.horas(cargaHorariaDepois));
            System.out.println("Custo mensal previsto: " + ConsoleUI.moeda(trilhaInvalida.calcularCustoMensalTotal()));
            System.out.println("Custo total do ciclo completo: " + ConsoleUI.moeda(trilhaInvalida.calcularCustoTotalDoCiclo()));
            ConsoleUI.separador();

            trilhaService.persistirJaValidada(trilhaInvalida);
        } catch (RuntimeException e) {
            System.out.println("Falha inesperada após ajuste: " + e.getMessage() + "\n");
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

package app.domain.usecase;

import app.config.MensagensLogger;
import app.domain.model.DemonstracaoResultado;
import app.domain.model.Mentor;
import app.domain.model.Mentorado;
import app.domain.model.NivelSenioridade;
import app.domain.model.Skill;
import app.domain.model.TrilhaMentoria;
import app.domain.port.in.ExecutarDemonstracaoPort;
import app.domain.port.out.TrilhaRepositoryPort;
import app.domain.validator.ValidadorTrilhaDomain;
import app.domain.exception.CargaHorariaExcedidaException;
import app.domain.exception.MaximoMentoradosAtingidosException;
import app.domain.exception.NivelDesproporcionalException;
import app.domain.exception.SkillIncompativelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ExecutarDemonstracaoAutomaticaUseCase implements ExecutarDemonstracaoPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutarDemonstracaoAutomaticaUseCase.class);

    private final TrilhaRepositoryPort trilhaRepositoryPort;
    private final ValidadorTrilhaDomain validador;
    private final List<String> logs = new ArrayList<>();
    private final List<DemonstracaoResultado.CenarioResultado> cenarios = new ArrayList<>();

    private static final List<String> NOMES_TRILHAS_DEMONSTRACAO = List.of(
            MensagensLogger.NOME_TRILHA_SOBRECARREGADA,
            MensagensLogger.NOME_TRILHA_SKILLS,
            MensagensLogger.NOME_TRILHA_NIVEL
    );

    public ExecutarDemonstracaoAutomaticaUseCase(
            TrilhaRepositoryPort trilhaRepositoryPort,
            ValidadorTrilhaDomain validador) {
        this.trilhaRepositoryPort = trilhaRepositoryPort;
        this.validador = validador;
    }

    @Override
    public DemonstracaoResultado executar() {
        logs.clear();
        cenarios.clear();

        limparDadosAnteriorDaDemonstracao();

        adicionarLog(MensagensLogger.INICIO_DEMONSTRACAO);
        adicionarLog("");

        TrilhaMentoria trilhaInvalida = cenario1_cargaHorariaExcedida();
        cenario2_skillsIncompativeis();
        cenario3_nivelDesproporcional();
        cenario4_trilhaValidaPersistida(trilhaInvalida);

        adicionarLog("");
        adicionarLog(MensagensLogger.FIM_DEMONSTRACAO);

        return new DemonstracaoResultado(
                MensagensLogger.TITULO_DEMONSTRACAO,
                MensagensLogger.MENSAGEM_SUCESSO,
                cenarios,
                logs
        );
    }

    private TrilhaMentoria cenario1_cargaHorariaExcedida() {
        adicionarLog(MensagensLogger.CENARIO_1_CARGA_HORARIA);

        Mentor mentor = novoMentor("Ana", NivelSenioridade.SENIOR, List.of(Skill.JAVA, Skill.SPRING, Skill.SQL));
        Mentorado mentorado1 = novoMentorado("Bruno", NivelSenioridade.JUNIOR, 5.0, List.of(Skill.JAVA));
        Mentorado mentorado2 = novoMentorado("Carla", NivelSenioridade.JUNIOR, 5.0, List.of(Skill.JAVA));
        Mentorado mentorado3 = novoMentorado("Daniel", NivelSenioridade.JUNIOR, 5.0, List.of(Skill.JAVA));
        Mentorado mentorado4 = novoMentorado("Erica", NivelSenioridade.JUNIOR, 6.0, List.of(Skill.JAVA));

        TrilhaMentoria trilha = new TrilhaMentoria(
                MensagensLogger.NOME_TRILHA_SOBRECARREGADA,
                3,
                mentor,
                new ArrayList<>(List.of(mentorado1, mentorado2, mentorado3, mentorado4)),
                new ArrayList<>(List.of(Skill.JAVA))
        );

        tentarValidar(trilha, 1);
        return trilha;
    }

    private void cenario2_skillsIncompativeis() {
        adicionarLog(MensagensLogger.CENARIO_2_SKILLS);

        Mentor mentor = novoMentor("Ana", NivelSenioridade.SENIOR, List.of(Skill.JAVA));
        Mentorado mentorado1 = novoMentorado("Bruno", NivelSenioridade.JUNIOR, 5.0,
                List.of(Skill.JAVA, Skill.ANGULAR, Skill.REACT, Skill.DOCKER));

        TrilhaMentoria trilha = new TrilhaMentoria(
                MensagensLogger.NOME_TRILHA_SKILLS,
                3,
                mentor,
                new ArrayList<>(List.of(mentorado1)),
                new ArrayList<>(List.of(Skill.JAVA, Skill.ANGULAR))
        );

        tentarValidar(trilha, 2);
    }

    private void cenario3_nivelDesproporcional() {
        adicionarLog(MensagensLogger.CENARIO_3_NIVEL);

        Mentor mentor = novoMentor("Diego", NivelSenioridade.PLENO, List.of(Skill.JAVA, Skill.SPRING));
        Mentorado mentorado1 = novoMentorado("Eva", NivelSenioridade.PLENO, 5.0, List.of(Skill.JAVA, Skill.SPRING));

        TrilhaMentoria trilha = new TrilhaMentoria(
                MensagensLogger.NOME_TRILHA_NIVEL,
                3,
                mentor,
                new ArrayList<>(List.of(mentorado1)),
                new ArrayList<>(List.of(Skill.JAVA))
        );

        tentarValidar(trilha, 3);
    }

     private void cenario4_trilhaValidaPersistida(TrilhaMentoria trilhaInvalida) {
          adicionarLog(MensagensLogger.CENARIO_4_AJUSTE);

          double cargaHorariaAntes = trilhaInvalida.getMentorados().stream()
                  .mapToDouble(Mentorado::getHorasDedicadas).sum();
          adicionarLog("Carga mensal ANTES do ajuste: " + formatarHoras(cargaHorariaAntes) + " (acima do limite de 20h)");

          List<Mentorado> mentorados = new ArrayList<>(trilhaInvalida.getMentorados());
          while (mentorados.stream().mapToDouble(Mentorado::getHorasDedicadas).sum() > 20.0 && !mentorados.isEmpty()) {
              Mentorado removido = mentorados.remove(mentorados.size() - 1);
              adicionarLog("Ajustando: removendo mentorado " + removido.getNome() + " - " + formatarHoras(removido.getHorasDedicadas()));
          }

          double cargaHorariaDepois = mentorados.stream()
                  .mapToDouble(Mentorado::getHorasDedicadas).sum();
          adicionarLog("Carga mensal DEPOIS do ajuste: " + formatarHoras(cargaHorariaDepois) + " (dentro do limite de 20h)");

          try {
              Mentor novoMentor = novoMentor(
                      trilhaInvalida.getMentor().getNome(),
                      trilhaInvalida.getMentor().getNivelSenioridade(),
                      new ArrayList<>(trilhaInvalida.getMentor().getSkills())
              );

              List<Mentorado> novosMentorados = new ArrayList<>();
              for (Mentorado mentorado : mentorados) {
                  Mentorado novoMentorado = new Mentorado(
                          mentorado.getNome(),
                          mentorado.getNivelSenioridade(),
                          new ArrayList<>(mentorado.getSkills()),
                          mentorado.getValorHora(),
                          mentorado.getHorasDedicadas(),
                          new ArrayList<>(mentorado.getSkillsDesejadas())
                  );
                  novosMentorados.add(novoMentorado);
              }

              TrilhaMentoria novaTrilha = new TrilhaMentoria(
                      trilhaInvalida.getNomeDaTrilha(),
                      trilhaInvalida.getCicloEmMeses(),
                      novoMentor,
                      novosMentorados,
                      new ArrayList<>(trilhaInvalida.getSkillsDaTrilha())
              );

              validador.validarTudo(novaTrilha);

              adicionarLog("Validações passaram após o ajuste. Acionando JPA...");
              adicionarLog("--- Dados da trilha: ---");
              adicionarLog("Nome: " + novaTrilha.getNomeDaTrilha());
              adicionarLog("Duração: " + novaTrilha.getCicloEmMeses() + " meses.");
              adicionarLog("Skills da ensinadas: " + formatarSkills(novaTrilha.getSkillsDaTrilha()));
              adicionarLog("Mentor: " + novaTrilha.getMentor().getNome() + " - " + novaTrilha.getMentor().getNivelSenioridade());
              adicionarLog("Carga horária mensal prevista: " + formatarHoras(cargaHorariaDepois));
              adicionarLog("Custo mensal previsto: " + formatarMoeda(novaTrilha.calcularCustoMensalTotal()));
              adicionarLog("Custo total do ciclo completo: " + formatarMoeda(novaTrilha.calcularCustoTotalDoCiclo()));
              adicionarLog("");

              trilhaRepositoryPort.persist(novaTrilha);
              adicionarLog(MensagensLogger.TRILHA_PERSISTIDA);

             cenarios.add(new DemonstracaoResultado.CenarioResultado(
                     4,
                     MensagensLogger.TITULO_TRILHA_VALIDA,
                     MensagensLogger.RESULTADO_TRILHA_PERSISTIDA,
                     null
             ));
         } catch (RuntimeException e) {
             String mensagemErro = "Falha inesperada após ajuste: " + e.getMessage();
             MensagensLogger.warn(LOGGER,
                     MensagensLogger.FALHA_TRILHA_AJUSTADA,
                     trilhaInvalida.getNomeDaTrilha(), e);
             adicionarLog(mensagemErro);

             cenarios.add(new DemonstracaoResultado.CenarioResultado(
                     4,
                     MensagensLogger.TITULO_TRILHA_VALIDA,
                     MensagensLogger.RESULTADO_ERRO,
                     e.getMessage()
             ));
         }
     }

    private void tentarValidar(TrilhaMentoria trilha, int numeroCenario) {
        try {
            validador.validarTudo(trilha);
            adicionarLog("    (Inesperado) Trilha passou nas validações.");
            adicionarLog("");

            cenarios.add(new DemonstracaoResultado.CenarioResultado(
                    numeroCenario,
                    "Cenário " + numeroCenario,
                    MensagensLogger.RESULTADO_INESPERADO,
                    null
            ));

        } catch (CargaHorariaExcedidaException | SkillIncompativelException |
                 NivelDesproporcionalException | MaximoMentoradosAtingidosException e) {

            String mensagem = "Exceção tratada: " + e.getClass().getSimpleName() + " -> " + e.getMessage();
            adicionarLog(mensagem);
            adicionarLog("");

            cenarios.add(new DemonstracaoResultado.CenarioResultado(
                    numeroCenario,
                    "Cenário " + numeroCenario,
                    MensagensLogger.RESULTADO_EXCECAO_CAPTURADA,
                    e.getClass().getSimpleName() + ": " + e.getMessage()
            ));
        }
    }

    private Mentor novoMentor(String nome, NivelSenioridade nivelSenioridade, List<Skill> skills) {
        return new Mentor(nome, nivelSenioridade, new ArrayList<>(skills), 150.0);
    }

    private Mentorado novoMentorado(String nome, NivelSenioridade nivelSenioridade, double horasDedicadas,
                                    List<Skill> skillsDesejadas) {
        return new Mentorado(nome, nivelSenioridade, new ArrayList<>(), 80.0, horasDedicadas, new ArrayList<>(skillsDesejadas));
    }

    private void adicionarLog(String mensagem) {
        logs.add(mensagem);
        MensagensLogger.info(LOGGER, mensagem);
    }

    private String formatarHoras(double horas) {
        return String.format("%.1fh", horas);
    }

    private String formatarMoeda(double valor) {
        return String.format("R$ %.2f", valor);
    }

    private String formatarSkills(List<Skill> skills) {
        return skills.isEmpty() ? "Nenhuma" : String.join(", ", skills.stream().map(Skill::name).toList());
    }

      private void limparDadosAnteriorDaDemonstracao() {
          try {
              List<TrilhaMentoria> todasTrilhas = trilhaRepositoryPort.listarTodasTrilhas();

              List<TrilhaMentoria> trilhasParaLimpar = todasTrilhas.stream()
                      .filter(trilha -> NOMES_TRILHAS_DEMONSTRACAO.contains(trilha.getNomeDaTrilha()))
                      .toList();

              for (TrilhaMentoria trilha : trilhasParaLimpar) {
                  try {
                      trilhaRepositoryPort.delete(trilha.getId());
                  } catch (Exception e) {
                      MensagensLogger.warn(LOGGER,
                              MensagensLogger.FALHA_LIMPEZA_TRILHA_RETRY,
                              trilha.getId(), trilha.getNomeDaTrilha(), e);

                      try {
                          Thread.sleep(100);
                          trilhaRepositoryPort.delete(trilha.getId());
                      } catch (InterruptedException retry) {
                          Thread.currentThread().interrupt();
                          MensagensLogger.warn(LOGGER,
                                  MensagensLogger.INTERRUPCAO_LIMPEZA_TRILHA,
                                  trilha.getId(), trilha.getNomeDaTrilha(), retry);
                      } catch (Exception retry) {
                          MensagensLogger.warn(LOGGER,
                                  MensagensLogger.FALHA_SEGUNDA_TENTATIVA_LIMPEZA,
                                  trilha.getId(), trilha.getNomeDaTrilha(), retry);
                      }
                  }
              }
          } catch (Exception e) {
              MensagensLogger.warn(LOGGER, MensagensLogger.ERRO_LIMPEZA_DEMONSTRACAO, e);
          }
      }

}

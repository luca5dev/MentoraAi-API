package app.domain.usecase;

import app.adapters.in.web.dto.DemonstracaoResponse;
import app.domain.model.Mentor;
import app.domain.model.Mentorado;
import app.domain.model.NivelSenioridade;
import app.domain.model.Skill;
import app.domain.model.TrilhaMentoria;
import app.domain.port.in.ExecutarDemonstracaoPort;
import app.domain.port.out.TrilhaRepositoryPort;
import app.domain.validator.ValidadorTrilhaDomain;
import app.exception.CargaHorariaExcedidaException;
import app.exception.MaximoMentoradosAtingidosException;
import app.exception.NivelDesproporcionalException;
import app.exception.SkillIncompativelException;

import java.util.ArrayList;
import java.util.List;

public class ExecutarDemonstracaoAutomaticaUseCase implements ExecutarDemonstracaoPort {

    private final TrilhaRepositoryPort trilhaRepositoryPort;
    private final ValidadorTrilhaDomain validador;
    private final List<String> logs = new ArrayList<>();
    private final List<DemonstracaoResponse.CenarioResponse> cenarios = new ArrayList<>();

    private static final List<String> NOMES_TRILHAS_DEMONSTRACAO = List.of(
            "Trilha Sobrecarregada e Editada",
            "Trilha Skills",
            "Trilha Nível"
    );

    public ExecutarDemonstracaoAutomaticaUseCase(
            TrilhaRepositoryPort trilhaRepositoryPort,
            ValidadorTrilhaDomain validador) {
        this.trilhaRepositoryPort = trilhaRepositoryPort;
        this.validador = validador;
    }

    @Override
    public DemonstracaoResponse executar() {
        logs.clear();
        cenarios.clear();

        try {
            limparDadosAnteriorDaDemonstracao();
        } catch (Exception e) {
            // ignora se falhar (dados podem não existir)
        }

        adicionarLog("========== AC6: DEMONSTRAÇÃO AUTOMÁTICA ==========");
        adicionarLog("");

        TrilhaMentoria trilhaInvalida = cenario1_cargaHorariaExcedida();
        cenario2_skillsIncompativeis();
        cenario3_nivelDesproporcional();
        cenario4_trilhaValidaPersistida(trilhaInvalida);

        adicionarLog("");
        adicionarLog("========== FIM DA DEMONSTRAÇÃO AUTOMÁTICA ==========");

        return new DemonstracaoResponse(
                "AC6: DEMONSTRAÇÃO AUTOMÁTICA",
                "Demonstração executada com sucesso",
                cenarios,
                logs
        );
    }

    private TrilhaMentoria cenario1_cargaHorariaExcedida() {
        adicionarLog(">> Cenário 1: Carga horária excedida (limite 20h/mês)");

        Mentor mentor = novoMentor("Ana", NivelSenioridade.SENIOR, List.of(Skill.JAVA, Skill.SPRING, Skill.SQL));
        Mentorado mentorado1 = novoMentorado("Bruno", NivelSenioridade.JUNIOR, 5.0, List.of(Skill.JAVA));
        Mentorado mentorado2 = novoMentorado("Carla", NivelSenioridade.JUNIOR, 5.0, List.of(Skill.JAVA));
        Mentorado mentorado3 = novoMentorado("Daniel", NivelSenioridade.JUNIOR, 5.0, List.of(Skill.JAVA));
        Mentorado mentorado4 = novoMentorado("Erica", NivelSenioridade.JUNIOR, 6.0, List.of(Skill.JAVA));

        TrilhaMentoria trilha = new TrilhaMentoria(
                "Trilha Sobrecarregada e Editada",
                3,
                mentor,
                new ArrayList<>(List.of(mentorado1, mentorado2, mentorado3, mentorado4)),
                new ArrayList<>(List.of(Skill.JAVA))
        );

        tentarValidar(trilha, 1);
        return trilha;
    }

    private void cenario2_skillsIncompativeis() {
        adicionarLog(">> Cenário 2: Skills incompatíveis (mentor não tem 70% das skills desejadas)");

        Mentor mentor = novoMentor("Ana", NivelSenioridade.SENIOR, List.of(Skill.JAVA));
        Mentorado mentorado1 = novoMentorado("Bruno", NivelSenioridade.JUNIOR, 5.0,
                List.of(Skill.JAVA, Skill.ANGULAR, Skill.REACT, Skill.DOCKER));

        TrilhaMentoria trilha = new TrilhaMentoria(
                "Trilha Skills",
                3,
                mentor,
                new ArrayList<>(List.of(mentorado1)),
                new ArrayList<>(List.of(Skill.JAVA, Skill.ANGULAR))
        );

        tentarValidar(trilha, 2);
    }

    private void cenario3_nivelDesproporcional() {
        adicionarLog(">> Cenário 3: Nível desproporcional (PLENO, mentorando PLENO)");

        Mentor mentor = novoMentor("Diego", NivelSenioridade.PLENO, List.of(Skill.JAVA, Skill.SPRING));
        Mentorado mentorado1 = novoMentorado("Eva", NivelSenioridade.PLENO, 5.0, List.of(Skill.JAVA, Skill.SPRING));

        TrilhaMentoria trilha = new TrilhaMentoria(
                "Trilha Nível",
                3,
                mentor,
                new ArrayList<>(List.of(mentorado1)),
                new ArrayList<>(List.of(Skill.JAVA))
        );

        tentarValidar(trilha, 3);
    }

     private void cenario4_trilhaValidaPersistida(TrilhaMentoria trilhaInvalida) {
          adicionarLog(">> Cenário 4: Ajuste dos dados da trilha inválida e persistência");

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
              // Cria novos objetos de mentor e mentorado para evitar conflitos com persistence context
              // Os objetos da trilha inválida já estão no persistence context do Hibernate,
              // então reusá-los causaria UPDATE ao invés de INSERT
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

              // Cria uma nova trilha com os objetos novos
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

              // Persiste a nova trilha (que nunca esteve no persistence context)
              trilhaRepositoryPort.persist(novaTrilha);
              adicionarLog("Trilha persistida com sucesso!");

             cenarios.add(new DemonstracaoResponse.CenarioResponse(
                     4,
                     "Trilha Válida e Persistida",
                     "SUCESSO: Trilha persistida com sucesso",
                     null
             ));
         } catch (RuntimeException e) {
             String mensagemErro = "Falha inesperada após ajuste: " + e.getMessage();
             adicionarLog(mensagemErro);

             cenarios.add(new DemonstracaoResponse.CenarioResponse(
                     4,
                     "Trilha Válida e Persistida",
                     "ERRO",
                     e.getMessage()
             ));
         }
     }

    private void tentarValidar(TrilhaMentoria trilha, int numeroCenario) {
        try {
            validador.validarTudo(trilha);
            adicionarLog("    (Inesperado) Trilha passou nas validações.");
            adicionarLog("");

            cenarios.add(new DemonstracaoResponse.CenarioResponse(
                    numeroCenario,
                    "Cenário " + numeroCenario,
                    "INESPERADO: Trilha passou nas validações",
                    null
            ));

        } catch (CargaHorariaExcedidaException | SkillIncompativelException |
                 NivelDesproporcionalException | MaximoMentoradosAtingidosException e) {

            String mensagem = "Exceção tratada: " + e.getClass().getSimpleName() + " -> " + e.getMessage();
            adicionarLog(mensagem);
            adicionarLog("");

            cenarios.add(new DemonstracaoResponse.CenarioResponse(
                    numeroCenario,
                    "Cenário " + numeroCenario,
                    "EXCEÇÃO CAPTURADA",
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
        System.out.println(mensagem);
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
                      // Se falhar na primeira tentativa, tenta novamente
                  } catch (Exception e) {
                      try {
                          Thread.sleep(100);
                          trilhaRepositoryPort.delete(trilha.getId());
                      } catch (Exception retry) {
                          // Ignora se falhar na segunda tentativa também
                          System.out.println("Aviso: Falha ao limpar trilha " + trilha.getNomeDaTrilha() + ": " + retry.getMessage());
                      }
                  }
              }

              // Limpa mentorados órfãos após deletar as trilhas
              trilhaRepositoryPort.limparMentoradosOrfaos();
          } catch (Exception e) {
              System.out.println("Aviso: Erro geral na limpeza de dados: " + e.getMessage());
          }
      }

}

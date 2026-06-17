package app.controller;

import app.exception.*;
import app.model.dto.TrilhaMentoriaDTO;
import app.model.dto.UsuarioCadastroDTO;
import app.service.interfaces.ParticipanteService;
import app.service.interfaces.TrilhaService;
import app.util.ConsoleInput;
import app.util.ConsoleUI;
import app.view.*;

public class MenuPrincipalController {

    private MenuView menu = new MenuView();
    boolean rodando = true;

    private final ParticipanteService participanteService;
    private final TrilhaService trilhaService;
    private final ConsultaView consultaView;
    private final MenuConsultas menuConsultas;
    private final CadastroTrilha cadastroTrilha;
    private final GerenciarTrilha gerenciarTrilha;
    private final GerenciarParticipante gerenciarParticipante;

    public MenuPrincipalController(ParticipanteService participanteService, TrilhaService trilhaService, ConsultaView consultaView) {
        this.participanteService = participanteService;
        this.trilhaService = trilhaService;
        this.consultaView = consultaView;
        this.menuConsultas = new MenuConsultas(consultaView);
        this.cadastroTrilha = new CadastroTrilha(consultaView);
        this.gerenciarTrilha = new GerenciarTrilha(trilhaService, consultaView);
        this.gerenciarParticipante = new GerenciarParticipante(participanteService, consultaView);
    }

    private int lerOpcaoMenu() {
        while(true) {
            try {
                return ConsoleInput.lerNumero();
            } catch (CampoVazioException | EntradaInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void cadastrarParticipante() {
        boolean cadastro = false;
        while (!cadastro) {
            try {
                UsuarioCadastroDTO dto = CadastroParticipante.coletarDadosParticipante();
                participanteService.cadastrar(dto);
                cadastro = true;
            } catch (CampoVazioException | EntradaInvalidaException | SkillDuplicadaException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void cadastrarTrilha() {
        try {
            TrilhaMentoriaDTO dto = cadastroTrilha.cadastrarTrilha();
            if (dto == null) {
                return;
            }
            trilhaService.cadastrar(dto);
        } catch (SkillIncompativelException e) {
            System.out.println(ConsoleUI.erro(e.getMessage()));
            System.out.println("""
                    Como ajustar:
                    - Edite as skills desejadas dos mentorados (menu Gerenciar), ou
                    - Cadastre/escolha um mentor que domine pelo menos 70% das skills desejadas.
                    """);
        } catch (CargaHorariaExcedidaException e) {
            System.out.println(ConsoleUI.erro(e.getMessage()));
            System.out.println("""
                    Como ajustar:
                    - Reduza a quantidade de mentorados na trilha, ou
                    - Diminua as horas dedicadas dos mentorados.
                    """);
        } catch (NivelDesproporcionalException e) {
            System.out.println(ConsoleUI.erro(e.getMessage()));
            System.out.println("""
                    Como ajustar:
                    - Escolha um mentor com senioridade SUPERIOR à de todos os mentorados.
                    """);
        } catch (MaximoMentoradosAtingidosException e) {
            System.out.println(ConsoleUI.erro(e.getMessage()));
            System.out.println("""
                    Como ajustar:
                    - Reduza a quantidade de mentorados para respeitar o limite do mentor.
                    """);
        } catch (ListaVaziaException | LimiteSkillsUltrapassadoException | CampoVazioException e) {
            System.out.println(ConsoleUI.erro(e.getMessage()));
        }
    }

    private void consultar() {
        boolean continuarConsultando = true;
        while (continuarConsultando) {
            menu.consultas();
            int opcao = lerOpcaoMenu();
            try {
                continuarConsultando = menuConsultas.consultar(opcao);
            } catch (CampoVazioException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void gerenciar() {
        boolean continuar = true;
        while (continuar) {
            menu.gerenciar();
            int opcao = lerOpcaoMenu();
            switch (opcao) {
                case 1:
                    gerenciarTrilha.editarTrilha();
                    break;
                case 2:
                    gerenciarTrilha.excluirTrilha();
                    break;
                case 3:
                    gerenciarParticipante.editarParticipante();
                    break;
                case 4:
                    gerenciarParticipante.excluirParticipante();
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void sair() {
        System.out.println("Finalizando programa...");
        ConsoleInput.fecharScanner();
        rodando = false;
    }

    public void iniciaPrograma() {

            while (rodando) {
            menu.exibirOpcoes();
            int opcao = lerOpcaoMenu();

            switch (opcao) {
                case 1:
                    cadastrarParticipante();
                    break;
                case 2:
                    cadastrarTrilha();
                    break;
                case 3:
                    consultar();
                    break;
                case 4:
                    gerenciar();
                    break;
                case 5:
                    sair();
                    break;
                default:
                    System.out.println("\nDigite uma opção válida.");
            }
        }
    }
}

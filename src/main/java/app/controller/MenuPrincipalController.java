package app.controller;

import app.exception.*;
import app.model.dto.TrilhaMentoriaDTO;
import app.model.dto.UsuarioCadastroDTO;
import app.service.interfaces.ParticipanteService;
import app.service.interfaces.TrilhaService;
import app.util.ConsoleInput;
import app.view.CadastroParticipante;
import app.view.CadastroTrilha;
import app.view.ConsultaView;
import app.view.MenuView;

public class MenuPrincipalController {

    private MenuView menu = new MenuView();
    boolean rodando = true;

    private final ParticipanteService participanteService;
    private final TrilhaService trilhaService;
    private final ConsultaView consultaView;
    private final MenuConsultas menuConsultas;
    private final CadastroTrilha cadastroTrilha;

    public MenuPrincipalController(ParticipanteService participanteService, TrilhaService trilhaService, ConsultaView consultaView) {
        this.participanteService = participanteService;
        this.trilhaService = trilhaService;
        this.consultaView = consultaView;
        this.menuConsultas = new MenuConsultas(consultaView);
        this.cadastroTrilha = new CadastroTrilha(consultaView);
    }

    private int lerOpcaoMenu() {
        while(true) {
            try {
                return ConsoleInput.lerNumero();
            } catch (CampoVazioException | EntradaInvalidaException e) {
                System.out.println(e.getMessage());
                iniciaPrograma();
            }
        }
    }

    private void cadastrarParticipante() {
        try {
            UsuarioCadastroDTO dto = CadastroParticipante.coletarDadosParticipante();
            participanteService.cadastrar(dto);
        } catch (CampoVazioException | EntradaInvalidaException | SkillDuplicadaException e) {
            System.out.println(e.getMessage());
            cadastrarParticipante();
        }
    }

    private void cadastrarTrilha() {
        try {
            TrilhaMentoriaDTO dto = cadastroTrilha.cadastrarTrilha();
            trilhaService.cadastrar(dto);
        } catch (ListaVaziaException | LimiteSkillsUltrapassadoException | CampoVazioException
                 | CargaHorariaExcedidaException | SkillIncompativelException | NivelDesproporcionalException
                | MaximoMentoradosAtingidosException e) {
            System.out.println(e.getMessage());
        }
    }

    private void consultar() {
        menu.consultas();
        int opcao = lerOpcaoMenu();
        try {
            menuConsultas.consultar(opcao);
        } catch (CampoVazioException e) {
            System.out.println(e.getMessage());
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
                    sair();
                    break;
                default:
                    System.out.println("\nDigite uma opção válida.");
            }
        }
    }
}

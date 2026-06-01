package app.controller;

import app.exception.CampoVazioException;
import app.exception.LimiteSkillsUltrapassadoException;
import app.exception.ListaVaziaException;
import app.model.dto.TrilhaMentoriaDTO;
import app.model.dto.UsuarioCadastroDTO;
import app.service.interfaces.ParticipanteService;
import app.service.interfaces.TrilhaService;
import app.util.ConsoleInput;
import app.view.CadastroParticipante;
import app.view.CadastroTrilha;
import app.view.MenuView;

public class MenuPrincipalController {

    private MenuView menu = new MenuView();
    boolean rodando = true;

    private final ParticipanteService participanteService;
    private final TrilhaService trilhaService;

    public MenuPrincipalController(ParticipanteService participanteService, TrilhaService trilhaService) {
        this.participanteService = participanteService;
        this.trilhaService = trilhaService;
    }

    private int lerOpcaoMenu() {
        while(true) {
            try {
                return ConsoleInput.lerNumero();
            } catch (CampoVazioException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void cadastrarParticipante() {
        try {
            UsuarioCadastroDTO dto = CadastroParticipante.cadastrarParticipante();
            participanteService.cadastrar(dto);
        } catch (CampoVazioException e) {
            System.out.println(e.getMessage());
        }
    }

    private void cadastrarTrilha() {
        try {
            participanteService.listarMentores();
            TrilhaMentoriaDTO dto = CadastroTrilha.cadastrarTrilha();
            trilhaService.cadastrar(dto);
        } catch (ListaVaziaException | LimiteSkillsUltrapassadoException | CampoVazioException e) {
            System.out.println(e.getMessage());
        }
    }

    private void consultar() {
        menu.consultas();

        try {
            int opcao = lerOpcaoMenu();
            MenuConsultas.consultar(opcao);
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
                    System.out.println("Digite uma opção válida");
            }
        }
    }
}

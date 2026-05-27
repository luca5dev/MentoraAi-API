package app.controller;

import app.dao.ParticipanteImpl;
import app.dao.TrilhaImpl;
import app.dao.interfaces.ParticipanteDAO;
import app.exception.CampoVazioException;
import app.exception.LimiteSkillsUltrapassadoException;
import app.exception.ListaVaziaException;
import app.model.dto.TrilhaMentoriaDTO;
import app.model.dto.UsuarioCadastroDTO;
import app.model.factory.EntityFactory;
import app.service.interfaces.ParticipanteService;
import app.service.interfaces.TrilhaService;
import app.service.useCase.ParticipanteUseCase;
import app.service.useCase.TrilhaUseCase;
import app.util.ConsoleInput;
import app.view.CadastroParticipante;
import app.view.CadastroTrilha;
import app.view.MenuView;

import java.util.InputMismatchException;

public class MenuPrincipalController {

    private MenuView menu = new MenuView();
    boolean rodando = true;

    private static final ParticipanteDAO participante = new ParticipanteImpl();
    private static final TrilhaImpl trilha = new TrilhaImpl();

    private static final ParticipanteService participanteService = new ParticipanteUseCase(participante);

    private static final TrilhaService trilhaService = new TrilhaUseCase(participante, trilha);

    public void iniciaPrograma() {

        int opcao = 0;

        while (rodando) {
            menu.exibirOpcoes();

            try {
                opcao = ConsoleInput.lerNumero();
            } catch (InputMismatchException e) {
                System.out.println("Caractere inválido! " + e.getMessage());
                ConsoleInput.limpaBuffer();
                continue;
            }

            switch (opcao) {
                case 1:
                    try {
                        UsuarioCadastroDTO dto = CadastroParticipante.cadastrarParticipante();
                        participanteService.cadastrar(dto);
                    } catch (CampoVazioException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Caractere inválido!" + e.getMessage());
                        ConsoleInput.limpaBuffer();
                    }
                    break;

                case 2:
                    try {
                        participante.listarMentores();
                        TrilhaMentoriaDTO dto = CadastroTrilha.cadastrarTrilha();
                        trilhaService.cadastrar(dto);
                    } catch (ListaVaziaException | LimiteSkillsUltrapassadoException | CampoVazioException e) {
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Caractere inválido" + e.getMessage());
                    }
                    break;

                case 3:
                    menu.consultas();
                    try {
                        opcao = ConsoleInput.lerNumero();
                        MenuConsultas.consultar(opcao);
                    } catch (InputMismatchException e) {
                        System.out.println("Caractere inválido" + e.getMessage());
                        ConsoleInput.limpaBuffer();
                    }
                    break;

                case 4:
                    System.out.println("Finalizando programa");
                    ConsoleInput.fecharScanner();
                    rodando = false;
                    break;

                default:
                    System.out.println("Digite uma opção válida");
            }
        }
    }
}

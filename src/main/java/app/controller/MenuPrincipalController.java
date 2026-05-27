package app.controller;

import app.dao.ParticipanteImpl;
import app.dao.TrilhaImpl;
import app.exception.CampoVazioException;
import app.exception.LimiteSkillsUltrapassadoException;
import app.exception.ListaVaziaException;
import app.model.factory.EntityFactory;
import app.util.ConsoleInput;
import app.view.MenuView;

import java.util.InputMismatchException;

public class MenuPrincipalController {
    private MenuView menu = new MenuView();
    boolean rodando = true;
    private static final ParticipanteImpl participante = new ParticipanteImpl();
    private static final TrilhaImpl trilha = new TrilhaImpl();

    public void iniciaPrograma() {
        int opcao = 0;
        while (rodando) {
            menu.exibirOpcoes();
            try {
                opcao = ConsoleInput.lerNumero();
            } catch (InputMismatchException e) {
                System.out.println("Caractere inválido! " + e.getMessage());
                ConsoleInput.limpaBuffer();
            }


            switch (opcao) {
                case 1:
                    try {
                        EntityFactory.cadastrarParticipante(participante);
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
                        EntityFactory.cadastrarTrilha(participante);
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

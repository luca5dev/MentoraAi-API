package app.controller;

import app.dao.ParticipanteDAO;
import app.exception.CampoVazioException;
import app.exception.LimiteSkillsUltrapassadoException;
import app.model.factory.EntityFactory;
import app.util.ConsoleInput;
import app.view.menus.MenuView;

import java.util.InputMismatchException;

public class MenuPrincipalController {
    private MenuView menu = new MenuView();
    boolean rodando = true;
    ParticipanteDAO jpa = new ParticipanteDAO();

    public void iniciaPrograma() {
        int opc = 0;
        while (rodando) {
            menu.exibirOpcoes();
            try {
                opc = ConsoleInput.lerNumero();
            } catch (InputMismatchException e) {
                System.out.println("Caractere inválido");
                ConsoleInput.limpaBuffer();
            }


            switch (opc) {
                case 1:
                    try {
                        EntityFactory.cadastrarParticipante(jpa);
                    } catch (CampoVazioException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Caractere inválido!");
                        ConsoleInput.limpaBuffer();
                    }
                    break;

                case 2:
                    try {
                        EntityFactory.cadastrarTrilha();
                    } catch (LimiteSkillsUltrapassadoException | CampoVazioException e) {
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Caractere inválido");
                    }
                    break;

                case 3:
                    menu.consultas();
                    try {
                        opc = ConsoleInput.lerNumero();
                        MenuConsultas.consultar(jpa, opc);
                    } catch (InputMismatchException e) {
                        System.out.println("Caractere inválido");
                        ConsoleInput.limpaBuffer();
                    }
                    break;

                case 4:
                    System.out.println("Finalizando programa");
                    ConsoleInput.fecharScanner();
                    jpa.close();
                    rodando = false;
                    break;

                default:
                    System.out.println("Digite uma opção válida");
            }
        }
    }
}

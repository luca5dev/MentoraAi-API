package app.controller.menuController;

import app.model.factory.EntityFactory;
import app.view.ConsoleInput;
import app.view.CadastroParticipante;
import app.view.menus.MenuView;

import java.util.Scanner;

public class MenuPrincipalController {
    private MenuView menu = new MenuView();
    boolean rodando = true;

    public void iniciaPrograma() {

        while (rodando) {
            menu.exibirOpcoes();
            int opc = ConsoleInput.lerNumero();

            switch (opc) {
                case 1:
                    EntityFactory.novoCadastro();
                    break;

                case 2:
                    System.out.println("Finalizando programa");
                    rodando = false;
                    break;

                default:
                    System.out.println("Digite uma opção válida");
            }
        }
    }
}

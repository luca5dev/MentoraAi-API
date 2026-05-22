package app.controller;

import app.model.factory.EntityFactory;
import app.view.ConsoleInput;
import app.view.menus.MenuView;

public class MenuPrincipalController {
    private MenuView menu = new MenuView();
    boolean rodando = true;

    public void iniciaPrograma() {

        while (rodando) {
            menu.exibirOpcoes();
            int opc = ConsoleInput.lerNumero();

            switch (opc) {
                case 1:
                    EntityFactory.cadastrarParticipante();
                    break;

                case 2:EntityFactory.cadastrarTrilha();
                        break;

                case 3:
                    System.out.println("Finalizando programa");
                    rodando = false;
                    break;

                default:
                    System.out.println("Digite uma opção válida");
            }
        }
    }
}

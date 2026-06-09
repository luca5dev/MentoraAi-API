package app.util;

import app.exception.CampoVazioException;

public class ConfirmarContinuar {

    public static boolean confirmar() {
        while (true) {
            try {
                System.out.print("Deseja continuar? (S/N): ");
                char opcao = Character.toUpperCase(ConsoleInput.lerOpcao());

                if (opcao == 'S') {
                    return true;
                }

                if (opcao == 'N') {
                    return false;
                }
                System.out.println("Opção inválida. Digite apenas S ou N.");
            } catch (CampoVazioException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

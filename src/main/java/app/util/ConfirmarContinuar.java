package app.util;

public class ConfirmarContinuar {

    public static boolean confirmar(){
        char opcao;

        do {
            System.out.println("Deseja continuar? y/n: ");
            opcao = ConsoleInput.lerOpcao();
            opcao = Character.toUpperCase(opcao);
            switch (opcao) {
                case 'Y':
                    return true;
                case 'N':
                    return false;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 'N');
        return false;
    }

}

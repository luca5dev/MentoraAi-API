package app.util;

public class ConfirmarContinuar {

    public static boolean confirmar(){
        char opcao;

        do {
            System.out.println("Deseja continuar? S/N: ");
            opcao = ConsoleInput.lerOpcao();
            opcao = Character.toUpperCase(opcao);
            switch (opcao) {
                case 'S':
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

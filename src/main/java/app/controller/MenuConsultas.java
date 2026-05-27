package app.controller;

import app.view.ConsultaView;

public class MenuConsultas {
    public static void consultar(int opcao) {

        switch (opcao) {
            case 1:
               ConsultaView.consultarParticipantes();
                break;

            case 2:
               ConsultaView.consultarMentores();
                break;

            case 3:
                ConsultaView.consultarMentorados();
                break;

            case 4:
                ConsultaView.consultarTrilhas();
            default:
                System.out.println("Opção inválida!");
        }
    }
}

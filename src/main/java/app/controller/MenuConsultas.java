package app.controller;

import app.view.ConsultaView;

public class MenuConsultas {

    private final ConsultaView consultaView;

    public MenuConsultas(ConsultaView consultaView) {
        this.consultaView = consultaView;
    }

    public void consultar(int opcao) {
        switch (opcao) {
            case 1:
               consultaView.consultarTodosParticipantesEmMemoria();
                break;
            case 2:
               consultaView.consultarMentoresEmMemoria();
                break;
            case 3:
                consultaView.consultarMentoradosEmMemoria();
                break;
            case 4:
                consultaView.consultarTrilhasPersistidas();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
}

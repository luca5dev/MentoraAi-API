package app.controller;

import app.view.ConsultaView;

public class MenuConsultas {

    private final ConsultaView consultaView;

    public MenuConsultas(ConsultaView consultaView) {
        this.consultaView = consultaView;
    }

    public boolean consultar(int opcao) {
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
            case 0:
                return false;
            default:
                System.out.println("Opção inválida!");
        }
        return true;
    }
}

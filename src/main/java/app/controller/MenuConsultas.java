package app.controller;

import app.dao.ParticipanteDAO;

public class MenuConsultas {
    public static void consultar(ParticipanteDAO jpa, int opc) {

        switch (opc) {
            case 1:
                jpa.listarParticipantes();
                break;

            case 2:
                jpa.listarMentores();
                break;

            case 3:
                jpa.listarMentorados();
                break;
            default:
                System.out.println("Opção inválida!");
        }

    }
}

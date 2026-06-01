package app;

import app.config.JPAUtil;
import app.controller.MenuPrincipalController;
import app.dao.ParticipanteImpl;
import app.dao.TrilhaImpl;
import app.dao.interfaces.ParticipanteDAO;
import app.dao.interfaces.TrilhaDAO;
import app.service.interfaces.ParticipanteService;
import app.service.interfaces.TrilhaService;
import app.service.useCase.ParticipanteUseCase;
import app.service.useCase.TrilhaUseCase;

import java.util.InputMismatchException;

public class MentoraAiApplication {

    public static void main(String[] args) {

        ParticipanteDAO participanteDAO = new ParticipanteImpl();
        TrilhaDAO trilhaDAO = new TrilhaImpl();

        ParticipanteService participanteService = new ParticipanteUseCase(participanteDAO);
        TrilhaService trilhaService = new TrilhaUseCase(participanteService, trilhaDAO);

        MenuPrincipalController controller = new MenuPrincipalController(participanteService, trilhaService);

        try {
            controller.iniciaPrograma();
        } finally {
            JPAUtil.fecharFactory();
        }
    }
}

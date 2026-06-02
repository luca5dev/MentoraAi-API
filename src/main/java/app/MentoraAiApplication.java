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
import app.view.ConsultaView;

public class MentoraAiApplication {

    public static void main(String[] args) {

        ParticipanteDAO participanteDAO = new ParticipanteImpl();
        TrilhaDAO trilhaDAO = new TrilhaImpl();

        ParticipanteService participanteService = new ParticipanteUseCase(participanteDAO);
        TrilhaService trilhaService = new TrilhaUseCase(participanteService, trilhaDAO);
        ConsultaView consultaView = new ConsultaView(participanteService, trilhaService);

        MenuPrincipalController controller = new MenuPrincipalController(participanteService, trilhaService, consultaView);

        try {
            //Inicia a demonstração solicitada no AC6 antes do menu interativo
            new DemonstracaoAutomatica(trilhaService).executar();

            controller.iniciaPrograma();
        } finally {
            JPAUtil.fecharFactory();
        }
    }
}

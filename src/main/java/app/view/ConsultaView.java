package app.view;

import app.dao.ParticipanteImpl;
import app.dao.TrilhaImpl;
import app.model.entity.Mentor;
import app.model.entity.ParticipantePrograma;
import app.model.entity.TrilhaMentoria;
import app.service.interfaces.ParticipanteService;
import app.service.interfaces.TrilhaService;

import java.util.List;

public class ConsultaView {

    private final ParticipanteService participanteService;
    private final TrilhaService trilhaService;

    public ConsultaView(ParticipanteService participanteService, TrilhaService trilhaService) {
        this.participanteService = participanteService;
        this.trilhaService = trilhaService;
    }

    publ

    public static void consultarParticipantes() {
        List<ParticipantePrograma> participantes = new ParticipanteImpl().listarParticipantes();

        if (participantes.isEmpty()) {
            System.out.println("Nenhum participante cadastrado no banco de dados!");
            return;
        }

        System.out.println("\n--- LISTA DE PARTICIPANTES ---");
        for (ParticipantePrograma p : participantes) {
            System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Senioridade: " + p.getNivelSenioridade());
        }
        System.out.println("-------------------------------\n");
    }

    public static void consultarMentores() {
        List<Mentor> mentores = new ParticipanteImpl().listarMentores();

        if (mentores.isEmpty()) {
            System.out.println("Nenhum participante cadastrado no banco de dados!");
            return;
        }

        System.out.println("\n--- LISTA DE MENTORES ---");
        for (Mentor m : mentores) {
            System.out.println("ID: " + m.getId() + " | Nome: " + m.getNome() + " | Senioridade: " + m.getNivelSenioridade());
        }
        System.out.println("-------------------------------\n");
    }

    public static void consultarMentorados() {
        List<Mentor> mentorados = new ParticipanteImpl().listarMentores();

        if (mentorados.isEmpty()) {
            System.out.println("Nenhum participante cadastrado no banco de dados!");
            return;
        }

        System.out.println("\n--- LISTA DE MENTORADOS ---");
        for (Mentor m : mentorados) {
            System.out.println("ID: " + m.getId() + " | Nome: " + m.getNome() + " | Senioridade: " + m.getNivelSenioridade());
        }
        System.out.println("-------------------------------\n");
    }

    public static void consultarTrilhas() {
        List<TrilhaMentoria> trilhas = new TrilhaImpl().listarTrilhas();

        if (trilhas.isEmpty()) {
            System.out.println("Nenhuma trilha criada!");
            return;
        }

        System.out.println("\n--- LISTA DE TRILHAS ---");
        for (TrilhaMentoria t : trilhas) {
            System.out.println("ID: " + t.getId() + " | Nome: " + t.getNomeDaTrilha() + " | Mentor: " + t.getMentor() + " | Skill da trilha: " + t.getSkillsDaTrilha());
        }
        System.out.println("-------------------------------\n");
    }
}

package app.view;

import app.controller.MenuConsultas;
import app.dao.ParticipanteImpl;
import app.dao.TrilhaImpl;
import app.model.entity.Mentor;
import app.model.entity.Mentorado;
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

    public void consultarMentoresEmMemoria() {
        List<Mentor> mentores = participanteService.listarMentoresEmMemoria();
        if (mentores.isEmpty()) {
            System.out.println("Nenhum mentor cadastrado nesta sessão");
            return;
        }
        System.out.println("\n--- Mentores em memória (sessão atual) ---");
        mentores.forEach(mentor -> System.out.println(
                "ID temporário: " + mentor.getId()
                + " | Nome: " + mentor.getNome()
                + " | Senioridade: " + mentor.getNivelSenioridade()
                + " | Skills: " + mentor.getSkills()
                + " | Valor/hora: R$" + mentor.getValorHora()
        ));
        System.out.println("-------------------------------------------\n");
    }

    public void consultarMentoradosEmMemoria() {
        List<Mentorado> mentorados = participanteService.listarMentoradosEmMemoria();
        if (mentorados.isEmpty()) {
            System.out.println("\nNenhum mentorado cadastrado nesta sessão.\n");
            return;
        }
        System.out.println("\n--- Mentorados em memória (sessão atual) ---");
        mentorados.forEach(mentorado -> System.out.println(
                "ID temporário: " + mentorado.getId()
                + " | Nome: " + mentorado.getNome()
                + " | Senioridade: " + mentorado.getNivelSenioridade()
                + " | Skills: " +  mentorado.getSkills()
                + " | Valor/hora: R$" + mentorado.getValorHora()
                + " | Skills desejadas: " + mentorado.getSkillsDesejadas()
        ));
        System.out.println("----------------------------------------\n");
    }

    public void consultarTodosParticipantesEmMemoria() {
        consultarMentoresEmMemoria();
        consultarMentoradosEmMemoria();
    }

    public void consultarTrilhasPersistidas() {
        List<TrilhaMentoria> trilhas = trilhaService.listarTrilhasPersistidas();
        if (trilhas.isEmpty()) {
            System.out.println("\nNenhuma trilha cadastrada no banco de dados.\n");
            return;
        }
        System.out.println("\n--- Trilhas cadastradas ---");
        trilhas.forEach(trilha -> {
                    System.out.println("ID: " + trilha.getId()
                            + " | Nome: " + trilha.getNomeDaTrilha()
                            + " | Duração: " + trilha.getDuracaoMeses() + " meses"
                            + " | Skills da trilha: " + trilha.getSkillsDaTrilha());

                    Mentor mentor = trilha.getMentor();
                    if (mentor != null) {
                        System.out.println("    Mentor: " + mentor.getNome()
                                + " (" + mentor.getNivelSenioridade() + ")");
                    }
                    System.out.println("    Mentorados (" + trilha.getMentorados().size() + "):");
                    trilha.getMentorados().forEach(mentorado -> System.out.println(
                            "   - " + mentorado.getNome() + " (" + mentorado.getNivelSenioridade() + ")"
                    ));
                    System.out.println();
                });
        System.out.println("--------------------------------------\n");
    }
}

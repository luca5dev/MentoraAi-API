package app.view;

import app.model.entity.Mentor;
import app.model.entity.Mentorado;
import app.model.entity.TrilhaMentoria;
import app.service.interfaces.ParticipanteService;
import app.service.interfaces.TrilhaService;
import app.util.ConsoleUI;

import java.util.List;

public class ConsultaView {

    private final ParticipanteService participanteService;
    private final TrilhaService trilhaService;

    public ConsultaView(ParticipanteService participanteService, TrilhaService trilhaService) {
        this.participanteService = participanteService;
        this.trilhaService = trilhaService;
    }

    public boolean mentoradoExiste(long id) {
        return participanteService.listarMentoradosEmMemoria().stream()
                .anyMatch(mentorado -> id == mentorado.getId());
    }

    public boolean mentorExiste(long id) {
        return participanteService.listarMentoresEmMemoria().stream()
                .anyMatch(mentor -> id == mentor.getId());
    }

    public boolean existemParticipantesParaTrilha() {
        return !participanteService.listarMentoresEmMemoria().isEmpty()
                && !participanteService.listarMentoradosEmMemoria().isEmpty();
    }

    public void consultarMentoresEmMemoria() {
        List<Mentor> mentores = participanteService.listarMentoresEmMemoria();
        if (mentores.isEmpty()) {
            System.out.println("\nNenhum mentor cadastrado nesta sessão.");
            return;
        }
        ConsoleUI.cabecalho("Mentores em memória (sessão atual)");
        mentores.forEach(mentor -> {
            System.out.println("ID temporário: " + mentor.getId());
            System.out.println("   Nome.......: " +  mentor.getNome());
            System.out.println("   Senioridade: " + mentor.getNivelSenioridade());
            System.out.println("   Skills.....: " + ConsoleUI.formataListaSkills(mentor.getSkills()));
            System.out.println("   Valor/hora.: " + ConsoleUI.moeda(mentor.getValorHora()));
            System.out.println();
        });
        ConsoleUI.separador();
    }

    public void consultarMentoradosEmMemoria() {
        List<Mentorado> mentorados = participanteService.listarMentoradosEmMemoria();
        if (mentorados.isEmpty()) {
            System.out.println("\nNenhum mentorado cadastrado nesta sessão.");
            return;
        }
        ConsoleUI.cabecalho("Mentorados em memória (sessão atual)");
        mentorados.forEach(mentorado -> {
            System.out.println("ID temporário...: " + mentorado.getId());
            System.out.println("   Nome.........: " + mentorado.getNome());
            System.out.println("   Senioridade..: " + mentorado.getNivelSenioridade());
            System.out.println("   Skills.......: " + ConsoleUI.formataListaSkills(mentorado.getSkills()));
            System.out.println("   Skills desejadas: " + ConsoleUI.formataListaSkills(mentorado.getSkillsDesejadas()));
            System.out.println("   Valor/hora...: " + ConsoleUI.moeda(mentorado.getValorHora()));
            System.out.println();
        });
        ConsoleUI.separador();
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
        ConsoleUI.cabecalho("Trilhas cadastradas no banco de dados.");
        trilhas.forEach(trilha -> {
                    System.out.println("ID: " + trilha.getId()
                            + " | Nome: " + trilha.getNomeDaTrilha()
                            + " | Duração: " + trilha.getCicloEmMeses() + " meses"
                            + " | Skills da trilha: " + ConsoleUI.formataListaSkills(trilha.getSkillsDaTrilha()));

                    Mentor mentor = trilha.getMentor();
                    if (mentor != null) {
                        System.out.println("        Mentor: " + mentor.getNome()
                                + " (" + mentor.getNivelSenioridade() + ")");
                    }
                    System.out.println("        Mentorados (" + trilha.getMentorados().size() + "):");
                    trilha.getMentorados().forEach(mentorado -> System.out.println(
                            "       - " + mentorado.getNome() + " (" + mentorado.getNivelSenioridade() + ")"
                    ));
                    System.out.println();
                });
        ConsoleUI.separador();
    }
}

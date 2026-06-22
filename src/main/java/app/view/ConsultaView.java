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
            System.out.println("   Skills desej.: " + ConsoleUI.formataListaSkills(mentorado.getSkillsDesejadas()));
            System.out.println("   Valor/hora...: " + ConsoleUI.moeda(mentorado.getValorHora()));
            System.out.println();
        });
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
        trilhas.forEach(trilha -> {
            ConsoleUI.cabecalho("Dados da trilha");
            System.out.println("ID: " + trilha.getId());
            System.out.println("Nome da trilha: " + trilha.getNomeDaTrilha());
            System.out.println("Duração: " + trilha.getCicloEmMeses() + " meses");

            System.out.println("\nMentor:");
            System.out.println("- " + trilha.getMentor().getNome()
            + " | Senioridade: "  + trilha.getMentor().getNivelSenioridade()
            + " | Skills: " + ConsoleUI.formataListaSkills(trilha.getMentor().getSkills()));

            System.out.println("\nMentorados:");
            trilha.getMentorados().forEach(mentorado -> System.out.println(
                    "- " + mentorado.getNome()
                    + " | Senioridade: " + mentorado.getNivelSenioridade()
                    + " | Skills desejadas: " + ConsoleUI.formataListaSkills(mentorado.getSkillsDesejadas())
            ));
            System.out.println("\nSkills que serão ensinadas: " + ConsoleUI.formataListaSkills(trilha.getSkillsDaTrilha()));
            System.out.println("Custo de oportunidade mensal: " + ConsoleUI.moeda(trilha.calcularCustoMensalTotal()));
            System.out.println("Custo de oportunidade total do ciclo: " + ConsoleUI.moeda(trilha.calcularCustoTotalDoCiclo()));
        });
    }
}

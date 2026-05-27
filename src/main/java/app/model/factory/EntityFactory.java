package app.model.factory;

import app.dao.ParticipanteImpl;
import app.dao.TrilhaImpl;
import app.exception.ListaVaziaException;
import app.exception.MaximoMentoradosAtingidosException;
import app.model.dto.TrilhaMentoriaDTO;
import app.model.dto.UsuarioCadastroDTO;
import app.model.entity.Mentor;
import app.model.entity.Mentorado;
import app.model.entity.ParticipantePrograma;
import app.model.entity.TrilhaMentoria;
import app.view.CadastroParticipante;
import app.view.CadastroTrilha;


public class EntityFactory {

    public static void cadastrarParticipante(ParticipanteImpl dao) {
        AdicionarSkills addSkills = new AdicionarSkills();
        UsuarioCadastroDTO dto = CadastroParticipante.cadastrarParticipante();
        ParticipantePrograma p = null;

        switch (dto.getOpcao()) {
            case 1:
                addSkills.skillBase(dto);
                p = new Mentor(dto.getNome(), dto.getNivelSenioridade(), dto.getSkills(), dto.getValorHora(), 0.0);
                dao.persist(p);
                break;

            case 2:
                addSkills.skillBase(dto);
                addSkills.skillsParaMentorados(dto);
                p = new Mentorado(dto.getNome(), dto.getNivelSenioridade(), dto.getSkills(), dto.getValorHora(), 0.0, dto.getSkillsDesejadas());
                dao.persist(p);
                break;

            default: System.out.println("Escolha uma opção válida");
        }

        if (p == null) {
           throw new ListaVaziaException("Nenhum participante cadastrado");
        }
    }

    public static void cadastrarTrilha(ParticipanteImpl participanteDAO) {
        TrilhaMentoriaDTO dto = CadastroTrilha.cadastrarTrilha();
        TrilhaImpl trilha = null;

        Mentor mentor = participanteDAO.buscarMentorId(dto.getIdMentor())
                .orElseThrow(() -> new RuntimeException("Mentor não encontrado com o ID informado!"));

        if (mentor.getMaximoMentorados() > 4){
            throw new MaximoMentoradosAtingidosException("O limite máximo de mentorados para esse mentor foi excedido.");
        }

        TrilhaMentoria novaTrilha = new TrilhaMentoria("Teste", 2, mentor, dto.getMentorados(), dto.getSkills());
        trilha.persist(novaTrilha);
        // 4. Salva a nova trilha no banco de dados
        // Nota: Você precisará de um TrilhaDAO ou usar o EntityManager correspondente para persistir a trilha
        // exemplo: trilhaDAO.persist(novaTrilha);

        System.out.println("Trilha de mentoria criada e vinculada com sucesso!");
    }
}
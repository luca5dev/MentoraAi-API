package app.service.useCase;

import app.dao.interfaces.ParticipanteDAO;
import app.model.dto.UsuarioCadastroDTO;
import app.model.entity.Mentor;
import app.model.entity.Mentorado;
import app.model.entity.ParticipantePrograma;
import app.model.factory.AdicionarSkills;
import app.model.factory.EntityFactory;
import app.service.interfaces.ParticipanteService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParticipanteUseCase implements ParticipanteService {

    private final ParticipanteDAO dao;
    private final AdicionarSkills adicionarSkills = new AdicionarSkills();
    private final List<Mentor> mentoresEmMemoria = new ArrayList<>();
    private final List<Mentorado> mentoradosEmMemoria = new ArrayList<>();

    //ID temporário (em memória) só para o usuário conseguir referenciar no console
    private long sequenciaMentor = 1L;
    private long sequenciaMentorado = 1L;

    public ParticipanteUseCase(ParticipanteDAO dao) {
    this.dao = dao;
    }

    @Override
    public void cadastrar(UsuarioCadastroDTO dto) {
        adicionarSkills.skillBase(dto);

        if (dto.getOpcao() == 2) {
            adicionarSkills.skillsParaMentorados(dto);
        }

        ParticipantePrograma participantePrograma = EntityFactory.criarParticipante(dto);
        if (participantePrograma instanceof Mentor mentor) {
            mentor.setId(sequenciaMentor++);
            mentoresEmMemoria.add(mentor);
            System.out.println("Mentor: " + mentor.getNome() + " cadastrado em memória (id temporário = " +  mentor.getId() + ").");
        } else if (participantePrograma instanceof Mentorado mentorado) {
            mentorado.setId(sequenciaMentorado++);
            mentoradosEmMemoria.add(mentorado);
            System.out.println("Mentorado: " + mentorado.getNome() + " cadastrado em memória (id temporário = " + mentorado.getId() + ").");
        }
    }

    @Override
    public List<Mentor> listarMentoresEmMemoria() {
        return Collections.unmodifiableList(mentoresEmMemoria);
    }

    public List<Mentorado> listarMentoradosEmMemoria() {
        return Collections.unmodifiableList(mentoradosEmMemoria);
    }

    public Mentor buscarMentorPorIdTemporario(Long id) {
        return mentoresEmMemoria.stream()
                .filter(mentor -> id.equals(mentor.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Mentor não encontrado em memória."));
    }

    public List<Mentorado> buscarMentoradosPorIdsTemporarios(List<Long> ids) {
        return mentoradosEmMemoria.stream()
                .filter(mentorado -> ids.contains(mentorado.getId()))
                .toList();
    }

    public void limparMemoriaAposPersistencia() {
        mentoresEmMemoria.clear();
        mentoradosEmMemoria.clear();
        sequenciaMentor = 1L;
        sequenciaMentorado = 1L;
    }
}

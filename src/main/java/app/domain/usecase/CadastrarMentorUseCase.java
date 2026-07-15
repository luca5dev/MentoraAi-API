package app.domain.usecase;

import app.domain.model.Mentor;
import app.domain.port.in.CadastrarMentorDados;
import app.domain.port.in.CadastrarMentorPort;
import app.domain.port.out.ParticipanteRepositoryPort;

public class CadastrarMentorUseCase implements CadastrarMentorPort {

    private final ParticipanteRepositoryPort participanteRepositoryPort;

    public CadastrarMentorUseCase(ParticipanteRepositoryPort participanteRepositoryPort) {
        this.participanteRepositoryPort = participanteRepositoryPort;
    }

    @Override
    public Mentor cadastrar(CadastrarMentorDados dados) {
        Mentor mentor = new Mentor(
                dados.nome(),
                dados.nivelSenioridade(),
                dados.skills(),
                dados.valorHora(),
                dados.horasDedicadas()
        );

        if (dados.maximoMentorados() != null) {
            mentor.setMaximoMentorados(dados.maximoMentorados());
        }

        participanteRepositoryPort.persist(mentor);
        return mentor;
    }
}

package app.domain.usecase;

import app.domain.model.Mentorado;
import app.domain.port.in.CadastrarMentoradoDados;
import app.domain.port.in.CadastrarMentoradoPort;
import app.domain.port.out.ParticipanteRepositoryPort;

public class CadastrarMentoradoUseCase implements CadastrarMentoradoPort {

    private final ParticipanteRepositoryPort participanteRepositoryPort;

    public CadastrarMentoradoUseCase(ParticipanteRepositoryPort participanteRepositoryPort) {
        this.participanteRepositoryPort = participanteRepositoryPort;
    }

    @Override
    public Mentorado cadastrar(CadastrarMentoradoDados dados) {
        Mentorado mentorado = new Mentorado(
                dados.nome(),
                dados.nivelSenioridade(),
                dados.skills(),
                dados.valorHora(),
                dados.horasDedicadas(),
                dados.skillsDesejadas()
        );

        participanteRepositoryPort.persist(mentorado);
        return mentorado;
    }
}

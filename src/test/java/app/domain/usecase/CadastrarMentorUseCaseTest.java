package app.domain.usecase;

import app.domain.exception.CargaHorariaExcedidaException;
import app.domain.model.Mentor;
import app.domain.model.NivelSenioridade;
import app.domain.model.Skill;
import app.domain.port.in.CadastrarMentorDados;
import app.domain.port.out.ParticipanteRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CadastrarMentorUseCaseTest {

    @Mock
    private ParticipanteRepositoryPort participanteRepositoryPort;

    @InjectMocks
    private CadastrarMentorUseCase cadastrarMentorUseCase;

    @Test
    void deveCadastrarMentorComHorasDedicadasInformadas() {
        CadastrarMentorDados dados = new CadastrarMentorDados(
                "Mentor",
                NivelSenioridade.SENIOR,
                List.of(Skill.JAVA, Skill.SPRING),
                150.0,
                12.0,
                3
        );

        Mentor mentor = cadastrarMentorUseCase.cadastrar(dados);

        assertEquals(12.0, mentor.getHorasDedicadas());
        assertEquals(3, mentor.getMaximoMentorados());

        ArgumentCaptor<Mentor> captor = ArgumentCaptor.forClass(Mentor.class);
        verify(participanteRepositoryPort).persist(captor.capture());
        assertEquals(12.0, captor.getValue().getHorasDedicadas());
    }

    @Test
    void deveLancarQuandoHorasDedicadasDoMentorPassamDoLimite() {
        CadastrarMentorDados dados = new CadastrarMentorDados(
                "Mentor",
                NivelSenioridade.SENIOR,
                List.of(Skill.JAVA, Skill.SPRING),
                150.0,
                25.0,
                3
        );

        assertThrows(CargaHorariaExcedidaException.class, () -> cadastrarMentorUseCase.cadastrar(dados));
    }
}

package app.domain.usecase;

import app.domain.model.Mentor;
import app.domain.model.Mentorado;
import app.domain.model.NivelSenioridade;
import app.domain.model.Skill;
import app.domain.model.TrilhaMentoria;
import app.domain.port.in.CriarTrilhaDados;
import app.domain.port.out.ParticipanteRepositoryPort;
import app.domain.port.out.TrilhaRepositoryPort;
import app.domain.validator.ValidadorTrilhaDomain;
import app.domain.exception.ParticipanteNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarTrilhaUseCaseTest {

    @Mock
    private ParticipanteRepositoryPort participanteRepositoryPort;

    @Mock
    private TrilhaRepositoryPort trilhaRepositoryPort;

    @Mock
    private ValidadorTrilhaDomain validadorTrilhaDomain;

    @InjectMocks
    private CriarTrilhaUseCase criarTrilhaUseCase;

    @Test
    void deveCriarTrilhaQuandoDadosValidos() {
        Mentor mentor = new Mentor("Mentor", NivelSenioridade.SENIOR,
                new ArrayList<>(List.of(Skill.JAVA, Skill.SPRING, Skill.SQL)), 150.0);
        mentor.setId(10L);

        Mentorado mentorado = new Mentorado("Mentorado", NivelSenioridade.JUNIOR,
                new ArrayList<>(List.of(Skill.JAVA)), 60.0, 4.0,
                new ArrayList<>(List.of(Skill.JAVA, Skill.SPRING)));
        mentorado.setId(20L);

        CriarTrilhaDados dados = new CriarTrilhaDados(
                "Trilha Backend",
                4,
                mentor.getId(),
                List.of(mentorado.getId()),
                List.of(Skill.JAVA, Skill.SPRING)
        );

        when(participanteRepositoryPort.buscarMentorPorId(10L)).thenReturn(Optional.of(mentor));
        when(participanteRepositoryPort.buscarMentoradosPorIds(List.of(20L))).thenReturn(List.of(mentorado));
        doNothing().when(validadorTrilhaDomain).validarTudo(org.mockito.ArgumentMatchers.any(TrilhaMentoria.class));

        TrilhaMentoria resultado = criarTrilhaUseCase.executar(dados);

        assertEquals("Trilha Backend", resultado.getNomeDaTrilha());
        assertEquals(4, resultado.getCicloEmMeses());
        assertEquals(mentor, resultado.getMentor());
        assertEquals(1, resultado.getMentorados().size());

        ArgumentCaptor<TrilhaMentoria> captor = ArgumentCaptor.forClass(TrilhaMentoria.class);
        verify(trilhaRepositoryPort).persist(captor.capture());
        assertEquals("Trilha Backend", captor.getValue().getNomeDaTrilha());
    }

    @Test
    void deveLancarQuandoMentorNaoEncontrado() {
        CriarTrilhaDados dados = new CriarTrilhaDados(
                "Trilha Backend",
                4,
                999L,
                List.of(1L),
                List.of(Skill.JAVA)
        );

        when(participanteRepositoryPort.buscarMentorPorId(999L)).thenReturn(Optional.empty());

        assertThrows(ParticipanteNaoEncontradoException.class, () -> criarTrilhaUseCase.executar(dados));
    }
}


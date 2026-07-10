package app.domain.usecase;

import app.adapters.in.web.dto.DemonstracaoResponse;
import app.domain.exception.CargaHorariaExcedidaException;
import app.domain.exception.NivelDesproporcionalException;
import app.domain.exception.SkillIncompativelException;
import app.domain.model.TrilhaMentoria;
import app.domain.port.out.TrilhaRepositoryPort;
import app.domain.validator.ValidadorTrilhaDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExecutarDemonstracaoAutomaticaUseCaseTest {

    @Mock
    private TrilhaRepositoryPort trilhaRepositoryPort;

    @Mock
    private ValidadorTrilhaDomain validador;

    @InjectMocks
    private ExecutarDemonstracaoAutomaticaUseCase useCase;

    @Test
    void deveCapturarExcecoesEAjustarTrilhaAntesDePersistir() {
        when(trilhaRepositoryPort.listarTodasTrilhas()).thenReturn(List.of());
        simularExcecoesDosTresPrimeirosCenarios();

        DemonstracaoResponse response = useCase.executar();

        assertEquals("AC6: DEMONSTRAÇÃO AUTOMÁTICA", response.titulo());
        assertEquals("Demonstração executada com sucesso", response.mensagem());
        assertEquals(4, response.cenarios().size());

        assertEquals("EXCEÇÃO CAPTURADA", response.cenarios().get(0).resultado());
        assertTrue(response.cenarios().get(0).exception().contains("CargaHorariaExcedidaException"));

        assertEquals("EXCEÇÃO CAPTURADA", response.cenarios().get(1).resultado());
        assertTrue(response.cenarios().get(1).exception().contains("SkillIncompativelException"));

        assertEquals("EXCEÇÃO CAPTURADA", response.cenarios().get(2).resultado());
        assertTrue(response.cenarios().get(2).exception().contains("NivelDesproporcionalException"));

        assertEquals("SUCESSO: Trilha persistida com sucesso", response.cenarios().get(3).resultado());

        verify(trilhaRepositoryPort).limparMentoradosOrfaos();

        ArgumentCaptor<TrilhaMentoria> trilhaCaptor = ArgumentCaptor.forClass(TrilhaMentoria.class);
        verify(trilhaRepositoryPort).persist(trilhaCaptor.capture());

        TrilhaMentoria trilhaPersistida = trilhaCaptor.getValue();
        assertEquals("Trilha Sobrecarregada e Editada", trilhaPersistida.getNomeDaTrilha());
        assertEquals(3, trilhaPersistida.getMentorados().size());
        assertEquals(15.0, cargaHorariaTotal(trilhaPersistida), 0.01);
        assertTrue(cargaHorariaTotal(trilhaPersistida) <= 20.0);
    }

    @Test
    void deveRetornarCenarioDeErroQuandoPersistenciaFalhaAposAjuste() {
        when(trilhaRepositoryPort.listarTodasTrilhas()).thenReturn(List.of());
        simularExcecoesDosTresPrimeirosCenarios();
        doThrow(new RuntimeException("banco indisponivel"))
                .when(trilhaRepositoryPort).persist(any(TrilhaMentoria.class));

        DemonstracaoResponse response = useCase.executar();

        DemonstracaoResponse.CenarioResponse cenarioPersistencia = response.cenarios().get(3);
        assertEquals(4, cenarioPersistencia.numero());
        assertEquals("Trilha Válida e Persistida", cenarioPersistencia.titulo());
        assertEquals("ERRO", cenarioPersistencia.resultado());
        assertEquals("banco indisponivel", cenarioPersistencia.exception());
    }

    private void simularExcecoesDosTresPrimeirosCenarios() {
        AtomicInteger chamada = new AtomicInteger();

        doAnswer(invocation -> {
            int numeroChamada = chamada.incrementAndGet();

            if (numeroChamada == 1) {
                throw new CargaHorariaExcedidaException("Carga horaria acima do limite");
            }

            if (numeroChamada == 2) {
                throw new SkillIncompativelException("Skills incompativeis");
            }

            if (numeroChamada == 3) {
                throw new NivelDesproporcionalException("Nivel desproporcional");
            }

            return null;
        }).when(validador).validarTudo(any(TrilhaMentoria.class));
    }

    private double cargaHorariaTotal(TrilhaMentoria trilha) {
        return trilha.getMentorados().stream()
                .mapToDouble(mentorado -> mentorado.getHorasDedicadas())
                .sum();
    }
}

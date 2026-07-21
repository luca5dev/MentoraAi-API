package app.adapters.in.web;

import app.adapters.in.web.dto.ErroResponse;
import app.config.MensagensLogger;
import app.domain.exception.CampoVazioException;
import app.domain.exception.CargaHorariaExcedidaException;
import app.domain.exception.EntradaInvalidaException;
import app.domain.exception.LimiteSkillsUltrapassadoException;
import app.domain.exception.ListaVaziaException;
import app.domain.exception.MaximoMentoradosAtingidosException;
import app.domain.exception.NivelDesproporcionalException;
import app.domain.exception.NumeroForaDoIntervaloException;
import app.domain.exception.ParticipanteNaoEncontradoException;
import app.domain.exception.SkillDuplicadaException;
import app.domain.exception.SkillIncompativelException;
import app.domain.exception.ValorDaHoraInvalidoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

   private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

   @ExceptionHandler({
           CargaHorariaExcedidaException.class,
           CampoVazioException.class,
           EntradaInvalidaException.class,
           LimiteSkillsUltrapassadoException.class,
           ListaVaziaException.class,
           MaximoMentoradosAtingidosException.class,
           NivelDesproporcionalException.class,
           NumeroForaDoIntervaloException.class,
           SkillIncompativelException.class,
           ParticipanteNaoEncontradoException.class,
           SkillDuplicadaException.class,
           ValorDaHoraInvalidoException.class
   })
   @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
   public ErroResponse tratarRegraDeNegocio(RuntimeException exception) {
      MensagensLogger.warn(LOGGER,
              MensagensLogger.ERRO_REGRA_NEGOCIO_TRATADO,
              exception.getClass().getSimpleName(),
              exception);

      return new ErroResponse(
              HttpStatus.UNPROCESSABLE_ENTITY.value(),
              MensagensLogger.ERRO_PROCESSAMENTO_ENTIDADE,
              exception.getMessage(),
              LocalDateTime.now());
   }

}

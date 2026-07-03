package app.adapters.in.web;

import app.adapters.in.web.dto.ErroResponse;
import app.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

   @ExceptionHandler({
           CargaHorariaExcedidaException.class,
           CampoVazioException.class,
           EntradaInvalidaException.class,
           MaximoMentoradosAtingidosException.class,
           NivelDesproporcionalException.class,
           SkillIncompativelException.class,
           ParticipanteNaoEncontradoException.class
   })
   @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
   public ErroResponse tratarRegraDeNegocio(RuntimeException exception) {
      return new ErroResponse(
              HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de processamento da entidade", exception.getMessage(), LocalDateTime.now());
   }

}

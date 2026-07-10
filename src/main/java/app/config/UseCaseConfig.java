package app.config;

import app.domain.port.in.*;
import app.domain.port.out.ParticipanteRepositoryPort;
import app.domain.port.out.TrilhaRepositoryPort;
import app.domain.usecase.*;
import app.domain.validator.ValidadorTrilhaDomain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

   @Bean
   public ValidadorTrilhaDomain validadorTrilhaDomain() {
      return new ValidadorTrilhaDomain();
   }

   @Bean
   public CadastrarMentorPort cadastrarMentorPort(ParticipanteRepositoryPort participanteRepositoryPort) {
      return new CadastrarMentorUseCase(participanteRepositoryPort);
   }

   @Bean
   public CadastrarMentoradoPort cadastrarMentoradoPort(ParticipanteRepositoryPort participanteRepositoryPort) {
      return new CadastrarMentoradoUseCase(participanteRepositoryPort);
   }

   @Bean
   public CriarTrilhaPort criarTrilhaPort(
           ParticipanteRepositoryPort participanteRepositoryPort,
           TrilhaRepositoryPort trilhaRepositoryPort,
           ValidadorTrilhaDomain validadorTrilhaDomain
   ) {
      return new CriarTrilhaUseCase(participanteRepositoryPort, trilhaRepositoryPort, validadorTrilhaDomain);
   }

   @Bean
   public ListarTrilhasPort listarTrilhasPort(TrilhaRepositoryPort trilhaRepositoryPort) {
      return new ListarTrilhasUseCase(trilhaRepositoryPort);
   }

   @Bean
   public ListarParticipantesPort listarParticipantesPort(ParticipanteRepositoryPort participanteRepositoryPort) {
      return new ListarParticipantesUseCase(participanteRepositoryPort);
   }

   @Bean
   public ExecutarDemonstracaoPort executarDemonstracaoPort(
           TrilhaRepositoryPort trilhaRepositoryPort,
           ValidadorTrilhaDomain validadorTrilhaDomain) {
      return new ExecutarDemonstracaoAutomaticaUseCase(trilhaRepositoryPort, validadorTrilhaDomain);
   }

}

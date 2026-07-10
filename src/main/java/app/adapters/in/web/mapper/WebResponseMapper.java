package app.adapters.in.web.mapper;

import app.adapters.in.web.dto.MentorResponse;
import app.adapters.in.web.dto.MentoradoResponse;
import app.adapters.in.web.dto.TrilhaResponse;
import app.adapters.in.web.dto.WebEnumMapper;
import app.domain.model.Mentor;
import app.domain.model.Mentorado;
import app.domain.model.TrilhaMentoria;

public final class WebResponseMapper {
   private WebResponseMapper(){

   }

   public static MentorResponse toResponse(Mentor mentor) {
      return new MentorResponse(
              mentor.getId(),
              mentor.getNome(),
              mentor.getNivelSenioridade().name(),
              WebEnumMapper.toSkillNames(mentor.getSkills()),
              mentor.getValorHora(),
              mentor.getHorasDedicadas(),
              mentor.getMaximoMentorados()
      );
   }

   public static MentoradoResponse toResponse(Mentorado mentorado){
      return new MentoradoResponse(
              mentorado.getId(),
              mentorado.getNome(),
              mentorado.getNivelSenioridade().name(),
              WebEnumMapper.toSkillNames(mentorado.getSkills()),// getSkills vindos do ParticipantePrograma = domain.model
              mentorado.getValorHora(),
              mentorado.getHorasDedicadas(),
              WebEnumMapper.toSkillNames(mentorado.getSkillsDesejadas()) //getSkillsDesejadas vindos do Mentorado
      );
   }

   public static TrilhaResponse toResponse(TrilhaMentoria trilha){
      return new TrilhaResponse(
              trilha.getId(),
              trilha.getNomeDaTrilha(),
              trilha.getCicloEmMeses(),
              trilha.getMentor().getId(),
              trilha.getMentorados().stream().map(Mentorado::getId).toList(),
              WebEnumMapper.toSkillNames(trilha.getSkillsDaTrilha()),
              trilha.calcularCustoMensalTotal(),
              trilha.calcularCustoTotalDoCiclo()
      );
   }
}

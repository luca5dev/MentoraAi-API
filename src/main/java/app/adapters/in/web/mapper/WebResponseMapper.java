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

   public static MentorResponse toResponse(Mentor mentor){
      return new MentorResponse(
              //Adicionar dados do mentor para respostas utilizando a classe abstrata Participante
              WebEnumMapper.toSkillNames(mentor.getSKills()), //getSkills vindos do Participante
      );
   }

   public static MentoradoResponse toResponse(Mentorado mentorado){
      return new MentoradoResponse(
              //Adicionar dados do mentorado para respostas utilizando a classe abstrata Participante
              WebEnumMapper.toSkillNames(mentorado.getSkills()),// getSkills vindos do Participante
              WebEnumMapper.toSkillNames(mentorado.getSkillsDesejadas()) //getSkillsDesejadas vindos do Mentorado
      );
   }

   public static TrilhaResponse toResponse(TrilhaMentoria trilha){
      return new TrilhaResponse(
              WebEnumMapper.toSkillNames(trilha.getSkills()), //getSkills() vindo do TrilhaMentoria
      )
   }
}

package app.adapters.in.web.dto;

import app.config.MensagensLogger;
import app.domain.model.Skill;
import app.domain.exception.EntradaInvalidaException;
import app.domain.model.NivelSenioridade;

import java.util.List;

public final class WebEnumMapper {

   private WebEnumMapper(){

   }

   public static NivelSenioridade toNivelSenioridade(String valor){
      try{
         return NivelSenioridade.valueOf(ValorObrigatorio(valor));
      }catch (IllegalArgumentException exception) {
         throw new EntradaInvalidaException(MensagensLogger.NIVEL_SENIORIDADE_INVALIDO + valor);
      }
   }

   public static List<Skill> toSkills(List<String> valores){
      if (valores == null){
         return List.of();
      }
      return valores.stream().map(WebEnumMapper::toSkills).toList();
   }

   public static List<String> toSkillNames(List<Skill> skills){
      return skills.stream().map(Skill::name).toList();
   }

   private static Skill toSkills(String valor){
      try{
         return Skill.valueOf(ValorObrigatorio(valor));
      }catch (IllegalArgumentException exception){
         throw new EntradaInvalidaException(MensagensLogger.SKILL_INVALIDA + valor);
      }
   }

   private static String ValorObrigatorio(String valor){
      if (valor == null || valor.isBlank()){
         throw new EntradaInvalidaException(MensagensLogger.VALOR_OBRIGATORIO_NAO_INFORMADO);
      }
      return valor.trim().toUpperCase();
   }
}

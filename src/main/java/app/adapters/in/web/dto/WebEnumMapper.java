package app.adapters.in.web.dto;

import app.domain.model.Skill;
import app.exception.EntradaInvalidaException;
import app.model.enums.NivelSenioridade;

import java.util.List;

public final class WebEnumMapper {

   private WebEnumMapper(){

   }

   public static NivelSenioridade toNivelSenioridade(String valor){
      try{
         return NivelSenioridade.valueOf(ValorObrigatorio(valor));
      }catch (IllegalArgumentException exception) {
         throw new EntradaInvalidaException("Nível de senioridade inválido!" + valor);
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
         throw new EntradaInvalidaException("Skill inválida!" + valor);
      }
   }

   private static String ValorObrigatorio(String valor){
      if (valor == null || valor.isBlank()){
         throw new EntradaInvalidaException("Valor obrigatório não informado!");
      }
      return valor.trim().toUpperCase();
   }
}

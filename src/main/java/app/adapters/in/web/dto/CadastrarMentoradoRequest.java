package app.adapters.in.web.dto;

import java.util.ArrayList;
import java.util.List;

public class CadastrarMentoradoRequest {

   private String nome;
   private String nivelSenioridade;
   private List<String> skills = new ArrayList<>();
   private Double valorHora;
   private Double horasDedicadas;
   private List<String> skillsDesejadas = new ArrayList<>();

   public String getNome() {
      return nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getNivelSenioridade() {
      return nivelSenioridade;
   }

   public void setNivelSenioridade(String nivelSenioridade) {
      this.nivelSenioridade = nivelSenioridade;
   }

   public List<String> getSkills() {
      return skills;
   }

   public void setSkills(List<String> skills) {
      this.skills = skills;
   }

   public Double getValorHora() {
      return valorHora;
   }

   public void setValorHora(Double valorHora) {
      this.valorHora = valorHora;
   }

   public Double getHorasDedicadas() {
      return horasDedicadas;
   }

   public void setHorasDedicadas(Double horasDedicadas) {
      this.horasDedicadas = horasDedicadas;
   }

   public List<String> getSkillsDesejadas() {
      return skillsDesejadas;
   }

   public void setSkillsDesejadas(List<String> skillsDesejadas) {
      this.skillsDesejadas = skillsDesejadas;
   }
}

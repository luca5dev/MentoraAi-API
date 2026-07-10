package app.adapters.in.web.dto;

import java.util.ArrayList;
import java.util.List;

public class CadastrarMentorRequest {

   private String nome;
   private String nivelSenioridade;
   private List<String> skills = new ArrayList<>();
   private Double valorHora;
   private Double horasDedicadas;
   private Integer maximoMentorados;

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

   public Integer getMaximoMentorados() {
      return maximoMentorados;
   }

   public void setMaximoMentorados(Integer maximoMentorados) {
      this.maximoMentorados = maximoMentorados;
   }
}

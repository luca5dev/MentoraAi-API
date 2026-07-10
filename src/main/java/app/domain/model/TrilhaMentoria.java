package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class TrilhaMentoria {

   private Long id;
   private String nomeDaTrilha;
   private Integer cicloEmMeses; //Duração do ciclo de mentoria, mencionado nas Premissas da US.
   private Mentor mentor;
   private List<Mentorado> mentorados = new ArrayList<>();
   private List<Skill> skillsDaTrilha = new ArrayList<>();

   public TrilhaMentoria() {}

   public TrilhaMentoria(String nomeDaTrilha, Integer cicloEmMeses, Mentor mentor,
                         List<Mentorado> mentorados, List<Skill> skillsDaTrilha) {
      this.nomeDaTrilha = nomeDaTrilha;
      this.cicloEmMeses = cicloEmMeses;
      this.mentor = mentor;
      this.mentorados = mentorados;
      this.skillsDaTrilha = skillsDaTrilha;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getNomeDaTrilha() {
      return nomeDaTrilha;
   }

   public void setNomeDaTrilha(String nomeDaTrilha) {
      this.nomeDaTrilha = nomeDaTrilha;
   }

   public Integer getCicloEmMeses() {
      return cicloEmMeses;
   }

   public void setCicloEmMeses(Integer cicloEmMeses) {
      this.cicloEmMeses = cicloEmMeses;
   }

   public Mentor getMentor() {
      return mentor;
   }

   public void setMentor(Mentor mentor) {
      this.mentor = mentor;
   }

   public List<Mentorado> getMentorados() {
      return mentorados;
   }

   public void setMentorados(List<Mentorado> mentorados) {
      this.mentorados = mentorados;
   }

   public List<Skill> getSkillsDaTrilha() {
      return skillsDaTrilha;
   }

   public void setSkillsDaTrilha(List<Skill> skillsDaTrilha) {
      this.skillsDaTrilha = skillsDaTrilha;
   }

   public void adicionarSkills(Skill skill){
      this.skillsDaTrilha.add(skill);
   }

   public void adicionarMentorado(Mentorado mentorado) {
      this.mentorados.add(mentorado);
   }

   public Double calcularCustoMensalTotal() {
      double custoMentor = mentor.calcularCustoOportunidadeMensal();
      double custoMentorados = mentorados.stream()
              .mapToDouble(Mentorado::calcularCustoOportunidadeMensal)
              .sum();
      return custoMentor + custoMentorados;
   }

   public Double calcularCustoTotalDoCiclo() {
      return calcularCustoMensalTotal() * cicloEmMeses;
   }

   @Override
   public String toString() {
      return "TrilhaMentoria{" +
              "id=" + id +
              ", nomeDaTrilha='" + nomeDaTrilha + '\'' +
              ", cicloEmMeses=" + cicloEmMeses +
              ", mentor=" + mentor +
              ", skillsDaTrilha=" + skillsDaTrilha +
              ", mentorados=" + mentorados +
              '}';
   }
}
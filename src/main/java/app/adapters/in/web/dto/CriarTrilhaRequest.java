package app.adapters.in.web.dto;

import java.util.ArrayList;
import java.util.List;

public class CriarTrilhaRequest {

   private String nome;
   private Integer cicloEmMeses;
   private Long idMentor;
   private List<Long> idsMentorados = new ArrayList<>();

   public String getNome() {
      return nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public Integer getCicloEmMeses() {
      return cicloEmMeses;
   }

   public void setCicloEmMeses(Integer cicloEmMeses) {
      this.cicloEmMeses = cicloEmMeses;
   }

   public Long getIdMentor() {
      return idMentor;
   }

   public void setIdMentor(Long idMentor) {
      this.idMentor = idMentor;
   }

   public List<Long> getIdsMentorados() {
      return idsMentorados;
   }

   public void setIdsMentorados(List<Long> idsMentorados) {
      this.idsMentorados = idsMentorados;
   }
}

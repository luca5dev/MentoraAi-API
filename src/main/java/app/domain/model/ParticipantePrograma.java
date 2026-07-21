package app.domain.model;

import app.domain.exception.CampoVazioException;
import app.domain.exception.SkillDuplicadaException;

import java.util.ArrayList;
import java.util.List;

public abstract class ParticipantePrograma {

   private Long id;
   private String nome;
   private NivelSenioridade nivelSenioridade;
   private List<Skill> skills = new ArrayList<>();
   private Double valorHora;
   private Double horasDedicadas;

   public ParticipantePrograma() {
   }

   public ParticipantePrograma(String nome, NivelSenioridade nivelSenioridade, List<Skill> skills, Double valorHora, Double horasDedicadas) {
      this.nome = nome;
      this.nivelSenioridade = nivelSenioridade;
      this.skills = new ArrayList<>();
      if (skills != null) {
         for (Skill skill : skills) {
            adicionarSkill(skill);
         }
      }
      setValorHora(valorHora);
      setHorasDedicadas(horasDedicadas);
   }

   public abstract Double calcularCustoOportunidadeMensal();

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getNome() {
      return nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public NivelSenioridade getNivelSenioridade() {
      return nivelSenioridade;
   }

   public void setNivelSenioridade(NivelSenioridade nivelSenioridade) {
      this.nivelSenioridade = nivelSenioridade;
   }

   public List<Skill> getSkills() {
      return skills;
   }

   public void adicionarSkill(Skill skill) {
      if (skill == null) {
         throw new CampoVazioException("Skill não pode ser nula.");
      }

      if (this.skills.contains(skill)) {
         throw new SkillDuplicadaException("Skill já adicionada.");
      }
      this.skills.add(skill);
   }

   public Double getValorHora() {
      return valorHora;
   }

   public void setValorHora(Double valorHora) {
      if (valorHora == null || valorHora <= 0) {
         throw new CampoVazioException("Valor hora deve ser positivo.");
      }
      this.valorHora = valorHora;
   }

   public Double getHorasDedicadas() {
      return horasDedicadas;
   }

   public void setHorasDedicadas(Double horasDedicadas) {
      if (horasDedicadas == null || horasDedicadas <= 0) {
         throw new CampoVazioException("Horas dedicadas deve ser positivo.");
      }
      this.horasDedicadas = horasDedicadas;
   }

   @Override
   public String toString() {
      return "ParticipantePrograma{" +
              "id=" + id +
              ", nome='" + nome + '\'' +
              ", nivelSenioridade=" + nivelSenioridade +
              ", skills=" + skills +
              ", valorHora=" + valorHora +
              '}';
   }
}

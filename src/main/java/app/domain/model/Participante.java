package app.domain.model;



import java.util.ArrayList;
import java.util.List;

public abstract class Participante {

   private Long id;
   private String nome;
   private NivelSenioridade nivelSenioridade;
   private List<Skill> skills = new ArrayList<>();
   private Double valorHora;
   private Double horasDedicadas;


   // desenvolver metodos Participante  - calcularCustoOportunidadeMensal e os gets e sets
}

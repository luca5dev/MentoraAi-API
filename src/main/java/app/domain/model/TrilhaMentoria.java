package app.domain.model;

import java.util.List;

public class TrilhaMentoria {
   private Long id;
   private String nome;
   private Integer cicloEmMeses;
   private Mentor mentor;
   private List<Mentorado> mentorados;
   private List<Skill> skills;


}
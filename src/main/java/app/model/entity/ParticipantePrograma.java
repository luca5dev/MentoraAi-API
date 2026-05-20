package app.model.entity;

import app.model.NivelSenioridade;
import app.model.Skill;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) //Diz ao JPA que as entidades usam heranças em tabelas separadas
public abstract class ParticipantePrograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private NivelSenioridade nivelSenioridade;

    @ElementCollection(targetClass = Skill.class) //Essa anotação permite mapear a lista de enums sem precisar criar uma entidade Skill, o Hibernate cria uma tabela auxiliar automaticamente
    @Enumerated(EnumType.STRING)
    private List<Skill> skills;

    private Double valorHora; //Valor hora é o custo por hora do participante, usado para calcular o custo total do programa

    public ParticipantePrograma() {}

    public ParticipantePrograma(String nome, NivelSenioridade nivelSenioridade, List<Skill> skills, Double valorHora) {
        this.nome = nome;
        this.nivelSenioridade = nivelSenioridade;
        this.skills = skills;
        this.valorHora = valorHora;
    }

    public abstract Double calcularCustoOportunidadeMensal(); //AC1: Calcula o valor financeiro das horas investidas no programa com base no nível do funcionário.

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

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Double getValorHora() {
        return valorHora;
    }

    public void setValorHora(Double valorHora) {
        this.valorHora = valorHora;
    }

}

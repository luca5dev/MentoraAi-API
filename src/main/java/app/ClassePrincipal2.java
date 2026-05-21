package app;

import app.model.entity.Mentor;
import app.model.entity.ParticipantePrograma;
import app.model.enums.NivelSenioridade;
import app.model.enums.Skill;
import app.service.useCase.AdicionarSkillUseCase;
import app.service.useCase.BuscaSenioridadeUseCase;
import app.service.useCase.BuscaSkillUseCase;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class ClassePrincipal2 {
    public static void main(String[] args) {
        Persistence jpa = new Persistence();
        ParticipantePrograma p;
        List<Skill> skillsParticipante = new ArrayList<>();
        AdicionarSkillUseCase adicionarSkillUseCase = new AdicionarSkillUseCase();
        BuscaSenioridadeUseCase buscaSenioridadeUseCase = new BuscaSenioridadeUseCase();
        BuscaSkillUseCase buscaSkillUseCase = new BuscaSkillUseCase();
        System.out.println("Hello world");
        System.out.println("Tentativa de conexão...");

       NivelSenioridade nivel = buscaSenioridadeUseCase.buscaSenioridade(3);
       Skill skill =  buscaSkillUseCase.buscaSkill(1);
       Skill skill2 = buscaSkillUseCase.buscaSkill(2);


       p = new Mentor();
       adicionarSkillUseCase.AdicionarSkill(p, 1);
       adicionarSkillUseCase.AdicionarSkill(p, 2);
       adicionarSkillUseCase.AdicionarSkill(p, 4);
       System.out.println(p.getSkills());



    }
}

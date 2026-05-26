package app.model.factory;

import app.dao.ParticipanteDAO;
import app.model.dto.TrilhaMentoriaDTO;
import app.model.dto.UsuarioCadastroDTO;
import app.model.entity.Mentor;
import app.model.entity.Mentorado;
import app.model.entity.ParticipantePrograma;
import app.model.entity.TrilhaMentoria;
import app.model.enums.Skill;
import app.view.CadastroParticipante;
import app.view.CadastroTrilha;

import java.util.List;
import java.util.stream.Collectors;

public class EntityFactory {
    public static void cadastrarParticipante(ParticipanteDAO jpa) {
        boolean cadastro = true;
        AdicionarSkills addSkills = new AdicionarSkills();
        UsuarioCadastroDTO dto = CadastroParticipante.cadastrarParticipante();
        ParticipantePrograma p = null;

        switch (dto.getOpcao()) {
            case 1:
                addSkills.skillBase(dto);
                p = new Mentor(dto.getNome(), dto.getNivelSenioridade(), dto.getSkills(), dto.getValorHora(), 0.0);
                jpa.persist(p);
                System.out.println("Mentor cadastrado com sucesso!");
                break;

            case 2:
                addSkills.skillBase(dto);
                addSkills.skillsParaMentorados(dto);
                p = new Mentorado(dto.getNome(), dto.getNivelSenioridade(), dto.getSkills(), dto.getValorHora(), 0.0, dto.getSkillsDesejadas());
                jpa.persist(p);
                System.out.println("Mentorado cadastrado com sucesso!");
                break;

            default:
                System.out.println("Escolha uma opção válida");
        }

        if (p != null) {
            System.out.println(p);
        } else {
            System.out.println("Nenhum participante cadastrado");
        }
    }

    public static void cadastrarTrilha() {
        TrilhaMentoriaDTO dto = CadastroTrilha.cadastrarTrilha();

        List<Skill> skills = dto.getSkills().stream().collect(Collectors.toList());
        List<Mentorado> mentorados = dto.getMentorados().stream().collect(Collectors.toList());

        //buscarpeloId e linkar mentor com o id selecionado no cadastrarTrilha
        Mentor mentor = new Mentor();
        mentor.setId((long) dto.getIdMentor());
        dto.setMentor(mentor);

        //Se Mentor.getTrilhas > quantidade -> lançar

        //Adicionar os dados, mentor linkado e listas de SKill e mentorados
        TrilhaMentoria novaTrilha = new TrilhaMentoria("Teste", 2, dto.getMentor(), dto.getMentorados(), dto.getSkills());

        System.out.println("Criado com sucesso");

        //Impressão de teste :p
        System.out.println(novaTrilha);
    }

}
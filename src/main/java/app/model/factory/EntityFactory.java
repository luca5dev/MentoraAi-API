package app.model.factory;

import app.model.dto.TrilhaMentoriaDTO;
import app.model.dto.UsuarioCadastroDTO;
import app.model.entity.Mentor;
import app.model.entity.Mentorado;
import app.model.entity.ParticipantePrograma;
import app.model.entity.TrilhaMentoria;
import app.model.enums.Skill;
import app.view.CadastroParticipante;
import app.view.CadastroTrilha;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntityFactory {
    public static void cadastrarParticipante(){
        UsuarioCadastroDTO dto = CadastroParticipante.cadastrarParticipante();
        List<Skill> skills = dto.getSkills().stream().collect(Collectors.toList());

        ParticipantePrograma p = null;

        switch (dto.getOpcao()){
            case 1: p = new Mentor(
                    dto.getNome(), dto.getNivelSenioridade(), skills,0.0,0,0 );
            System.out.println("Mentor cadastrado");
            break;

            case 2: p = new Mentorado(dto.getNome(), dto.getNivelSenioridade(), skills,0.0,0,skills);
            break;

            default:
                System.out.println("Escolha uma opção válida");
        }

        if (p!=null){
            System.out.println(p);
        } else {
            System.out.println("Vazio");
        }
    }

    public static void cadastrarTrilha(){
        TrilhaMentoriaDTO dto = CadastroTrilha.cadastrarTrilha();

        List<Skill> skills = dto.getSkills().stream().collect(Collectors.toList());
        List<Mentorado> mentorados = dto.getMentorados().stream().collect(Collectors.toList());

        //buscarpeloId e linkar mentor com o id selecionado no cadastrarTrilha
        Mentor mentor = new Mentor();
        mentor.setId((long) dto.getIdMentor());
        dto.setMentor(mentor);

        //Se Mentor.getTrilhas > quantidade -> lançar

        //Adicionar os dados, mentor linkado e listas de SKill e mentorados
        TrilhaMentoria novaTrilha = new TrilhaMentoria("Teste", 2, dto.getMentor(), skills, mentorados);

        System.out.println("Criado com sucesso");

        //Impressão de teste :p
        System.out.println(novaTrilha);
    }
}
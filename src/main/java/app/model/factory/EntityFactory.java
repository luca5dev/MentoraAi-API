package app.model.factory;

import app.model.dto.UsuarioCadastroDTO;
import app.model.entity.Mentor;
import app.model.entity.Mentorado;
import app.model.entity.ParticipantePrograma;
import app.model.enums.NivelSenioridade;
import app.model.enums.Skill;
import app.view.CadastroParticipante;

import java.util.List;
import java.util.stream.Collectors;

public class EntityFactory {
    public static void novoCadastro(){
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
}
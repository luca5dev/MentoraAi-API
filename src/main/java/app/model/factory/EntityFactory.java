package app.model.factory;

import app.model.dto.TrilhaMentoriaDTO;
import app.model.dto.UsuarioCadastroDTO;
import app.model.entity.Mentor;
import app.model.entity.Mentorado;
import app.model.entity.ParticipantePrograma;
import app.model.entity.TrilhaMentoria;

import java.util.List;

public class EntityFactory {

    public EntityFactory() {}

    public static Mentor criarMentor(UsuarioCadastroDTO dto) {
        return new Mentor(
                dto.getNome(),
                dto.getNivelSenioridade(),
                dto.getSkills(),
                dto.getValorHora()
        );
    }
    public static Mentorado criarMentorado(UsuarioCadastroDTO dto) {
        return new Mentorado(
                dto.getNome(),
                dto.getNivelSenioridade(),
                dto.getSkills(),
                dto.getValorHora(),
                0.0,
                dto.getSkillsDesejadas()
        );
    }
    public static ParticipantePrograma criarParticipante(UsuarioCadastroDTO dto) {
        switch (dto.getOpcao()) {
            case 1: return criarMentor(dto);
            case 2: return criarMentorado(dto);
            default: throw new IllegalArgumentException("Opção inválida");
        }
    }

    public static TrilhaMentoria criarTrilha(TrilhaMentoriaDTO dto, Mentor mentor, List<Mentorado> mentorados) {
        return new TrilhaMentoria(
                dto.getNome(),
                dto.getDuracaoHoras(),
                mentor,
                mentorados,
                dto.getSkills() //Tirei da assinatura do metodo porque esses dados já estão no dto e ficaria redundante.
        );
    }
}

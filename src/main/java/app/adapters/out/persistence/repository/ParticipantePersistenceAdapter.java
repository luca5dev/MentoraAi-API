package app.adapters.out.persistence.repository;

public class ParticipantePersistenceAdapter {

   /*essa classe vai implementar o ParticipanteRepositoryPort, aí vai ter aqui os metodos Mentor com o salvarMentor
   Mentorado com o salvarMentorado, Mentor com buscarMentorPorId <- aqui da pra usar a excessão de participante não encontrado
   e a lista de mentorado pra buscar mentorados por id. depois fazer as associações em um metodo toDomain e toEntity. vou colocar um exemplo em seguida

   private MentorJpaEntity toEntity(Mentor mentor) {
        MentorJpaEntity entity = new MentorJpaEntity();
        entity.setId(mentor.getId());
        entity.setNome(mentor.getNome());
        entity.setNivelSenioridade(mentor.getNivelSenioridade());
        entity.setSkills(mentor.getSkills());
        entity.setValorHora(mentor.getValorHora());
        entity.setHorasDedicadas(mentor.getHorasDedicadas());
        entity.setMaximoMentorados(mentor.getMaximoMentorados());
        return entity;
    }

    */

}

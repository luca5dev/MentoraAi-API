package app.adapters.out.persistence.repository;

import app.adapters.out.persistence.entity.MentorJpaEntity;
import app.adapters.out.persistence.entity.MentoradoJpaEntity;
import app.adapters.out.persistence.entity.ParticipanteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ParticipanteJpaRepository extends JpaRepository<ParticipanteJpaEntity, Long> {

    @Query("select p from ParticipanteJpaEntity p where p.ativo = true or p.ativo is null")
    List<ParticipanteJpaEntity> findAllAtivos();

    @Query("select p from ParticipanteJpaEntity p where p.id = :id and (p.ativo = true or p.ativo is null)")
    Optional<ParticipanteJpaEntity> findAtivoById(@Param("id") Long id);

    @Query("select m from MentorJpaEntity m where m.id = :id and (m.ativo = true or m.ativo is null)")
    Optional<MentorJpaEntity> findMentorById(@Param("id") Long id);

    @Query("select m from MentoradoJpaEntity m where m.id in :ids and (m.ativo = true or m.ativo is null)")
    List<MentoradoJpaEntity> findMentoradosByIds(@Param("ids") List<Long> ids);
}

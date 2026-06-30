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

    @Query("select m from MentorJpaEntity m where m.id = :id")
    Optional<MentorJpaEntity> findMentorById(@Param("id") Long id);

    @Query("select m from MentoradoJpaEntity m where m.id in :ids")
    List<MentoradoJpaEntity> findMentoradosByIds(@Param("ids") List<Long> ids);
}

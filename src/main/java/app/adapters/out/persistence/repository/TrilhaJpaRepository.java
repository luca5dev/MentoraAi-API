package app.adapters.out.persistence.repository;

import app.adapters.out.persistence.entity.TrilhaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TrilhaJpaRepository extends JpaRepository<TrilhaJpaEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE participanteprograma
            SET ativo = false
            WHERE nome IN ('Bruno', 'Carla', 'Daniel', 'Erica', 'Eva')
            AND id IN (
                SELECT id FROM mentorado
                WHERE trilha_id IS NULL
                OR trilha_id NOT IN (SELECT id FROM trilhamentoria)
            )
            """,
            nativeQuery = true)
    void limparMentoradosOrfaos();
}



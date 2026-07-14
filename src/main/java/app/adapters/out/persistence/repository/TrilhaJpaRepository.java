package app.adapters.out.persistence.repository;

import app.adapters.out.persistence.entity.TrilhaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrilhaJpaRepository extends JpaRepository<TrilhaJpaEntity, Long> {
}



package dk.ek.kinoxp.catalog.repository;

import dk.ek.kinoxp.catalog.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}

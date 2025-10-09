package dk.ek.kinoxp.catalog.repository;

import dk.ek.kinoxp.catalog.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}

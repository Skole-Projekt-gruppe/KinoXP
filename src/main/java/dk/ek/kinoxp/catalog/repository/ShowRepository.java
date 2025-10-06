package dk.ek.kinoxp.catalog.repository;

import dk.ek.kinoxp.catalog.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository <Show, Long>
{
}

package dk.ek.kinoxp.catalog.repository;

import dk.ek.kinoxp.catalog.model.Teather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeatherRepository extends JpaRepository<Teather, Long>
{
}

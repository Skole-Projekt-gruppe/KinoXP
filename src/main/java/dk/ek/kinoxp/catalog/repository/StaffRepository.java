package dk.ek.kinoxp.catalog.repository;

import dk.ek.kinoxp.catalog.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
}

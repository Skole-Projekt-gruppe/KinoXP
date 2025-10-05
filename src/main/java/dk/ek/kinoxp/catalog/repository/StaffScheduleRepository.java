package dk.ek.kinoxp.catalog.repository;

import dk.ek.kinoxp.catalog.model.StaffSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffScheduleRepository extends JpaRepository<StaffSchedule, Integer> {
}

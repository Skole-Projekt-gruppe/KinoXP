package dk.ek.kinoxp.catalog.repository;

import dk.ek.kinoxp.catalog.model.StaffSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface StaffScheduleRepository extends JpaRepository<StaffSchedule, Integer>
{
    @Query("SELECT s FROM StaffSchedule s WHERE s.staff.staff_id = :staffId")
    List<StaffSchedule> findByStaffId(@Param("staffId") Integer staffId);



}

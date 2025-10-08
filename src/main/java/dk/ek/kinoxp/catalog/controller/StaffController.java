package dk.ek.kinoxp.catalog.controller;

import dk.ek.kinoxp.catalog.model.Staff;
import dk.ek.kinoxp.catalog.model.StaffSchedule;
import dk.ek.kinoxp.catalog.repository.StaffRepository;
import dk.ek.kinoxp.catalog.repository.StaffScheduleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
@CrossOrigin
public class StaffController
{

    private final StaffRepository repo;
    private final StaffScheduleRepository staffScheduleRepository;

    public StaffController(StaffRepository repo, StaffScheduleRepository staffScheduleRepository) {
        this.repo = repo;
        this.staffScheduleRepository = staffScheduleRepository;
    }

    @GetMapping
    public List<Staff> getAllStaff() {
        return repo.findAll();
    }

    @GetMapping("/{id}/schedule")
    public List<StaffSchedule> getStaffSchedule(@PathVariable Integer id) {
        return staffScheduleRepository.findByStaffId(id);
    }


}

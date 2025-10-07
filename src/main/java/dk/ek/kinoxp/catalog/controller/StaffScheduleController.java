package dk.ek.kinoxp.catalog.controller;

import dk.ek.kinoxp.catalog.model.StaffSchedule;
import dk.ek.kinoxp.catalog.repository.StaffScheduleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@CrossOrigin
public class StaffScheduleController {

    private final StaffScheduleRepository repo;

    public StaffScheduleController(StaffScheduleRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<StaffSchedule> getAllSchedules() {
        return repo.findAll();
    }
}

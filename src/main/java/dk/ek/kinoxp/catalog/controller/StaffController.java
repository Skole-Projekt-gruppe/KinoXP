package dk.ek.kinoxp.catalog.controller;

import dk.ek.kinoxp.catalog.model.Staff;
import dk.ek.kinoxp.catalog.repository.StaffRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
@CrossOrigin
public class StaffController
{

    private final StaffRepository repo;

    public StaffController(StaffRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Staff> getAllStaff() {
        return repo.findAll();
    }

}

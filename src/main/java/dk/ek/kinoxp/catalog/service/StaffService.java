package dk.ek.kinoxp.catalog.service;

import dk.ek.kinoxp.catalog.dto.StaffMapper;
import dk.ek.kinoxp.catalog.dto.StaffDto;
import dk.ek.kinoxp.catalog.exception.NotFoundException;
import dk.ek.kinoxp.catalog.model.Staff;
import dk.ek.kinoxp.catalog.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class StaffService
{
    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<StaffDto> getAllStaff() {
        List<Staff> staff = staffRepository.findAll();

        if (staff.isEmpty()) {
            throw new NotFoundException("No staff found");
        }

        List<StaffDto> staffDtos = new ArrayList<>();
        for(var member : staff) {
            staffDtos.add(StaffMapper.toDto(member));
        }
        return staffDtos;
    }


}

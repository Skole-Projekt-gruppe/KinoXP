package dk.ek.kinoxp.catalog.service;

import dk.ek.kinoxp.catalog.dto.StaffScheduleMapper;
import dk.ek.kinoxp.catalog.dto.StaffScheduleDto;
import dk.ek.kinoxp.catalog.exception.NotFoundException;
import dk.ek.kinoxp.catalog.model.StaffSchedule;
import dk.ek.kinoxp.catalog.repository.StaffScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class StaffScheduleService
{
    private final StaffScheduleRepository staffScheduleRepository;

    public StaffScheduleService(StaffScheduleRepository staffScheduleRepository) {
        this.staffScheduleRepository = staffScheduleRepository;
    }

    public List<StaffScheduleDto> getAllStaffSchedules() {
        List<StaffSchedule> staffSchedules = staffScheduleRepository.findAll();

        if (staffSchedules.isEmpty()) {
            throw new NotFoundException("No staff schedules found");
        }

        List<StaffScheduleDto> staffScheduleDtos = new ArrayList<>();
        for(var schedule : staffSchedules) {
            staffScheduleDtos.add(StaffScheduleMapper.toDto(schedule));
        }
        return staffScheduleDtos;
    }

}

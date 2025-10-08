package dk.ek.kinoxp.catalog.dto;

import dk.ek.kinoxp.catalog.model.StaffSchedule;

public class StaffScheduleMapper
{

    public static StaffScheduleDto toDto(StaffSchedule staffSchedule)
    {
        return new StaffScheduleDto(
                staffSchedule.getStaffSchedule_id(),
                staffSchedule.getStaff(),
                staffSchedule.getWork_date(),
                staffSchedule.getShift_start(),
                staffSchedule.getShift_end()
        );
    }

    public static StaffSchedule toEntity(StaffScheduleDto staffScheduleDto)
    {
        StaffSchedule staffSchedule = new StaffSchedule();

        staffSchedule.setStaffSchedule_id(staffScheduleDto.staffSchedule_id());
        staffSchedule.setStaff(staffScheduleDto.staff_id());
        staffSchedule.setWork_date(staffScheduleDto.work_date());
        staffSchedule.setShift_start(staffScheduleDto.shift_start());
        staffSchedule.setShift_end(staffScheduleDto.shift_end());

        return staffSchedule;
    }
}

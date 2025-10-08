package dk.ek.kinoxp.catalog.dto;

import dk.ek.kinoxp.catalog.model.Staff;

public class StaffMapper
{

    public static StaffDto toDto(Staff staff)
    {
        return new StaffDto(
                staff.getStaff_id(),
                staff.getName(),
                staff.getRole()

        );
    }

    public static Staff toEntity(StaffDto staffDto)
    {
        Staff staff = new Staff();

        staff.setStaff_id(staffDto.staff_id());
        staff.setName(staffDto.name());
        staff.setRole(staffDto.role());
        return staff;
    }
}

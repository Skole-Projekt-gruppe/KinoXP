package dk.ek.kinoxp.catalog.dto;

import dk.ek.kinoxp.catalog.model.Staff;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Time;
import java.util.Date;


public record StaffScheduleDto(
        Integer staffSchedule_id,

        @NotBlank(message = "Must have staff id")
        Staff staff_id,

        @NotBlank(message = "Schedule must have a date")
        Date work_date,

        @NotBlank(message = "Schedule must have a Shift start")
        Time shift_start,

        @NotBlank(message = "Schedule must have a Shift end")
        Time shift_end
) {
}

package dk.ek.kinoxp.catalog.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ShowDto(
        Long show_id,

        @NotNull(message = "Show must have a planned date")
        LocalDate planned_at,

        @NotNull(message = "Show must have a start time")
        LocalTime start_time,

        @NotNull(message = "Show must have a end time")
        LocalTime end_time,

        @NotNull(message = "Show must have a movie")
        MovieDto movie,

        @NotNull(message = "Show must have a teather")
        TeatherDto teather
)
{
}

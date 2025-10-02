package dk.ek.kinoxp.catalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public record MovieDto(
        Long movie_id,

        @NotBlank(message = "Movie must have a title")
        String title,

        String poster,

        @NotNull(message = "Movie must have a duration")
        int duration_min,

        @NotNull(message = "Movie must have a age limit")
        int age_limit,

        @NotNull(message = "Movie must have a start date")
        LocalDate start_date,

        @NotNull(message ="Movie must have a end date")
        LocalDate end_date
) {
}
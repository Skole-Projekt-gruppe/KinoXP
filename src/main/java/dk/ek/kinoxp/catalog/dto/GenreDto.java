package dk.ek.kinoxp.catalog.dto;

import jakarta.validation.constraints.NotBlank;

public record GenreDto(
        Long genre_id,

        @NotBlank(message = "Genre must have a name")
        String name
) {
}

package dk.ek.kinoxp.catalog.dto;

import jakarta.validation.constraints.NotNull;

public record TeatherDto(
        Long teather_id,
        @NotNull(message = "Show must have a name")
        String name
)
{
}




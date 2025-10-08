package dk.ek.kinoxp.catalog.dto;

import jakarta.validation.constraints.NotBlank;

public record ActorDto(
        Long actor_id,

        @NotBlank(message = "Actor must have a name")
        String name
) {
}

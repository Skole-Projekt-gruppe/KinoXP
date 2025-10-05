package dk.ek.kinoxp.catalog.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record StaffDto(
        Integer staff_id,

        @NotBlank(message = "Staff must have a name")
        String name,

        @NotBlank(message = "Staff must have a role")
        String role
) {}

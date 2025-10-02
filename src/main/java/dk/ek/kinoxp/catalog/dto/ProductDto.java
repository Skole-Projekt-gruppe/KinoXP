package dk.ek.kinoxp.catalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ProductDto(
        int product_id,

        @NotBlank(message = "Product must have a name")
        String name,


        @NotNull(message = "Product must have a price")
        int price
        ) {
}
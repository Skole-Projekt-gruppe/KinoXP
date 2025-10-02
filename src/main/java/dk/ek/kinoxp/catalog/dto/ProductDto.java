package dk.ek.kinoxp.catalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record ProductDto(
        Integer product_id,

        @NotBlank(message = "Product must have a name")
        String name,


        @NotNull(message = "Product must have a price")
        int price
        ) {
}
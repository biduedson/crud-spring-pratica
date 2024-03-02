package com.example.crud.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

public record RequestProductDTO(

        String id,
        @NotBlank
        String name,
        @Null
        Integer price_in_cents ) {
}

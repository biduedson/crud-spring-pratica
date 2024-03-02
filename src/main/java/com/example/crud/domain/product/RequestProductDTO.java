package com.example.crud.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record RequestProductDTO(

        String id,
        @NotBlank
        String name,
        @NotNull
        Integer price_in_cents ) {
}

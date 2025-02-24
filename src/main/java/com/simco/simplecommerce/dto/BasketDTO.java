package com.simco.simplecommerce.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BasketDTO(
        @NotNull(message = "Basket ID cannot be null")
        int id,

        @NotNull(message = "User cannot be null")
        @Size(min = 1, max = 20, message = "Product id must be between 1 and 20 characters")
        String productId,

        ProductDTO product
) {}

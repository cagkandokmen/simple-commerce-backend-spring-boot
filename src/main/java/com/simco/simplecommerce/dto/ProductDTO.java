package com.simco.simplecommerce.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
public record ProductDTO(
        @NotNull(message = "Product ID cannot be null")
        @Size(min = 1, max = 20, message = "Product id must be between 1 and 20 characters")
        String id,

        @NotNull(message = "Product name cannot be null")
        @Size(min = 1, max = 100, message = "Product name must be between 1 and 100 characters")
        String name,

        @NotNull(message = "Product type cannot be null")
        @Size(min = 1, max = 100, message = "Product type must be between 1 and 100 characters")
        String type,

        @NotNull(message = "Product image url cannot be null")
        @Size(min = 1, max = 100, message = "Product image url must be between 1 and 100 characters")
        String imagePath
) {}

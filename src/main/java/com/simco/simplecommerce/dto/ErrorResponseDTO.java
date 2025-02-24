package com.simco.simplecommerce.dto;

public record ErrorResponseDTO(
        int statusCode,
        String message
) {}
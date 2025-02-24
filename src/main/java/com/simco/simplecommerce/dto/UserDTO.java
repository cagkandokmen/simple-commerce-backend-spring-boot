package com.simco.simplecommerce.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserDTO(

        @NotNull(message = "Username cannot be null")
        @Size(min = 1, max = 20, message = "Username must be between 1 and 20 characters")
        String username,

        @NotNull(message = "Password cannot be null")
        @Size(min = 1, max = 20, message = "Password must be between 1 and 20 characters")
        String password,


        @NotNull(message = "Role cannot be null")
        @Size(min = 1, max = 20, message = "Role must be between 1 and 20 characters")
        String role
) {}


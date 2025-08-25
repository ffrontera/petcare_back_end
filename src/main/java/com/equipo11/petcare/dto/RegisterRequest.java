package com.equipo11.petcare.dto;

import com.equipo11.petcare.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RegisterRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        LocalDate birthdate,
        @NotBlank @Email String email,
        @NotBlank
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^A-Za-z0-9]).+$",
                message = "Debe contener al menos una mayúscula, una minúscula, un número y un carácter especial"
        ) String password,
        @NotBlank String address,
        @NotBlank String city,
        @NotBlank String region,
        @NotBlank String country,
        @NotBlank String typeDocument,
        @NotBlank int numberPhone,
        @NotBlank Role role) {
}

package com.equipo11.petcare.dto;

import com.equipo11.petcare.domain.user.entity.enums.RoleName;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Set;

public record RegisterRequest(
        @NotBlank(message = "El nombre no puede estar vacío")
        String firstName,

        @NotBlank(message = "El apellido no puede estar vacío")
        String lastName,

        @NotNull(message = "La fecha de nacimiento es obligatoria")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate birthdate,

        @NotBlank(message = "El correo no puede estar vacío")
        @Email(message = "El correo debe tener un formato válido")
        String email,

        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(min = 8, message = "La contraseña debe tener al menos {min} caracteres")
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^A-Za-z0-9]).+$",
                message = "La contraseña debe contener mayúscula, minúscula, número y carácter especial"
        )
        String password,

        @NotNull(message = "La dirección es obligatoria")
        @Valid
        AddressDTO address,

        @NotNull(message = "El tipo de documento es obligatorio")
        String typeDocument,

        @Positive(message = "El número de teléfono debe ser positivo")
        @Pattern(
                regexp = "\\+?\\d{6,15}",
                message = "El teléfono debe tener entre 6 y 15 dígitos, opcional '+' inicial"
        )
        String numberPhone,

        @NotNull(message = "El rol es obligatorio")
        String role
) {
}

package com.equipo11.petcare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressDTO(

        @NotBlank(message = "El nombre de la calle no puede estar vacío")
        @Size(max = 200, message = "El nombre de la calle no puede superar {max} caracteres")
        String streetName,

        @NotBlank(message = "El número de la calle no puede estar vacío")
        @Pattern(
                regexp = "\\d+[A-Za-z]?",
                message = "El número de calle debe iniciar con dígitos y opcional letra (ej. 123, 123A)"
        )
        String streetNumber,

        @Size(max = 50, message = "La unidad no puede superar {max} caracteres")
        String unit,

        @Pattern(
                regexp = "\\d{4,10}",
                message = "El código postal debe tener entre 4 y 10 dígitos"
        )
        String postalCode,

        @NotBlank(message = "La ciudad no puede estar vacía")
        @Size(max = 100, message = "La ciudad no puede superar {max} caracteres")
        String city,

        @NotBlank(message = "La región no puede estar vacía")
        @Size(max = 100, message = "La región no puede superar {max} caracteres")
        String region,

        @NotBlank(message = "El código de país no puede estar vacío")
        @Pattern(
                regexp = "^[A-Z]{2}$",
                message = "El país debe usarse en formato ISO-3166 alpha-2 (ej. AR, US, FR)"
        )
        String countryCode

) { }


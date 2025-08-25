package com.equipo11.petcare.dto;

public record ParsedAddress(
        String streetName,
        String streetNumber,
        String unit,
        String postalCode,
        String city,
        String region,
        String countryCode
) { }


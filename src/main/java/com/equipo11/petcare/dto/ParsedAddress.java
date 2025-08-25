package com.equipo11.petcare.dto;

import lombok.Getter;


public record ParsedAddress(
        String streetName,
        String streetNumber,
        String unit,
        String postalCode,
        String city,
        String region,
        String countryCode
) { }


package com.equipo11.petcare.dto;

import com.equipo11.petcare.domain.enums.ServiceName;

import java.time.LocalDateTime;

public record AvailabilityResponse(
        Long id,
        Long sitterId,
        ServiceName serviceName,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Boolean active
) {}

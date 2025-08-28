package com.equipo11.petcare.service;

import com.equipo11.petcare.dto.AvailabilityRequest;
import com.equipo11.petcare.dto.AvailabilityResponse;

import java.util.List;

public interface AvailabilityService {
    AvailabilityResponse createAvailability(Long sitterId, AvailabilityRequest availabilityRequest);
    List<AvailabilityResponse> getBySitter(Long sitterId);
    void deleteAvailability(Long id);
    AvailabilityResponse updateAvailability(Long id, boolean active);
}

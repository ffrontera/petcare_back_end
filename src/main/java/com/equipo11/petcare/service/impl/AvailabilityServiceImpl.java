package com.equipo11.petcare.service.impl;

import com.equipo11.petcare.domain.Availability;
import com.equipo11.petcare.domain.enums.ServiceName;
import com.equipo11.petcare.domain.repository.AvailabilityRepository;
import com.equipo11.petcare.domain.user.entity.Sitter;
import com.equipo11.petcare.dto.AvailabilityRequest;
import com.equipo11.petcare.dto.AvailabilityResponse;
import com.equipo11.petcare.exception.BusinessException;
import com.equipo11.petcare.service.AvailabilityService;
import com.equipo11.petcare.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final UserService userService; // Asumo que existirá

    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository, UserService userService) {
        this.availabilityRepository = availabilityRepository;
        this.userService = userService;
    }

    public AvailabilityResponse createAvailability(Long sitterId, AvailabilityRequest request) {
        Sitter sitter = userService.findSitterById(sitterId)
                .orElseThrow(() -> new BusinessException("Sitter not found"));

        dateValidation(request.startTime(), request.endTime());
        durationValidation(request.serviceName(), request.startTime(), request.endTime());

        // Verificar solapamiento de servicios
        List<Availability> overlapping = availabilityRepository.findOverlapping(sitterId, request.startTime(), request.endTime());

        if (!overlapping.isEmpty()) {
            throw new BusinessException("Availability overlaps with existing schedule");
        }

        Availability availability = Availability.builder()
                .sitter(sitter)
                .serviceName(request.serviceName())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .active(true)
                .build();

        Availability savedAvailability = availabilityRepository.save(availability);
        return toResponse(savedAvailability);
    }

    private void dateValidation(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.isAfter(endTime)) {
            throw new BusinessException("Start time must be before end time");
        }
    }

    private void durationValidation(ServiceName serviceName, LocalDateTime startTime, LocalDateTime endTime) {
        long minutes = Duration.between(startTime, endTime).toMinutes();

        if (serviceName == ServiceName.WALKING && minutes != 60) {
            throw new BusinessException("Walks must be exactly 1 hour");
        }

        if (serviceName == ServiceName.PET_SITTING && minutes != 180) {
            throw new BusinessException("Pet sitting must be in 3-hour blocks");
        }

        if (serviceName == ServiceName.PET_DAYCARE) {
            if (!fullDay(startTime, endTime)) {
                throw new BusinessException("Pet daycare must be for a full day (00:00 to 23:59)");
            }
        }
    }

    private boolean fullDay(LocalDateTime startTime, LocalDateTime endTime) {
        return startTime.toLocalTime().equals(java.time.LocalTime.MIN) && endTime.toLocalTime().equals(java.time.LocalTime.MAX);
    }

    public List<AvailabilityResponse> getBySitter(Long sitterId) {
        return availabilityRepository.findBySitterIdAndServiceNameAndActiveTrue(sitterId, null)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public void deleteAvailability(Long id) {
        availabilityRepository.deleteById(id);
    }

    public AvailabilityResponse updateAvailability(Long id, boolean active) {
        Availability availability = availabilityRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Availability not found"));
        availability.setActive(active);
        Availability savedAvailability = availabilityRepository.save(availability);
        return toResponse(savedAvailability);
    }

    private AvailabilityResponse toResponse(Availability availability) {
        return new AvailabilityResponse(
            availability.getId(),
            availability.getSitter().getUserId(),
            availability.getServiceName(),
            availability.getStartTime(),
            availability.getEndTime(),
            availability.getActive()
        );
    }
}

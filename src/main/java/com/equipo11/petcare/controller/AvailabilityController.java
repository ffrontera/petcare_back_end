package com.equipo11.petcare.controller;

import com.equipo11.petcare.dto.AvailabilityRequest;
import com.equipo11.petcare.dto.AvailabilityResponse;
import com.equipo11.petcare.service.impl.AvailabilityServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

    private final AvailabilityServiceImpl availabilityService;

    public AvailabilityController(AvailabilityServiceImpl availabilityService) {
        this.availabilityService = availabilityService;
    }

    @PostMapping
    public ResponseEntity<AvailabilityResponse> createAvailability(
            @RequestParam Long sitterId,
            @Valid @RequestBody AvailabilityRequest availabilityRequest) {
        AvailabilityResponse availabilityResponse = availabilityService.createAvailability(sitterId, availabilityRequest);
        return ResponseEntity.created(URI.create("/api/availability/" + availabilityResponse.id())).body(availabilityResponse);
    }

    @GetMapping("sitter/{sitterId}")
    public ResponseEntity<List<AvailabilityResponse>> getAvailabilityBySitterId(@PathVariable Long sitterId) {
        return ResponseEntity.ok(availabilityService.getBySitter(sitterId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable Long id) {
        availabilityService.deleteAvailability(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<AvailabilityResponse> activateAvailability(@PathVariable Long id, @RequestParam boolean active) {
        return ResponseEntity.ok(availabilityService.updateAvailability(id, active));
    }

}

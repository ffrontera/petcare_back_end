package com.equipo11.petcare.domain.repository;

import com.equipo11.petcare.domain.Availability;
import com.equipo11.petcare.domain.enums.ServiceName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    @Query("SELECT a FROM Availability a WHERE a.sitter.userId = :sitterId " +
            "AND a.active = true " +
            "AND (:serviceName IS NULL OR a.serviceName = :serviceName)")
    List<Availability> findBySitterIdAndServiceNameAndActiveTrue(
            @Param("sitterId") Long sitterId,
            @Param("serviceName") ServiceName serviceName
    );

    // Verificar solapamiento: hay disponibilidad en el rango [startTime, endTime]
    @Query("SELECT a FROM Availability a WHERE a.sitter.userId = :sitterId " +
            "AND a.active = true " +
            "AND a.startTime < :endTime AND a.endTime > :startTime")
    List<Availability> findOverlapping(
            @Param("sitterId") Long sitterId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );

}

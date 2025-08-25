package com.equipo11.petcare.domain.address.repository;

import com.equipo11.petcare.domain.address.entity.Region;
import com.equipo11.petcare.domain.address.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {

    /**
     * Busca una región por su nombre y el país al que pertenece.
     */
    Optional<Region> findByNameAndCountry(String name, Country country);
}


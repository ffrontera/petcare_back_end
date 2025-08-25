package com.equipo11.petcare.domain.address.repository;

import com.equipo11.petcare.domain.address.entity.City;
import com.equipo11.petcare.domain.address.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    /**
     * Busca una ciudad por su nombre dentro de una determinada región.
     */
    Optional<City> findByNameAndRegion(String name, Region region);
}


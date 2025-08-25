package com.equipo11.petcare.domain.address.repository;


import com.equipo11.petcare.domain.address.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    /**
     * Busca un país por su código ISO alpha-2.
     */
    Optional<Country> findByCountryCode(String countryCode);
}

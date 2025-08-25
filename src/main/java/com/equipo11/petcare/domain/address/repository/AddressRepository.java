package com.equipo11.petcare.domain.address.repository;

import com.equipo11.petcare.domain.address.entity.Address;
import com.equipo11.petcare.domain.address.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    /**
     * Busca una dirección por calle, número y ciudad.
     * Útil para detectar y evitar duplicados.
     */
    Optional<Address> findByStreetNameAndStreetNumberAndCity(
            String streetName,
            String streetNumber,
            City city
    );
}


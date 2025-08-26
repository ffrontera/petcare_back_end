package com.equipo11.petcare.domain.address.service;

import com.equipo11.petcare.domain.address.entity.Address;
import com.equipo11.petcare.domain.address.entity.City;
import com.equipo11.petcare.domain.address.entity.Country;
import com.equipo11.petcare.domain.address.entity.Region;
import com.equipo11.petcare.domain.address.repository.AddressRepository;
import com.equipo11.petcare.domain.address.repository.CityRepository;
import com.equipo11.petcare.domain.address.repository.CountryRepository;
import com.equipo11.petcare.domain.address.repository.RegionRepository;
import com.equipo11.petcare.dto.ParsedAddress;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class AddressNormalizationService {
    // inyectar repos: CountryRepo, RegionRepo, CityRepo, AddressRepo
    @Autowired
    private CountryRepository countryRepo;

    @Autowired
    private RegionRepository regionRepo;

    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private AddressRepository addressRepo;

    public Address normalize(ParsedAddress dto) {
        Country country = countryRepo.findByCountryCode(dto.countryCode().toUpperCase())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "País no encontrado con código: " + dto.countryCode()
                        )
                );

        Region region = regionRepo.findByNameAndCountry(dto.region(), country)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Provincia no encontrada: " + dto.region()
                        )
                );

        City city = cityRepo.findByNameAndRegion(dto.city(), region)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Ciudad no encontrada: " + dto.city()
                )
                );


        return addressRepo.findByStreetNameAndStreetNumberAndCity(
                dto.streetName(), dto.streetNumber(), city
        ).orElseGet(() -> addressRepo.save(new Address(
                null,
                dto.streetName(),
                dto.streetNumber(),
                dto.unit(),
                dto.postalCode(),
                city
        )));
    }
}


package com.equipo11.petcare.domain.address.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
@Getter
@Setter @NoArgsConstructor @AllArgsConstructor
public class Country {

    @Id
    @Column(name = "country_code", length = 2, nullable = false, unique = true)
    private String countryCode;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private Set<Region> regions = new HashSet<>();
}


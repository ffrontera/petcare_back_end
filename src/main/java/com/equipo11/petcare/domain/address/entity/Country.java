package com.equipo11.petcare.domain.address.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "countries")
@Getter
@Setter @NoArgsConstructor @AllArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_code", length = 2, nullable = false, unique = true)
    private String countryCode;

    @Column(nullable = false)
    private String name;
}


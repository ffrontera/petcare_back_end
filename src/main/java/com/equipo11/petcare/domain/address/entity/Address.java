package com.equipo11.petcare.domain.address.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street_name", nullable = false)
    private String streetName;

    @Column(name = "street_number")
    private String streetNumber;

    @Column
    private String unit;

    @Column(name = "postal_code")
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
}

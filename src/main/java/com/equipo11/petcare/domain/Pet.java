package com.equipo11.petcare.domain;

import com.equipo11.petcare.domain.user.entity.Owner;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    private String species;

    @Column(nullable = false)
    private String breed;
    private int age;
    private BigDecimal weight;
    private String comments;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToMany(mappedBy = "pets")
    private List<Booking> bookings = new ArrayList<>();

}

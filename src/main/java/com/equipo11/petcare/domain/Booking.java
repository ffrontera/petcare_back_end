package com.equipo11.petcare.domain;

import com.equipo11.petcare.domain.enums.BookingState;
import com.equipo11.petcare.domain.user.entity.Owner;
import com.equipo11.petcare.domain.user.entity.Sitter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "sitter_id")
    private Sitter sitter;

    @ManyToMany
    @JoinTable(
            name = "booking_pet",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id")
    )
    private List<Pet> pets = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private BookingState status = BookingState.PENDING;

    @Column(nullable = false)
    private BigDecimal price;

    private Boolean paid = false;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private Service service;

    @OneToOne(mappedBy = "booking")
    private Review review;
}

package com.equipo11.petcare.domain;

import com.equipo11.petcare.domain.user.entity.Owner;
import com.equipo11.petcare.domain.user.entity.Sitter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "sitter_id")
    private Sitter sitter;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}

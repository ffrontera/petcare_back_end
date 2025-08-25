package com.equipo11.petcare.domain;

import com.equipo11.petcare.domain.address.entity.Address;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private LocalDate birthdate;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    private boolean state;

    @Column(nullable = false)
    private int numberPhone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}

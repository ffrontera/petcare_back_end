package com.equipo11.petcare.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sitter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sitter extends User{

    private String typeDocument;
    private boolean state = false;
    private boolean availability;
    private String bio;


    @OneToMany(mappedBy = "sitter")
    private List<Availability> availabilities;

    @OneToMany(mappedBy = "sitter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "sitter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

}

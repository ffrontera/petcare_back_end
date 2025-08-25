package com.equipo11.petcare.domain.user.entity;

import com.equipo11.petcare.domain.Booking;
import com.equipo11.petcare.domain.Pet;
import com.equipo11.petcare.domain.Review;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "owner")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Owner extends User{

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

}

package com.equipo11.petcare.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "availability")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Availability {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sitter_id")
    private Sitter sitter;
}

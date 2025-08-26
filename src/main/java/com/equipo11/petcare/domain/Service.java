package com.equipo11.petcare.domain;

import com.equipo11.petcare.domain.enums.ServiceName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ServiceName name;

    @Column(nullable = false)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean available;

    @OneToOne(mappedBy = "service", cascade = CascadeType.ALL)
    private Booking booking;
}

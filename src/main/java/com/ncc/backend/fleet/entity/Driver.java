package com.ncc.backend.fleet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "drivers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String licenseNumber; // Numero patente / patentino KB

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DriverStatus status;

    public enum DriverStatus {
        AVAILABLE,
        ON_TRIP,
        OFF_DUTY
    }
}
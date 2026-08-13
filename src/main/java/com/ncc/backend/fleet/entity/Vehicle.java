package com.ncc.backend.fleet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String brand; // es. Mercedes-Benz

    @Column(nullable = false)
    private String model; // es. Classe V

    @Column(nullable = false, unique = true)
    private String licensePlate; // Targa

    @Column(nullable = false)
    private Integer passengerCapacity; // Capienza passeggeri

    @Column(nullable = false)
    private Integer luggageCapacity; // Capienza bagagli

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleStatus status;

    public enum VehicleStatus {
        AVAILABLE,
        IN_MAINTENANCE,
        OUT_OF_SERVICE
    }
}
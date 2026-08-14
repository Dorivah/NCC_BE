package com.ncc.backend.fleet.entity;

import com.fasterxml.jackson.annotation.JsonValue;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleCategory category;

    @Column(nullable = false)
    private Integer passengerCapacity; // Capienza passeggeri

    @Column(nullable = false)
    private Integer luggageCapacity; // Capienza bagagli

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleStatus status;

    // Enum Categorie NCC
    public enum VehicleCategory {
        AUTOVETTURA("Autovettura"),
        SUV_CROSSOVER("SUV / crossover"),
        MONOVOLUME_MINIVAN("Monovolume / minivan"),
        MINIBUS("Minibus"),
        LUXURY_EXECUTIVE("Veicoli di lusso / executive"),
        PMR_ACCESSIBLE("Veicoli attrezzati PMR"),
        ELECTRIC_HYBRID("Veicoli elettrici o ibridi");

        private final String label;

        VehicleCategory(String label) {
            this.label = label;
        }

        @JsonValue
        public String getLabel() {
            return label;
        }
    }

    // Enum Stato Veicolo
    public enum VehicleStatus {
        AVAILABLE,
        IN_MAINTENANCE,
        OUT_OF_SERVICE
    }
}
package com.ncc.backend.fleet.dto;

import com.ncc.backend.fleet.entity.Vehicle.VehicleStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class VehicleDTOs {

    public record CreateVehicleRequest(
            @NotBlank(message = "La marca è obbligatoria") String brand,
            @NotBlank(message = "Il modello è obbligatorio") String model,
            @NotBlank(message = "La targa è obbligatoria") String licensePlate,
            @NotNull @Min(1) Integer passengerCapacity,
            @NotNull @Min(0) Integer luggageCapacity
    ) {}

    public record VehicleResponse(
            UUID id,
            String brand,
            String model,
            String licensePlate,
            Integer passengerCapacity,
            Integer luggageCapacity,
            VehicleStatus status
    ) {}
}
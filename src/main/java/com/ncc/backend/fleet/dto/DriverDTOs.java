package com.ncc.backend.fleet.dto;

import com.ncc.backend.fleet.entity.Driver.DriverStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public class DriverDTOs {

    public record CreateDriverRequest(
            @NotBlank(message = "Il nome è obbligatorio") String firstName,
            @NotBlank(message = "Il cognome è obbligatorio") String lastName,
            @NotBlank(message = "Il numero di telefono è obbligatorio") String phoneNumber,
            @NotBlank(message = "Il numero di patente/patentino KB è obbligatorio") String licenseNumber
    ) {}

    public record DriverResponse(
            UUID id,
            String firstName,
            String lastName,
            String phoneNumber,
            String licenseNumber,
            DriverStatus status
    ) {}
}
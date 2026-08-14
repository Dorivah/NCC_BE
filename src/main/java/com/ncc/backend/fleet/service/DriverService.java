package com.ncc.backend.fleet.service;

import com.ncc.backend.fleet.dto.DriverDTOs.*;
import com.ncc.backend.fleet.entity.Driver;
import com.ncc.backend.fleet.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    @Transactional
    public DriverResponse createDriver(CreateDriverRequest request) {
        if (driverRepository.existsByLicenseNumber(request.licenseNumber())) {
            throw new IllegalArgumentException("Autista con patente " + request.licenseNumber() + " già esistente.");
        }
        if (driverRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new IllegalArgumentException("Autista con numero di telefono " + request.phoneNumber() + " già esistente.");
        }

        Driver driver = Driver.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .phoneNumber(request.phoneNumber())
                .licenseNumber(request.licenseNumber().toUpperCase())
                .status(Driver.DriverStatus.AVAILABLE)
                .build();

        Driver saved = driverRepository.save(driver);
        return mapToResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<DriverResponse> getAllDrivers() {
        return driverRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public DriverResponse getDriverById(UUID id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Autista non trovato con ID: " + id));
        return mapToResponse(driver);
    }

    private DriverResponse mapToResponse(Driver driver) {
        return new DriverResponse(
                driver.getId(),
                driver.getFirstName(),
                driver.getLastName(),
                driver.getPhoneNumber(),
                driver.getLicenseNumber(),
                driver.getStatus()
        );
    }
}
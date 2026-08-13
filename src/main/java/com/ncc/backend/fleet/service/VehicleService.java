package com.ncc.backend.fleet.service;

import com.ncc.backend.fleet.dto.VehicleDTOs.*;
import com.ncc.backend.fleet.entity.Vehicle;
import com.ncc.backend.fleet.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Transactional
    public VehicleResponse createVehicle(CreateVehicleRequest request) {
        if (vehicleRepository.existsByLicensePlate(request.licensePlate())) {
            throw new IllegalArgumentException("Veicolo con targa " + request.licensePlate() + " già esistente.");
        }

        Vehicle vehicle = Vehicle.builder()
                .brand(request.brand())
                .model(request.model())
                .licensePlate(request.licensePlate().toUpperCase())
                .passengerCapacity(request.passengerCapacity())
                .luggageCapacity(request.luggageCapacity())
                .status(Vehicle.VehicleStatus.AVAILABLE)
                .build();

        Vehicle saved = vehicleRepository.save(vehicle);
        return mapToResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<VehicleResponse> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public VehicleResponse getVehicleById(UUID id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Veicolo non trovato con ID: " + id));
        return mapToResponse(vehicle);
    }

    private VehicleResponse mapToResponse(Vehicle vehicle) {
        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getLicensePlate(),
                vehicle.getPassengerCapacity(),
                vehicle.getLuggageCapacity(),
                vehicle.getStatus()
        );
    }
}
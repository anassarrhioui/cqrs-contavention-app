package me.arrhioui.contraventionqueryservice.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.arrhioui.contraventionqueryservice.entity.Contravention;
import me.arrhioui.contraventionqueryservice.entity.Owner;
import me.arrhioui.contraventionqueryservice.entity.Vehicle;
import me.arrhioui.contraventionqueryservice.repository.ContraventionRepository;
import me.arrhioui.contraventionqueryservice.repository.OwnerRepository;
import me.arrhioui.contraventionqueryservice.repository.VehicleRepository;
import me.arrhioui.core.dto.OverSpeedRequestDTO;
import me.arrhioui.core.dto.VehicleRequestDTO;
import me.arrhioui.core.event.*;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ContraventionEventHandler {

    private final ContraventionRepository contraventionRepository;
    private final OwnerRepository ownerRepository;
    private final VehicleRepository vehicleRepository;

    @EventHandler
    public void on(VehicleCreatedEvent event) {
        log.info("VehicleCreatedEvent in contravention");
        Owner owner = null;
        VehicleRequestDTO payload = event.getPayload();
        if (!payload.getOwnerId().equals("")) {
            owner = ownerRepository.findById(payload.getOwnerId()).orElse(null);
        }

        if (owner == null) {
            owner = Owner
                    .builder()
                    .id(UUID.randomUUID().toString())
                    .ownerNationalIdCard(payload.getOwnerNationalIdCard())
                    .ownerName(payload.getOwnerName())
                    .ownerEmail(payload.getOwnerEmail())
                    .ownerPhoneNumber(payload.getOwnerPhoneNumber())
                    .ownerAddress(payload.getOwnerAddress())
                    .build();
            owner = ownerRepository.save(owner);
        }

        Vehicle vehicle = Vehicle
                .builder()
                .registrationNumber(payload.getRegistrationNumber())
                .brand(payload.getBrand())
                .type(payload.getType())
                .model(payload.getModel())
                .owner(owner)
                .build();

        vehicleRepository.save(vehicle);

    }

    @EventHandler
    public void on(VehicleUpdatedEvent event){
        Vehicle vehicle = vehicleRepository.findById(event.getId()).get();
        VehicleRequestDTO payload = event.getPayload();
        vehicle.setBrand(payload.getBrand());
        vehicle.setModel(payload.getModel());
        vehicle.setType(payload.getType());
        vehicle.setFiscalPower(payload.getFiscalPower());

        vehicleRepository.save(vehicle);
    }

    @EventHandler
    public void on(VehicleOwnerUpdatedEvent event){
        Vehicle vehicle = vehicleRepository.findById(event.getId()).get();
        Owner owner = ownerRepository.findById(event.getPayload().getOwnerId()).get();
        vehicle.setOwner(owner);
        vehicleRepository.save(vehicle);
    }

    @EventHandler
    public void on(ContraventionCreatedEvent event){
        log.info("Contravention Added For ");
        OverSpeedRequestDTO payload = event.getPayload();
        System.out.println("==payload.getVehicleRegistrationNumber() = " + payload.getVehicleRegistrationNumber());
        Vehicle vehicle = vehicleRepository.findByRegistrationNumber(payload.getVehicleRegistrationNumber()).orElse(null);
        Contravention contravention = Contravention
                .builder()
                .vehicle(vehicle)
                .date(payload.getTimeStamp())
                .id(UUID.randomUUID().toString())
                .radarId(payload.getRadarId())
                .matriculationVehicule(payload.getVehicleRegistrationNumber())
                .viteseRadarMax(payload.getRadarMaxSpeed())
                .vitesseVehicule(payload.getVehicleSpeed())
                .build();
        contraventionRepository.save(contravention);
    }
}

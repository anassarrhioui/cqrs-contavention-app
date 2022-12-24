package me.arrhioui.vehiclequeryservice.service;

import lombok.AllArgsConstructor;
import me.arrhioui.core.dto.OwnerRequestDTO;
import me.arrhioui.core.dto.UpdateVehicleOwnerRequestDTO;
import me.arrhioui.core.event.OwnerCreatedEvent;
import me.arrhioui.core.event.VehicleOwnerUpdatedEvent;
import me.arrhioui.vehiclequeryservice.entity.Owner;
import me.arrhioui.vehiclequeryservice.repository.OwnerRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class OwnerEventHandlerService {
    private final OwnerRepository ownerRepository;

    @EventHandler
    public void on(OwnerCreatedEvent event){
        OwnerRequestDTO payload = event.getPayload();
        Owner owner = Owner
                .builder()
                .id(event.getId())
                .ownerName(payload.getOwnerName())
                .ownerNationalIdCard(payload.getOwnerNationalIdCard())
                .ownerAddress(payload.getOwnerAddress())
                .ownerEmail(payload.getOwnerEmail())
                .ownerPhoneNumber(payload.getOwnerPhoneNumber())
                .build();

        ownerRepository.save(owner);
    }

}

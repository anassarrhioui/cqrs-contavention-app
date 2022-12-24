package me.arrhioui.vehiclequeryservice.query;


import lombok.AllArgsConstructor;
import me.arrhioui.core.query.GetAllOwners;
import me.arrhioui.core.query.GetAllVehiclesQuery;
import me.arrhioui.vehiclequeryservice.entity.Owner;
import me.arrhioui.vehiclequeryservice.entity.Vehicle;
import me.arrhioui.vehiclequeryservice.repository.OwnerRepository;
import me.arrhioui.vehiclequeryservice.repository.VehicleRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OwnerQueryHandler {

    private final OwnerRepository ownerRepository;

    @QueryHandler
    public List<Owner> getAllOwners(GetAllOwners query){
        return ownerRepository.findAll();
    }
}

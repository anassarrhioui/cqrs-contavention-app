package me.arrhioui.vehiclequeryservice.query;


import lombok.AllArgsConstructor;
import me.arrhioui.core.query.GetAllVehiclesQuery;
import me.arrhioui.vehiclequeryservice.entity.Vehicle;
import me.arrhioui.vehiclequeryservice.repository.VehicleRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VehicleQueryHandler {

    private final VehicleRepository vehicleRepository;

    @QueryHandler
    public List<Vehicle> getAllVehicle(GetAllVehiclesQuery query){
        return vehicleRepository.findAll();
    }
}

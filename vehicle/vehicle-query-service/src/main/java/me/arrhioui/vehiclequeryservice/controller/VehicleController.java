package me.arrhioui.vehiclequeryservice.controller;


import lombok.AllArgsConstructor;
import me.arrhioui.core.query.GetAllVehiclesQuery;
import me.arrhioui.vehiclequeryservice.entity.Vehicle;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController()
@RequestMapping("/query/vehicle")
@AllArgsConstructor
@CrossOrigin("*")
public class VehicleController {

    private QueryGateway queryGateway;

    @GetMapping(path = "/all")
    public CompletableFuture<List<Vehicle>> getAllVehicle() {
        return queryGateway.query(new GetAllVehiclesQuery(), ResponseTypes.multipleInstancesOf(Vehicle.class));
    }
}

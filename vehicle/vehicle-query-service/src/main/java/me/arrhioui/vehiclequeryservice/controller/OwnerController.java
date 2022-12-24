package me.arrhioui.vehiclequeryservice.controller;


import lombok.AllArgsConstructor;
import me.arrhioui.core.query.GetAllOwners;
import me.arrhioui.core.query.GetAllVehiclesQuery;
import me.arrhioui.vehiclequeryservice.entity.Owner;
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
@RequestMapping("/query/owners")
@AllArgsConstructor
@CrossOrigin("*")
public class OwnerController {

    private QueryGateway queryGateway;

    @GetMapping( path = "/all")
    public CompletableFuture<List<Owner>> getAllOwners() {
        return queryGateway.query(new GetAllOwners(), ResponseTypes.multipleInstancesOf(Owner.class));
    }
}

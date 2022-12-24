package me.arrhioui.vehiclequeryservice.repository;

import me.arrhioui.vehiclequeryservice.entity.Owner;
import me.arrhioui.vehiclequeryservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
}

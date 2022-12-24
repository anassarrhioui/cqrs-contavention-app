package me.arrhioui.contraventionqueryservice.repository;

import me.arrhioui.contraventionqueryservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    Optional<Vehicle> findByRegistrationNumber(String matriculation);
}

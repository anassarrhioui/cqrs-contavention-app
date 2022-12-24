package me.arrhioui.vehiclequeryservice.repository;

import me.arrhioui.vehiclequeryservice.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, String> {
}

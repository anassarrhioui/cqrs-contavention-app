package me.arrhioui.contraventionqueryservice.repository;

import me.arrhioui.contraventionqueryservice.entity.Contravention;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContraventionRepository extends JpaRepository<Contravention, String> {
    List<Contravention> findByVehicle_Owner_OwnerNationalIdCard(String cin);
}

package me.arrhioui.contraventionqueryservice.repository;

import me.arrhioui.contraventionqueryservice.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, String> {
}

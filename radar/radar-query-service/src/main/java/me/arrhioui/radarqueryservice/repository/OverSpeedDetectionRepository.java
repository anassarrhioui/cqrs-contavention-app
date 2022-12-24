package me.arrhioui.radarqueryservice.repository;

import me.arrhioui.radarqueryservice.entity.OverSpeedDetection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OverSpeedDetectionRepository extends JpaRepository<OverSpeedDetection, String> {
}

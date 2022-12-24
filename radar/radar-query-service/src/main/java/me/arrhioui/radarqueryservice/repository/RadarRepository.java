package me.arrhioui.radarqueryservice.repository;

import me.arrhioui.radarqueryservice.entity.Radar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RadarRepository extends JpaRepository<Radar, String> {
}

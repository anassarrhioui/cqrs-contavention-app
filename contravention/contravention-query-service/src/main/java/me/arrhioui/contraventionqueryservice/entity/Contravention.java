package me.arrhioui.contraventionqueryservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Contravention {
    @Id
    private String id;
    private Instant date;
    private String radarId;
    private String matriculationVehicule;
    private float vitesseVehicule;
    private float viteseRadarMax;
    private float montant;
    @OneToOne
    private Vehicle vehicle;
}

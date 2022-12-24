package me.arrhioui.radarqueryservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OverSpeedDetection {
    @Id
    private String overSpeedId;
    private Instant timeStamp;
    private String vehicleRegistrationNumber;
    private int vehicleSpeed;
    @ManyToOne
    private Radar radar;
}

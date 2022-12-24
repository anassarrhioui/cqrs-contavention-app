package me.arrhioui.radarqueryservice.entity;

import lombok.*;
import me.arrhioui.core.enums.RadarStatus;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Radar {

    @Id
    private String radarId;
    private String name;
    private double longitude;
    private double latitude;
    private  double altitude;
    private int maxSpeed;
    private String roadDesignation;
    private RadarStatus radarStatus;
}

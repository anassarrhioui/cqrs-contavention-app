package me.arrhioui.radarcommandservice.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.EntityId;

import java.time.Instant;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OverSpeedMembre {

    @EntityId
    private String overSpeedId;
    private Instant timeStamp;
    private String vehicleRegistrationNumber;
    private int vehicleSpeed;
}

package me.arrhioui.radarcommandservice.aggregate;


import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.arrhioui.core.command.ChangeRadarStatusCommand;
import me.arrhioui.core.command.CreateNewRadarCommand;
import me.arrhioui.core.command.NewVehicleOverSpeedDetectionCommand;
import me.arrhioui.core.command.UpdateRadarCommand;
import me.arrhioui.core.enums.RadarStatus;
import me.arrhioui.core.event.RadarCreatedEvent;
import me.arrhioui.core.event.RadarStatusChangedEvent;
import me.arrhioui.core.event.RadarUpdatedEvent;
import me.arrhioui.core.event.VehicleOverSpeedDetectedEvent;
import me.arrhioui.radarcommandservice.member.OverSpeedMembre;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Aggregate
@NoArgsConstructor
@Slf4j
public class RadarAggregate {

    @AggregateIdentifier
    private String radarId;
    private String name;
    private double longitude;
    private double latitude;
    private double altitude;
    private int maxSpeed;
    private String roadDesignation;
    private RadarStatus radarStatus;

    @AggregateMember
    private List<OverSpeedMembre> overSpeedMembres = new ArrayList<>();

    @CommandHandler
    public RadarAggregate(CreateNewRadarCommand newRadarCommand) {
        log.info("RadarCreatedEvent for id = " + newRadarCommand.getId());
        AggregateLifecycle.apply(new RadarCreatedEvent(
                newRadarCommand.getId(),
                newRadarCommand.getPayload()
        ));
    }

    @EventSourcingHandler
    public void on(RadarCreatedEvent event) {
        log.info("RadarCreatedEvent for id = " + event.getId());
        radarId = event.getId();
        name = event.getPayload().getName();
        altitude = event.getPayload().getAltitude();
        longitude = event.getPayload().getLongitude();
        latitude = event.getPayload().getLatitude();
        radarStatus = event.getPayload().getRadarStatus();
        maxSpeed = event.getPayload().getMaxSpeed();
        roadDesignation = event.getPayload().getRoadDesignation();
    }

    @CommandHandler
    public void on(ChangeRadarStatusCommand command) {
        log.info("ChangeRadarStatusCommand for id = " + command.getId());
        AggregateLifecycle.apply(new RadarStatusChangedEvent(
                command.getId(),
                command.getPayload()
        ));
    }

    @EventSourcingHandler
    public void on(RadarStatusChangedEvent event) {
        log.info("Radar Status Updated Event for id = " + event.getId());
        radarStatus = event.getPayload();
    }

    @CommandHandler
    public void handle(NewVehicleOverSpeedDetectionCommand command) {
        log.info("NewVehicleOversSpeedDetectionCommand received");

        command.getPayload().setOverSpeedId(UUID.randomUUID().toString());
        command.getPayload().setRadarMaxSpeed(this.maxSpeed);
        command.getPayload().setRadarLongitude(this.longitude);
        command.getPayload().setRadarLongitude(this.latitude);
        command.getPayload().setRadarAltitude(this.altitude);

        AggregateLifecycle.apply(new VehicleOverSpeedDetectedEvent(
                command.getId(),
                command.getPayload(),
                UUID.randomUUID().toString()
        ));
    }

    @EventSourcingHandler
    public void on(VehicleOverSpeedDetectedEvent event) {
        log.info("***** VehicleOversSpeedDetectedEvent");

        this.radarId = event.getId();
        OverSpeedMembre overSpeedMember = new OverSpeedMembre();
        overSpeedMember.setOverSpeedId(UUID.randomUUID().toString());
        overSpeedMember.setVehicleRegistrationNumber(event.getPayload().getVehicleRegistrationNumber());
        overSpeedMember.setVehicleSpeed(event.getPayload().getVehicleSpeed());
        overSpeedMembres.add(overSpeedMember);

        log.info("***** OverSpeed added " + overSpeedMembres.size());
    }

    @CommandHandler
    public void on(UpdateRadarCommand command){
        AggregateLifecycle.apply(new RadarUpdatedEvent(
                command.getId(),
                command.getRadarDTO()
        ));
    }
}

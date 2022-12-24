package me.arrhioui.contraventioncommandservice.aggregate;

import lombok.extern.slf4j.Slf4j;
import me.arrhioui.core.command.NewContraventionCommand;
import me.arrhioui.core.dto.OverSpeedRequestDTO;
import me.arrhioui.core.event.ContraventionCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;

@Aggregate
@Slf4j
public class ContraventionAggregate {

    @AggregateIdentifier
    private String id;
    private Instant date;
    private String radarId;
    private String matriculationVehicule;
    private float vitesseVehicule;
    private float viteseRadarMax;
    private float montant;

    public ContraventionAggregate() {
    }

    @CommandHandler
    public ContraventionAggregate(NewContraventionCommand command){
        log.info("***** NewContraventionCommand");
        command.getPayload().setMontant((float) (1000 * Math.random()));
        AggregateLifecycle.apply(new ContraventionCreatedEvent(
                command.getId(),
                command.getPayload()
        ));
    }

    @EventSourcingHandler
    public void on(ContraventionCreatedEvent event){
        log.info("***** ContraventionCreatedEvent");
        OverSpeedRequestDTO payload = event.getPayload();
        id = event.getId();
        date = payload.getTimeStamp();
        matriculationVehicule = payload.getVehicleRegistrationNumber();
        vitesseVehicule = payload.getVehicleSpeed();
        viteseRadarMax = payload.getRadarMaxSpeed();
        montant = (float) payload.getMontant();
    }

}

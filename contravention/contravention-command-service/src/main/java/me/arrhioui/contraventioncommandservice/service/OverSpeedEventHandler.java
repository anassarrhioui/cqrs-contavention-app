package me.arrhioui.contraventioncommandservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.arrhioui.core.command.NewContraventionCommand;
import me.arrhioui.core.event.VehicleOverSpeedDetectedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OverSpeedEventHandler {

    private final CommandGateway commandGateway;
    private EventStore eventStore;


    @EventHandler
    public void on(VehicleOverSpeedDetectedEvent event) {
        log.info("+++++VehicleOverSpeedDetectedEvent occured");
        commandGateway.send(new NewContraventionCommand(
                event.getContraventionId(),
                event.getPayload()
        ));

    }
}

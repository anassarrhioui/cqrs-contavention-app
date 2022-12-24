package me.arrhioui.vehiclecommandservice.aggregate;

import me.arrhioui.core.command.CreateOwnerCommand;
import me.arrhioui.core.command.UpdateVehicleOwnerCommand;
import me.arrhioui.core.dto.OwnerRequestDTO;
import me.arrhioui.core.dto.UpdateVehicleOwnerRequestDTO;
import me.arrhioui.core.enums.VehicleType;
import me.arrhioui.core.event.OwnerCreatedEvent;
import me.arrhioui.core.event.VehicleOwnerUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class OwnerAggregate {

    @AggregateIdentifier
    private String ownerId;
    private String ownerName;
    private String ownerNationalIdCard;
    private String ownerEmail;
    private String ownerPhoneNumber;
    private String ownerAddress;

    public  OwnerAggregate() {}

    @CommandHandler
    public  OwnerAggregate(CreateOwnerCommand command) {
        AggregateLifecycle.apply(new OwnerCreatedEvent(
                command.getId(),
                command.getPayload()
        ));
    }

    @EventSourcingHandler
    public void on(OwnerCreatedEvent event) {
        OwnerRequestDTO payload = event.getPayload();
        ownerId = payload.getOwnerId();
        ownerName = payload.getOwnerName();
        ownerNationalIdCard = payload.getOwnerNationalIdCard();
        ownerEmail = payload.getOwnerEmail();
        ownerPhoneNumber = payload.getOwnerPhoneNumber();
        ownerAddress = payload.getOwnerAddress();
    }
}

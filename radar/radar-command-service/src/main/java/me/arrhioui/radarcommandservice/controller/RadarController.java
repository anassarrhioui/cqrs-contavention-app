package me.arrhioui.radarcommandservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.arrhioui.core.command.ChangeRadarStatusCommand;
import me.arrhioui.core.command.CreateNewRadarCommand;
import me.arrhioui.core.command.NewVehicleOverSpeedDetectionCommand;
import me.arrhioui.core.command.UpdateRadarCommand;
import me.arrhioui.core.dto.ChangeRadarStatusRequestDTO;
import me.arrhioui.core.dto.OverSpeedRequestDTO;
import me.arrhioui.core.dto.RadarDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/radars/commands")
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class RadarController {

    private final CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture<String> createNewRadar(@RequestBody RadarDTO radarDTO){
        return commandGateway.send(new CreateNewRadarCommand(
                UUID.randomUUID().toString(),
                radarDTO)
        );
    }

    @PutMapping("/update")
    public CompletableFuture<String> updateRadar(@RequestBody RadarDTO radarDTO){
        return commandGateway.send(new UpdateRadarCommand(
                radarDTO.getRadarId(),
                radarDTO
        ));
    }

    @PutMapping("/updateStatus")
    public CompletableFuture<String> changeRadarStatus(@RequestBody ChangeRadarStatusRequestDTO dto){
        return commandGateway.send(new ChangeRadarStatusCommand(
                dto.getRadarId(),
                dto.getRadarStatus()
        ));
    }

    @PostMapping("/overSpeedDetection")
    public CompletableFuture<String> newOverSpeed(@RequestBody OverSpeedRequestDTO request){
        CompletableFuture<String> cmd1 = this.commandGateway.send(new NewVehicleOverSpeedDetectionCommand(
                request.getRadarId(),
                request
        ));
        return cmd1;
    }


}

package me.arrhioui.radarqueryservice.controller;

import lombok.AllArgsConstructor;
import me.arrhioui.core.dto.RadarResponseDTO;
import me.arrhioui.core.query.GetAllRadarsQuery;
import me.arrhioui.core.query.GetRadarById;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/radars/query")
@CrossOrigin("*")
@AllArgsConstructor
public class RadarController {

    private final QueryGateway queryGateway;

    @GetMapping("/all")
    public CompletableFuture<List<RadarResponseDTO>> getAllRadars(){
        return queryGateway.query(
                new GetAllRadarsQuery(),
                ResponseTypes.multipleInstancesOf(RadarResponseDTO.class)
        );
    }

    @GetMapping("/{id}")
    public CompletableFuture<RadarResponseDTO> getRadarById(@PathVariable String id){
        return queryGateway.query(
                new GetRadarById(id),
                ResponseTypes.instanceOf(RadarResponseDTO.class)
        );
    }
}

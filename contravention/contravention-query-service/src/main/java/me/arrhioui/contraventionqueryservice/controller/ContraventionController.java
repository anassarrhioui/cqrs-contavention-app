package me.arrhioui.contraventionqueryservice.controller;


import lombok.AllArgsConstructor;
import me.arrhioui.contraventionqueryservice.entity.Contravention;
import me.arrhioui.core.dto.ContraventionData;
import me.arrhioui.core.query.GetAllContraventions;
import me.arrhioui.core.query.GetContraventionsByNationalCardNumber;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/contraventions/query")
@AllArgsConstructor
@CrossOrigin("*")
public class ContraventionController {

    private final QueryGateway queryGateway;

    @GetMapping("/all")
    public CompletableFuture<List<Contravention>> getAll(){
        return queryGateway.query(new GetAllContraventions(0, 0),
                ResponseTypes.multipleInstancesOf(Contravention.class));
    }

    @GetMapping("/cin/{cin}")
    public CompletableFuture<List<Contravention>> getByCin(@PathVariable String cin){
        return queryGateway.query(new GetContraventionsByNationalCardNumber(cin, 0, 0),
                ResponseTypes.multipleInstancesOf(Contravention.class));
    }
}

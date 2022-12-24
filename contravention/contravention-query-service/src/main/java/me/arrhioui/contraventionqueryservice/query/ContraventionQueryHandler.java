package me.arrhioui.contraventionqueryservice.query;

import lombok.AllArgsConstructor;
import me.arrhioui.contraventionqueryservice.entity.Contravention;
import me.arrhioui.contraventionqueryservice.repository.ContraventionRepository;
import me.arrhioui.core.query.GetAllContraventions;
import me.arrhioui.core.query.GetContraventionsByNationalCardNumber;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContraventionQueryHandler {

    private ContraventionRepository contraventionRepository;

    @QueryHandler
    public List<Contravention> getAllContravention(GetAllContraventions query){
        return contraventionRepository.findAll();
    }

    @QueryHandler
    public List<Contravention> getContraventionByCIN(GetContraventionsByNationalCardNumber query){
        return contraventionRepository.findByVehicle_Owner_OwnerNationalIdCard(query.getNationalCardNumber());
    }
}

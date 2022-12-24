package me.arrhioui.radarqueryservice.query;

import lombok.AllArgsConstructor;
import me.arrhioui.core.dto.RadarResponseDTO;
import me.arrhioui.core.query.GetAllRadarsQuery;
import me.arrhioui.core.query.GetRadarById;
import me.arrhioui.radarqueryservice.entity.Radar;
import me.arrhioui.radarqueryservice.mapper.RadarMapper;
import me.arrhioui.radarqueryservice.repository.OverSpeedDetectionRepository;
import me.arrhioui.radarqueryservice.repository.RadarRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RadarQueryHandler {

    private RadarRepository radarRepository;
    private OverSpeedDetectionRepository overSpeedDetectionRepository;
    private RadarMapper radarMappers;

    @QueryHandler
    public List<RadarResponseDTO> handler(GetAllRadarsQuery query){
        return radarRepository
                .findAll()
                .stream()
                .map(radar->radarMappers.from(radar))
                .collect(Collectors.toList());
    }

    @QueryHandler
    public RadarResponseDTO getRadarById(GetRadarById query){
        return radarRepository.findById(query.getRadarId())
                .map( radar -> radarMappers.from(radar))
                .orElse(null);
    }
}

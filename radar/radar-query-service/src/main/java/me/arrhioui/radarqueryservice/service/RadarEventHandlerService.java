package me.arrhioui.radarqueryservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.arrhioui.core.dto.EventDataResponseDTO;
import me.arrhioui.core.dto.OverSpeedRequestDTO;
import me.arrhioui.core.event.RadarCreatedEvent;
import me.arrhioui.core.event.RadarStatusChangedEvent;
import me.arrhioui.core.event.RadarUpdatedEvent;
import me.arrhioui.core.event.VehicleOverSpeedDetectedEvent;
import me.arrhioui.core.query.SubscribeToEventsQuery;
import me.arrhioui.radarqueryservice.entity.OverSpeedDetection;
import me.arrhioui.radarqueryservice.entity.Radar;
import me.arrhioui.radarqueryservice.mapper.RadarMapper;
import me.arrhioui.radarqueryservice.repository.OverSpeedDetectionRepository;
import me.arrhioui.radarqueryservice.repository.RadarRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class RadarEventHandlerService {
    private RadarRepository radarRepository;
    private OverSpeedDetectionRepository overSpeedDetectionRepository;
    private RadarMapper radarMappers;
    private QueryUpdateEmitter queryUpdateEmitter;

    @EventHandler
    public void on(RadarCreatedEvent event, EventMessage<RadarCreatedEvent> eventMessage) {
        log.info("RadarCreatedEvent date = " + eventMessage.getTimestamp());
        Radar radar = radarMappers.from(event.getPayload());
        radar.setRadarId(event.getId());
        radarRepository.save(radar);
        event.getPayload().setRadarId(event.getId());
        EventDataResponseDTO eventDataResponseDTO = new EventDataResponseDTO(
                event.getClass().getSimpleName(),
                event
        );
        queryUpdateEmitter.emit(SubscribeToEventsQuery.class, (query) -> true, eventDataResponseDTO);
    }

    @EventHandler
    public void on(RadarStatusChangedEvent event) {
        Radar radar = radarRepository.findById(event.getId()).get();
        radar.setRadarStatus(event.getPayload());
        radarRepository.save(radar);
    }

    @EventHandler
    public void on(VehicleOverSpeedDetectedEvent event) {
        OverSpeedRequestDTO payload = event.getPayload();
        String radarId = payload.getRadarId();
        Radar radar = radarRepository.findById(radarId).get();
        OverSpeedDetection overSpeedDetection = OverSpeedDetection
                .builder()
                .radar(radar)
                .vehicleSpeed(payload.getVehicleSpeed())
                .overSpeedId(payload.getOverSpeedId())
                .timeStamp(event.getPayload().getTimeStamp())
                .vehicleRegistrationNumber(payload.getVehicleRegistrationNumber())
                .build();
        overSpeedDetectionRepository.save(overSpeedDetection);
    }

    @EventHandler
    public void on(RadarUpdatedEvent event){
        Radar radar = radarMappers.from(event.getPayload());
        radar.setRadarId(event.getId());
        radarRepository.save(radar);
    }
}

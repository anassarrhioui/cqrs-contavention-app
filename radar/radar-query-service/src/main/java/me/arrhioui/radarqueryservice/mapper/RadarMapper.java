package me.arrhioui.radarqueryservice.mapper;

import me.arrhioui.core.dto.*;
import me.arrhioui.radarqueryservice.entity.OverSpeedDetection;
import me.arrhioui.radarqueryservice.entity.Radar;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RadarMapper {
    Radar from(RadarDTO radarDTO);
    RadarResponseDTO from(Radar radar);
    RadarOverSpeedsDTO fromRadar(Radar radar);
    OverSpeedDetection from(OverSpeedRequestDTO overSpeedRequestDTO);
    OverSpeedResponseDTO fromOS(OverSpeedDetection overSpeedRequest);
}

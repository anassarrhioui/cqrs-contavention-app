package me.arrhioui.core.command

import me.arrhioui.core.dto.OverSpeedRequestDTO
import me.arrhioui.core.dto.RadarDTO
import me.arrhioui.core.enums.RadarStatus
import org.axonframework.modelling.command.TargetAggregateIdentifier


abstract class BaseCommand<T>(
    @TargetAggregateIdentifier open val id: T
);
data class CreateNewRadarCommand(
    override val id: String,
    val payload: RadarDTO,
) : BaseCommand<String>(id);

data class UpdateRadarCommand(
    override val id: String,
    val radarDTO: RadarDTO,
) : BaseCommand<String>(id);

data class ChangeRadarStatusCommand(
    override val id: String,
    val payload: RadarStatus,
) : BaseCommand<String>(id);

data class ChangeRadarSpeedLimitCommand(
    override val id: String,
    val payload: Int,
) : BaseCommand<String>(id);

data class NewVehicleOverSpeedDetectionCommand(
    override val id: String,
    val payload: OverSpeedRequestDTO,
) : BaseCommand<String>(id);

data class NewContraventionCommand(
    override val id: String,
    val payload: OverSpeedRequestDTO,
) : BaseCommand<String>(id);

package me.arrhioui.core.event

import me.arrhioui.core.dto.ContraventionData
import me.arrhioui.core.dto.OverSpeedRequestDTO
import me.arrhioui.core.dto.RadarDTO
import me.arrhioui.core.enums.RadarStatus


abstract class BaseEvent<T>(
    open val id: T
);
data class RadarCreatedEvent(
    override val id: String,
    val payload: RadarDTO
) : BaseEvent<String>(id);

data class RadarUpdatedEvent(
    override val id: String,
    val payload: RadarDTO
) : BaseEvent<String>(id);

data class RadarStatusChangedEvent(
    override val id: String,
    val payload: RadarStatus,
) : BaseEvent<String>(id);

data class RadarSpeedLimitChangedEvent(
    override val id: String,
    val payload: Int,
) : BaseEvent<String>(id);

data class VehicleOverSpeedDetectedEvent(
    override val id: String,
    val payload: OverSpeedRequestDTO,
    val contraventionId: String,
) : BaseEvent<String>(id);

data class ContraventionCreatedEvent(
    override val id: String,
    val payload: OverSpeedRequestDTO,
) : BaseEvent<String>(id);


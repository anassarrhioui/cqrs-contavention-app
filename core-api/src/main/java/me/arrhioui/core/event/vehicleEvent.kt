package me.arrhioui.core.event

import me.arrhioui.core.command.BaseCommand
import me.arrhioui.core.dto.OwnerRequestDTO
import me.arrhioui.core.dto.UpdateVehicleOwnerRequestDTO
import me.arrhioui.core.dto.VehicleRequestDTO


data class VehicleCreatedEvent(
    override val id : String,
    val payload: VehicleRequestDTO
): BaseEvent<String>(id);

data class VehicleUpdatedEvent(
    override val id : String,
    val payload: VehicleRequestDTO
): BaseEvent<String>(id);

data class VehicleOwnerUpdatedEvent(
    override val id : String,
    val payload: UpdateVehicleOwnerRequestDTO
):BaseEvent<String>(id);


data class OwnerCreatedEvent(
    override val id : String,
    val payload: OwnerRequestDTO
): BaseEvent<String>(id);

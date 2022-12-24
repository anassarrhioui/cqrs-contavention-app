package me.arrhioui.core.command

import me.arrhioui.core.dto.OwnerRequestDTO
import me.arrhioui.core.dto.UpdateVehicleOwnerRequestDTO
import me.arrhioui.core.dto.VehicleRequestDTO

data class CreateVehicleCommand(
    override val id : String,
    val payload : VehicleRequestDTO,
) : BaseCommand<String>(id);

data class UpdateVehicleCommand(
    override val id : String,
    val payload : VehicleRequestDTO,
) : BaseCommand<String>(id);


data class UpdateVehicleOwnerCommand(
    override val id : String,
    val payload : UpdateVehicleOwnerRequestDTO,
) : BaseCommand<String>(id);


data class CreateOwnerCommand(
    override val id : String,
    val payload : OwnerRequestDTO,
) : BaseCommand<String>(id);

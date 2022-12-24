package me.arrhioui.core.exception


abstract class VehicleOverSpeedingTicketException(message : String)
    : RuntimeException(message);
class VehicleOwnerAlreadyAffectedException(message : String) : VehicleOverSpeedingTicketException(message);

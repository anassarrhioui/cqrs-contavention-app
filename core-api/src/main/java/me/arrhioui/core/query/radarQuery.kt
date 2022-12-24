package me.arrhioui.core.query


class GetAllRadarsQuery();
class GetRadarsQuery();

data class GetRadarById(
    val radarId: String,
);

class GetOverSpeedsQuery();

class SubscribeToEventsQuery();

class GetAllOverSpeedsQuery();

class GetAllOverSpeedsByRegistrationNumberQuery(
    val registrationNumber: String,
);

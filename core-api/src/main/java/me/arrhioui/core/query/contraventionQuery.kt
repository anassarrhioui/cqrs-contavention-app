package me.arrhioui.core.query


class GetAllContraventions(
    val page : Int,
    val size : Int
);

class GetContraventionsByNationalCardNumber(
    val nationalCardNumber : String,
    val page : Int,
    val size : Int
);

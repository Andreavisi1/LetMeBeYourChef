package com.example.letmebeyourchef.model

data class Dieta (
    var titolo: String = "",
    val image: String = "",
    val perc_carb: Int = 0,
    val perc_prot: Int = 0,
    val perc_grassi: Int = 0,
    val descrizione: String = ""
    )
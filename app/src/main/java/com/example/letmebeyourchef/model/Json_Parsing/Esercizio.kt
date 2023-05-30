package com.example.letmebeyourchef.model.Json_Parsing

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Esercizio(
    val id : String = "",
    @field:Json(name = "calories_per_hour")val calorieOra: Int = 0,
    @field:Json(name = "duration_minutes")val durata: Int = 0,
    @field:Json(name = "name")val nome: String = "",
    @field:Json(name = "total_calories")val calorieTot: Int = 0
)
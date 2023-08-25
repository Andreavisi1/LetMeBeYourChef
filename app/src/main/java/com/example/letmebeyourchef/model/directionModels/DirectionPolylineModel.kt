package com.example.letmebeyourchef.model.directionModels

import com.squareup.moshi.Json

data class DirectionPolylineModel(
    @field:Json(name="points")

    var points: String? = null
)
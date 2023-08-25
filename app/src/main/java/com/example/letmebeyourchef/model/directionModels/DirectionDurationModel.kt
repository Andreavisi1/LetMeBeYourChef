package com.example.letmebeyourchef.model.directionModels

import com.squareup.moshi.Json

data class DirectionDurationModel(
    @field:Json(name = "text")
    var text: String? = null,

    @field:Json(name = "value")
    var value: Int? = null
)
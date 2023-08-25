package com.example.letmebeyourchef.model.directionModels

import com.squareup.moshi.Json

data class EndLocationModel(
    @field:Json(name = "lat")
    var lat: Double? = null,

    @field:Json(name = "lng")
    var lng: Double? = null
) {

}
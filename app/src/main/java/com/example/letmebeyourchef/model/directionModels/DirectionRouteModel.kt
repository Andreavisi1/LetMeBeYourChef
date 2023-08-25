package com.example.letmebeyourchef.model.directionModels

import com.squareup.moshi.Json

data class DirectionRouteModel(
    @field:Json(name = "legs")

    var legs: List<DirectionLegModel>? = null,

    @field:Json(name = "overview_polyline")

    var polylineModel: DirectionPolylineModel? = null,

    @field:Json(name = "summary")

    var summary: String? = null
) {

}
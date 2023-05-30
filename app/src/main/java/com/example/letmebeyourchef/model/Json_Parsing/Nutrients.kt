package com.example.letmebeyourchef.model.Json_Parsing

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Nutrients(
    @field:Json(name = "ENERC_KCAL") val chiloCalorie : Double? = 0.0, //Kcal
    @field:Json(name = "PROCNT") val proteine : Double? = 0.0, //g
    @field:Json(name = "FAT") val grassi : Double? = 0.0, // g
    @field:Json(name = "CHOCDF") val carboidrati : Double? = 0.0, //g
) : Parcelable
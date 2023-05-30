package com.example.letmebeyourchef.model.Json_Parsing

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Json_Hint(
    val food: Prodotto?
)
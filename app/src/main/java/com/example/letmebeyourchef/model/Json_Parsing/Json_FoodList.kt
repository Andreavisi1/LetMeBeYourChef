package com.example.letmebeyourchef.model.Json_Parsing


import com.squareup.moshi.JsonClass

//Oggetto esterno che rappresenta la lista di oggetti
//hint chea loro volta contengono oggetti di tipo Prodotto che
//a loro volta contengo ognuno un oggetto Nutrients
@JsonClass(generateAdapter = true)
data class Json_FoodList(
    val hints: List<Json_Hint>?,
    val _links: Json_Links?
)
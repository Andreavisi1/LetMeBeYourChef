package com.example.letmebeyourchef.model.Json_Parsing

import com.squareup.moshi.JsonClass


//Se un attributo non ha il corrispettivo JSON l'app crasha quindi per
//una questione di sicurezza ho reso tutte le variabili nullabili
@JsonClass(generateAdapter = true)
data class Prodotto(
    val brand: String? = "",
    val category: String? = "",
    val categoryLabel: String? = "",
    val foodContentsLabel: String? = "",
    val foodId: String? = "",
    val image: String? = "",
    val knownAs: String? = "",
    val label: String? = "",
    val nutrients: Nutrients? = Nutrients()
){

}
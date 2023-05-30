package com.example.letmebeyourchef.model

import java.time.LocalDate

data class Diario(
    val utente : String,
    val data : String,
    val fabbisogno : Int,
    val grassiTot : Int,
    val proteineTot : Int,
    val carboidratiTot : Int,
    val chiloCalorieEsercizio : Int,
    val chiloCalorieColazione : Int,
    val chiloCaloriePranzo : Int,
    val chiloCalorieCena : Int,
    val chiloCalorieSpuntino : Int,
    val acqua : ArrayList<Boolean>

) { constructor(): this("",LocalDate.now().toString(), 0,0,0,0,0,0,0,0,0, arrayListOf(false,false,false,false,false,false,false,false))
}
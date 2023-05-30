package com.example.letmebeyourchef.model

data class Pasto(
    val calorie : Double,
    val carboidrati : Double,
    val grassi : Double,
    val id : String,
    val image : String,
    val nome : String,
    val proteine : Double,
    val quantita : Double

) { constructor(): this(0.0,0.0,0.0,"","","",0.0,0.0)
}
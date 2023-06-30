package com.example.letmebeyourchef.model

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate
import java.time.Period


@Parcelize
data class Utente(
        var nome: String,
        var cognome: String,
        var email: String,
        var sesso: String,
        var data_nascita: String,
        var dieta: String,
        var intolleranze: String?

) : Parcelable {    constructor(): this("","","","","","",null)

}
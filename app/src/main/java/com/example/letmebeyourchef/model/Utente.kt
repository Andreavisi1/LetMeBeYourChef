package com.example.letmebeyourchef.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Utente(
        var nome: String,
        var cognome: String,
        var email: String,
        var sesso: String,
        var data_nascita: String,
        var intolleranze: ArrayList<String>?

) : Parcelable {    constructor(): this("","","","","",null)

}
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
        var LAF: Double,
        var agonistico: Boolean,
        var sesso: String,
        var data_nascita: String,
        var altezza: Int,
        var peso_attuale: Double,
        var sport: String?,
        var dieta: String

) : Parcelable {    constructor(): this("","","",0.0,false,"","",0,0.0,"","Climatica")

        @RequiresApi(Build.VERSION_CODES.O)
        fun calculateFabbisogno(data_nascita :String, sesso: String, peso_attuale: Double, altezza:Int, LAF:Double) : Int{
                val today = LocalDate.now()
                val birthday: LocalDate = LocalDate.parse(data_nascita)
                val period: Period = Period.between(birthday, today)
                if(sesso == "Uomo")
                        return ((66 + (13.7 * peso_attuale) + (5 * altezza) - (6.8 * period.years)) * LAF).toInt()
                else
                        return ((65 + (9.6 * peso_attuale) + (1.8 * altezza) - (4.7 * period.years)) * LAF).toInt()


        }



}
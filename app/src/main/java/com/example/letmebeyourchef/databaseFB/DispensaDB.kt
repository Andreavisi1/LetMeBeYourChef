package com.example.letmebeyourchef.databaseFB

import com.example.letmebeyourchef.model.Dispensa
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import me.moallemi.tools.daterange.localdate.LocalDateRange
import java.time.LocalDate

class DispensaDB : FirebaseDB() {
    val dispensa_collection = db.collection("Utente").document(auth).collection("Dispensa")
    var status = false

    suspend fun setDispensa(
        utente : String,
        data : String,
        fabbisogno : Int,
        grassiTot : Int,
        proteineTot : Int,
        carboidratiTot : Int,
        chiloCalorieEsercizio : Int,
        chiloCalorieColazione : Int,
        chiloCaloriePranzo : Int,
        chiloCalorieCena : Int,
        chiloCalorieSpuntino : Int,
        acqua : ArrayList<Boolean>
    ): Boolean {
        val dispensa = hashMapOf<String, Any>(
            "utente" to utente,
            "data" to data,
            "fabbisogno" to fabbisogno,
            "grassiTot" to grassiTot,
            "proteineTot" to proteineTot,
            "carboidratiTot" to carboidratiTot,
            "chiloCalorieEsercizio" to chiloCalorieEsercizio,
            "chiloCalorieColazione" to chiloCalorieColazione,
            "chiloCaloriePranzo" to chiloCaloriePranzo,
            "chiloCalorieCena" to chiloCalorieCena,
            "chiloCalorieSpuntino" to chiloCalorieSpuntino,
            "acqua" to acqua
        )
        withContext(Dispatchers.IO) {
            dispensa_collection
                .document(data)
                .set(dispensa)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }

    suspend fun getDispensa(): List<Dispensa>?{
        try{
            return dispensa_collection.get().await().toObjects()
        }catch (e: Exception){
            return null
        }
    }

    suspend fun getUserDispensa(utente: String) : Dispensa? {
        val dispensaList = getDispensa()
        val date = LocalDate.now().toString()
        if(dispensaList != null){
            for(dispensa in dispensaList) {
                if (dispensa.utente == utente && dispensa.data == date)
                    return dispensa
            }
        }
        return null
    }

    suspend fun getStatistiche(inizio: String, fine: String) : ArrayList<Dispensa>{
        val data_inizio = LocalDate.parse(inizio)
        val data_fine = LocalDate.parse(fine)
        val statistiche = ArrayList<Dispensa>()
        var dispensa : Dispensa?
        val range = LocalDateRange(data_inizio,data_fine)
        for(d in range){
            dispensa = dispensa_collection
                .document(d.toString())
                .get().await().toObject<Dispensa>()
            if(dispensa != null)
                statistiche.add(dispensa)

        }

        return statistiche

    }
}
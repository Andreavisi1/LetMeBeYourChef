package com.example.letmebeyourchef.databaseFB

import com.example.letmebeyourchef.model.Json_Parsing.Esercizio
import com.example.letmebeyourchef.model.Pasto
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PreferitiDB : FirebaseDB() {
    val prodotti_collection = db.collection("Utente").document(auth).collection("Preferiti")
    var status = false

    suspend fun setPastoPreferiti(
        utente : String,
        tipologiaPasto : String,
        foodId : String,
        image : String,
        nome/*label*/ : String,
        calorie : Double,
        proteine : Double,
        carboidrati : Double,
        grassi : Double
    ): Boolean {
        val prodotto = hashMapOf<String, Any>(
            "id" to foodId,
            "image" to image,
            "nome" to nome,
            "calorie" to calorie,
            "proteine" to proteine,
            "carboidrati" to carboidrati,
            "grassi" to grassi
        )
        withContext(Dispatchers.IO) {
            prodotti_collection
                .document(utente)
                .collection(tipologiaPasto)
                .document(foodId)
                .set(prodotto)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }

    suspend fun setEsercizioPreferiti(
        utente : String,
        nome/*label*/ : String,
        calorie_ora: Int,
    ): Boolean {
        val esercizio = hashMapOf<String, Any>(
            "nome" to nome,
            "calorieOra" to calorie_ora
        )
        withContext(Dispatchers.IO) {
            prodotti_collection
                .document(utente)
                .collection("ESERCIZIO")
                .document(nome.replace('/',','))
                .set(esercizio)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }

    suspend fun getPastiPreferiti(utente: String, tipologia: String): List<Pasto> {
        return prodotti_collection
            .document(utente)
            .collection(tipologia).get().await().toObjects()

    }
    suspend fun getEserciziPreferiti(utente: String): List<Esercizio> {
        return prodotti_collection
            .document(utente)
            .collection("ESERCIZIO").get().await().toObjects()

    }

    suspend fun deletePreferiti(utente: String,id:String,tipologia: String): Boolean{
        withContext(Dispatchers.IO) {
            prodotti_collection
            .document(utente).collection(tipologia)
            .document(id).delete()
            .addOnSuccessListener {status = true }
            .addOnFailureListener {status = false }
        }.await()
        return status
    }
    suspend fun deleteEserciziPreferiti(utente: String,nome:String): Boolean{
        withContext(Dispatchers.IO) {
            prodotti_collection
                .document(utente).collection("ESERCIZIO")
                .document(nome.replace("/",",")).delete()
                .addOnSuccessListener {status = true }
                .addOnFailureListener {status = false }
        }.await()
        return status
    }
}


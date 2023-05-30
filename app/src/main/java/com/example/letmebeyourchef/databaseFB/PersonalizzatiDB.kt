package com.example.letmebeyourchef.databaseFB

import com.example.letmebeyourchef.model.Json_Parsing.Esercizio
import com.example.letmebeyourchef.model.Pasto
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*

class PersonalizzatiDB : FirebaseDB() {
    val personalizzati_collection = db.collection("Utente").document(auth).collection("Personalizzati")
    var status = false

    suspend fun setPastoPersonalizzati(
        utente : String,
        tipologiaPasto : String,
        nome/*label*/ : String,
        calorie : Double,
        proteine : Double,
        carboidrati : Double,
        grassi : Double
    ): Boolean {
        val id = UUID.randomUUID().toString()
        val prodotto = hashMapOf<String, Any>(
            "id" to id,
            "nome" to nome,
            "calorie" to calorie,
            "proteine" to proteine,
            "carboidrati" to carboidrati,
            "grassi" to grassi
        )
        withContext(Dispatchers.IO) {
            personalizzati_collection
                .document(utente)
                .collection(tipologiaPasto)
                .document(id)
                .set(prodotto)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }
    suspend fun setEsercizioPersonalizzati(
        utente : String,
        nome/*label*/ : String,
        calorieOra : Double,
    ): Boolean {
        val id = UUID.randomUUID().toString()
        val esercizio = hashMapOf<String, Any>(
            "id" to id,
            "nome" to nome,
            "calorieOra" to calorieOra,
        )
        withContext(Dispatchers.IO) {
            personalizzati_collection
                .document(utente)
                .collection("ESERCIZIO")
                .document(id)
                .set(esercizio)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }

    suspend fun getPastiPersonalizzati(utente: String, tipologiaPasto: String): List<Pasto> {
        return personalizzati_collection
            .document(utente)
            .collection(tipologiaPasto).get().await().toObjects()

    }

    suspend fun getEserciziPersonalizzati(utente: String): List<Esercizio> {
        return personalizzati_collection
            .document(utente)
            .collection("ESERCIZIO").get().await().toObjects()

    }

    suspend fun deletePersonalizzato(utente: String,id:String,tipologiaPasto: String): Boolean{
        withContext(Dispatchers.IO) {
            personalizzati_collection
                .document(utente).collection(tipologiaPasto)
                .document(id).delete()
                .addOnSuccessListener {status = true }
                .addOnFailureListener {status = false }
        }.await()
        return status
    }

    suspend fun deleteEsercizioPersonalizzato(utente: String,id:String): Boolean{
        withContext(Dispatchers.IO) {
            personalizzati_collection
                .document(utente).collection("ESERCIZIO")
                .document(id).delete()
                .addOnSuccessListener {status = true }
                .addOnFailureListener {status = false }
        }.await()
        return status
    }

    suspend fun updatePastoPersonalizzato(
        id : String,
        utente : String,
        tipologiaPasto : String,
        nome/*label*/ : String,
        calorie : Double,
        proteine : Double,
        carboidrati : Double,
        grassi : Double
    ): Boolean {
        val prodotto = hashMapOf<String, Any>(
            "id" to id,
            "nome" to nome,
            "calorie" to calorie,
            "proteine" to proteine,
            "carboidrati" to carboidrati,
            "grassi" to grassi
        )
        withContext(Dispatchers.IO) {
            personalizzati_collection
                .document(utente)
                .collection(tipologiaPasto)
                .document(id)
                .set(prodotto)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }

    suspend fun updateEsercizioPersonalizzato(
        id : String,
        utente : String,
        nome/*label*/ : String,
        calorieOra : Double,
    ): Boolean {
        val esercizio = hashMapOf<String, Any>(
            "id" to id,
            "nome" to nome,
            "calorieOra" to calorieOra,
        )
        withContext(Dispatchers.IO) {
            personalizzati_collection
                .document(utente)
                .collection("ESERCIZIO")
                .document(id)
                .set(esercizio)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }


}
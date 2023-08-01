package com.example.letmebeyourchef.databaseFB

import android.util.Log
import com.example.letmebeyourchef.model.Pasto
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ProdottoDB : FirebaseDB(){
    val prodotti_collection = db.collection("Utente").document(auth).collection("Pasti")
    var status = false

    suspend fun setPasto(
        utente : String,
        date : String,
        tipologiaPasto: String,
        foodId : String,
        image : String,
        nome : String,
        calorie : Double,
        proteine : Double,
        carboidrati : Double,
        grassi: Double,
    ): Boolean {
        val prodotto = hashMapOf<String, Any>(
            "id" to foodId,
            "image" to image,
            "nome" to nome,
            "calorie" to calorie,
            "proteine" to proteine,
            "carboidrati" to carboidrati,
            "grassi" to grassi,
        )
        withContext(Dispatchers.IO) {
            prodotti_collection
                .document(date)
                .collection(tipologiaPasto)
                .document(foodId)
                .set(prodotto)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }

    suspend fun getProdotti(date : String, utente : String, tipologiaPasto: String): List<Pasto>?{
        try{
            return prodotti_collection
                .document(date)
                .collection(tipologiaPasto).get().await().toObjects()
        }catch (e: Exception){
            return null
        }
    }

    suspend fun updatePasto(
        utente : String,
        date : String,
        tipologiaPasto: String,
        foodId : String,
    ): Boolean {
        val prodotto = hashMapOf<String, Any>(
        )
        withContext(Dispatchers.IO) {
            prodotti_collection
                .document(date)
                .collection(tipologiaPasto)
                .document(foodId)
                .update(prodotto)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }

    suspend fun deleteProdotti(date : String, utente : String, tipologiaPasto: String, id : String) : Boolean{
        prodotti_collection.document(date)
            .collection(tipologiaPasto).document(id).delete()
            .addOnSuccessListener {status = true}
            .addOnFailureListener { status = false}
            .await()
        return status

    }

}
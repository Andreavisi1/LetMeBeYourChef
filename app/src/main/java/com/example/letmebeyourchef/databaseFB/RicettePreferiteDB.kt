package com.example.letmebeyourchef.databaseFB

import android.util.Log
import com.example.letmebeyourchef.model.Json_Parsing.Esercizio
import com.example.letmebeyourchef.model.Pasto
import com.example.letmebeyourchef.recipeModels.AnalyzedInstruction
import com.example.letmebeyourchef.recipeModels.ExtendedIngredient
import com.example.letmebeyourchef.recipeModels.FavouriteRecipe
import com.example.letmebeyourchef.recipeModels.Recipe
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class RicettePreferiteDB : FirebaseDB() {
    val ricette_preferite_collection = db.collection("Utente").document(auth).collection("Ricette preferite")
    var status = false

    suspend fun setRicettePreferite(
         foodId: Int,
         title: String?,
         sourceName: String?,
         readyInMinutes: Int,
         servings: Int,
         sourceUrl: String?,
         image: String,
         imageType: String?,
         instructions: String?,
         spoonacularSourceUrl: String?,
         utente : String
    ): Boolean {
        val ricetta_preferita = hashMapOf<String, Any>(
            "id" to foodId,
            "image" to image,
            "sourceName" to sourceName!!,
            "title" to title!!,
            "readyInMinutes" to readyInMinutes,
            "servings" to servings,
            "sourceUrl" to sourceUrl!!,
            "imageType" to imageType!!,
            "instructions" to instructions!!,
            "spoonacularSourceUrl" to spoonacularSourceUrl!!,
            "utente" to utente

        )
        withContext(Dispatchers.IO) {
            ricette_preferite_collection
                .document(foodId.toString())
                .set(ricetta_preferita)
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
            ricette_preferite_collection
                .document(utente)
                .collection("ESERCIZIO")
                .document(nome.replace('/',','))
                .set(esercizio)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }

    suspend fun getRicettePreferite(utente: String): List<FavouriteRecipe> {
        return db
            .collection("Utente")
            .document(utente)
            .collection("Ricette preferite")
            .get().await().toObjects()
    }

    suspend fun deleteRicettaPreferita(utente: String,id:String): Boolean{
        withContext(Dispatchers.IO) {
                db
                .collection("Utente")
                .document(utente)
                .collection("Ricette preferite")
                .document(id).delete()
                .addOnSuccessListener {status = true }
                .addOnFailureListener {status = false }
        }.await()
        return status
    }
    suspend fun deleteEserciziPreferiti(utente: String,nome:String): Boolean{
        withContext(Dispatchers.IO) {
            ricette_preferite_collection
                .document(utente).collection("ESERCIZIO")
                .document(nome.replace("/",",")).delete()
                .addOnSuccessListener {status = true }
                .addOnFailureListener {status = false }
        }.await()
        return status
    }
}


package com.example.letmebeyourchef.databaseFB

import com.example.letmebeyourchef.recipeModels.Ingredient
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class IngredientiPreferitiDB : FirebaseDB() {
    val ingredienti_preferiti_collection = db.collection("Utente").document(auth).collection("Ingredienti preferiti")
    var status = false

    suspend fun setIngredientiPreferiti(
         id: Int,
         name: String?,
         image: String?,
         utente : String
    ): Boolean {
        val ingrediente = hashMapOf<String, Any>(
            "id" to id,
            "image" to image!!,
            "name" to name!!,
            "utente" to utente
        )
        withContext(Dispatchers.IO) {
            ingredienti_preferiti_collection
                .document(id.toString())
                .set(ingrediente)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }

    suspend fun getIngredientiPreferiti(utente: String): List<Ingredient> {
        return db
            .collection("Utente")
            .document(utente)
            .collection("Ingredienti preferiti")
            .get().await().toObjects()
    }

    suspend fun deleteIngredientPreferito(utente: String, id: String): Boolean{
        withContext(Dispatchers.IO) {
                db
                .collection("Utente")
                .document(utente)
                .collection("Ingredienti preferiti")
                .document(id).delete()
                .addOnSuccessListener {status = true }
                .addOnFailureListener {status = false }
        }.await()
        return status
    }
}


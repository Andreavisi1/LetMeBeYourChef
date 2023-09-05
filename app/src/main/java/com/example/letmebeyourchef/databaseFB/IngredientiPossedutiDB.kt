package com.example.letmebeyourchef.databaseFB

import com.example.letmebeyourchef.recipeModels.Ingredient
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class IngredientiPossedutiDB : FirebaseDB() {
    val ingredienti_posseduti_collection = db.collection("Utente").document(auth).collection("Ingredienti posseduti")
    var status = false

    suspend fun setIngredienti(
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
            ingredienti_posseduti_collection
                .document(id.toString())
                .set(ingrediente)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }

    suspend fun getIngredientiPosseduti(utente: String): List<Ingredient> {
        return db
            .collection("Utente")
            .document(utente)
            .collection("Ingredienti posseduti")
            .get().await().toObjects()
    }

    suspend fun deleteIngredient(utente: String, id: Int): Boolean{
        withContext(Dispatchers.IO) {
                db
                .collection("Utente")
                .document(utente)
                .collection("Ingredienti posseduti")
                .document(id.toString()).delete()
                .addOnSuccessListener {status = true }
                .addOnFailureListener {status = false }
        }.await()
        return status
    }
}


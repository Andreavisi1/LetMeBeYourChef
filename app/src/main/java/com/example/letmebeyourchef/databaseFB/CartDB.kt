package com.example.letmebeyourchef.databaseFB

import com.example.letmebeyourchef.recipeModels.Ingredient
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class CartDB : FirebaseDB() {
    val cart_collection = db.collection("Utente").document(auth).collection("Cart")
    var status = false

    suspend fun setIngredientToCart(
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
            cart_collection
                .document(id.toString())
                .set(ingrediente)
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
            cart_collection
                .document(utente)
                .collection("ESERCIZIO")
                .document(nome.replace('/',','))
                .set(esercizio)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }

    suspend fun getCart(utente: String): List<Ingredient> {
        return db
            .collection("Utente")
            .document(utente)
            .collection("Cart")
            .get().await().toObjects()
    }

    suspend fun deleteIngredientFromCart(utente: String, id: Int): Boolean{
        withContext(Dispatchers.IO) {
                db
                .collection("Utente")
                .document(utente)
                .collection("Cart")
                .document(id.toString()).delete()
                .addOnSuccessListener {status = true }
                .addOnFailureListener {status = false }
        }.await()
        return status
    }
    suspend fun deleteEserciziPreferiti(utente: String,nome:String): Boolean{
        withContext(Dispatchers.IO) {
            cart_collection
                .document(utente).collection("ESERCIZIO")
                .document(nome.replace("/",",")).delete()
                .addOnSuccessListener {status = true }
                .addOnFailureListener {status = false }
        }.await()
        return status
    }
}


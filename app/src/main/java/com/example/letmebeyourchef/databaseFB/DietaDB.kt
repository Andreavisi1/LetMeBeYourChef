package com.example.letmebeyourchef.databaseFB

import com.example.letmebeyourchef.model.Dieta
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.tasks.await

class DietaDB : FirebaseDB() {
    private val diete_collection = db.collection("Diete")

    suspend fun getDiete(): List<Dieta> {
        return diete_collection.get().await().toObjects()
    }

    suspend fun getDieta(titolo: String) : Dieta {
      return  diete_collection.document(titolo).get().await().toObject<Dieta>()!!
    }
}


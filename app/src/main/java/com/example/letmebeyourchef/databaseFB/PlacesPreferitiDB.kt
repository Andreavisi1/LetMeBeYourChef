package com.example.letmebeyourchef.databaseFB

import com.example.letmebeyourchef.model.SavedPlaceModel
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PlacesPreferitiDB : FirebaseDB() {
    val places_preferiti_collection = db.collection("Utente").document(auth).collection("Places preferiti")
    var status = false

    suspend fun setPlacesPreferiti(
        name:String="",
        address:String="",
        placeId:String="",
        totalRating:Int=0,
        rating:Double=0.0,
        lat:Double=0.0,
        lng:Double=0.0,
        utente : String
    ): Boolean {
        val place = hashMapOf<String, Any>(
            "name" to name,
            "address" to address!!,
            "placeId" to placeId!!,
            "totalRating" to totalRating,
            "rating" to rating!!,
            "lat" to lat!!,
            "lng" to lng!!,
            "utente" to utente
        )
        withContext(Dispatchers.IO) {
            places_preferiti_collection
                .document(name.toString())
                .set(place)
                .addOnSuccessListener { status = true }
                .addOnFailureListener { status = false }
        }.await()
        return status
    }

    suspend fun getPlacesPreferiti(utente: String): List<SavedPlaceModel> {
        return db
            .collection("Utente")
            .document(utente)
            .collection("Places preferiti")
            .get().await().toObjects()
    }

    suspend fun deletePlacePreferito(utente: String, id: String): Boolean{
        withContext(Dispatchers.IO) {
                db
                .collection("Utente")
                .document(utente)
                .collection("Places preferiti")
                .document(id.toString()).delete()
                .addOnSuccessListener {status = true }
                .addOnFailureListener {status = false }
        }.await()
        return status
    }
}


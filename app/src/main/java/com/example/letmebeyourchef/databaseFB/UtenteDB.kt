package com.example.letmebeyourchef.databaseFB

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.letmebeyourchef.model.Utente
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await


class UtenteDB : FirebaseDB() {
    // Riferimento alla collection Utente

    val utenti_collection = db.collection("Utente")
    var status = false

    suspend fun addUtente(
        nome: String,
        cognome : String,
        email: String,
        LAF: Double,
        agonistico: Boolean,
        sesso: String,
        data_nascita: String,
        altezza: Int,
        peso_attuale: Double,
        sport : String?,
        contesto: Context,
        dieta : String = "Climatica" ): Boolean {
        val utente = hashMapOf<String, Any>(
            "nome" to nome,
            "cognome" to cognome,
            "email" to email,
            "LAF" to LAF,
            "agonistico" to agonistico,
            "sesso" to sesso,
            "data_nascita" to data_nascita,
            "altezza" to altezza,
            "peso_attuale" to peso_attuale,
            "sport" to sport!!,
            "dieta" to dieta
        )

        withContext(Dispatchers.IO){
            utenti_collection
                .document(email)
                .set(utente)
                .addOnSuccessListener {
                    Toast.makeText(contesto, "Operazione completata con successo!", Toast.LENGTH_SHORT).show()
                    status = true

                }
                .addOnFailureListener{
                    Toast.makeText(contesto, "Qualcosa è andato storto...", Toast.LENGTH_SHORT).show()
                    status = false
                }
                .await()
            }
        return status
    }

    suspend fun getUtenti(): List<Utente>{
        return utenti_collection.get().await().toObjects()
    }

    suspend fun getUtente(email: String) : Utente {
        val utentiList = getUtenti()
        for(utente in utentiList) {
            if (utente.email == email) {
                Log.d("Utente",utente.toString())
                return utente
            }
        }
        return Utente()
    }

    suspend fun updateUtente(
        nome: String,
        cognome : String,
        email: String,
        LAF: Double,
        agonistico: Boolean,
        sesso: String,
        data_nascita: String,
        altezza: Int,
        peso_attuale: Double,
        sport: String?,
        contesto: Context): Boolean {
        val utente = hashMapOf<String, Any>(
            "nome" to nome,
            "cognome" to cognome,
            "email" to email,
            "LAF" to LAF,
            "agonistico" to agonistico,
            "sesso" to sesso,
            "data_nascita" to data_nascita,
            "altezza" to altezza,
            "peso_attuale" to peso_attuale,
            "sport" to sport!!
        )

        withContext(Dispatchers.IO){
            utenti_collection
                .document(email)
                .update(utente)
                .addOnSuccessListener {
                    Toast.makeText(contesto, "Operazione completata con successo!", Toast.LENGTH_SHORT).show()
                    status = true

                }
                .addOnFailureListener{
                    Toast.makeText(contesto, "Qualcosa è andato storto...", Toast.LENGTH_SHORT).show()
                    status = false
                }
                .await()
        }
        return status
    }

    suspend fun updateDieta(titolo: String, email: String):Boolean{
        val dieta = hashMapOf<String,Any>(
            "dieta" to titolo
        )

        withContext(Dispatchers.IO){
                utenti_collection
                    .document(email)
                    .update(dieta)
                    .addOnSuccessListener {
                        status = true

                    }
                    .addOnFailureListener{
                        status = false
                    }
                    .await()
        }
        return status

    }
}
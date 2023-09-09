package com.example.letmebeyourchef.databaseFB

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.letmebeyourchef.model.Utente
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class UtenteDB : FirebaseDB() {
    // Riferimento alla collection Utente

    val utenti_collection = db.collection("Utente")
    var status = false
    suspend fun addUtente(
        nome: String,
        cognome: String,
        email: String,
        sesso: String,
        data_nascita: String,
        intolleranze: ArrayList<String>?,
        contesto: Context, ): Boolean {
        val utente = hashMapOf<String, Any>(
            "nome" to nome,
            "cognome" to cognome,
            "email" to email,
            "sesso" to sesso,
            "data_nascita" to data_nascita,
            "intolleranze" to intolleranze!!,
        )
        withContext(Dispatchers.IO){
            utenti_collection
                .document(email)
                .set(utente)
                .addOnSuccessListener {
                    Toast.makeText(contesto, "Operation  completed successfully! ", Toast.LENGTH_LONG).show()
                    status = true
                }
                .addOnFailureListener{
                    Toast.makeText(contesto, "Something went wrong... Retry or try to sign in", Toast.LENGTH_LONG).show()
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
        sesso: String,
        data_nascita: String,
        intolleranze: ArrayList<String>?,
        contesto: Context): Boolean {
        val utente = hashMapOf<String, Any>(
            "nome" to nome,
            "cognome" to cognome,
            "email" to email,
            "sesso" to sesso,
            "data_nascita" to data_nascita,
            "intolleranze" to intolleranze!!
        )

        withContext(Dispatchers.IO){
            utenti_collection
                .document(email)
                .update(utente)
                .addOnSuccessListener {
                    Toast.makeText(contesto, "Operation  completed successfully!", Toast.LENGTH_SHORT).show()
                    status = true

                }
                .addOnFailureListener{
                    Toast.makeText(contesto, "Something went wrong...", Toast.LENGTH_SHORT).show()
                    status = false
                }
                .await()
        }
        return status
    }

}

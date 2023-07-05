package com.example.letmebeyourchef.autenticazione

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.letmebeyourchef.databaseFB.UtenteDB
import com.example.letmebeyourchef.model.Utente
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class AuthViewModel : ViewModel() {
    private var auth: FirebaseAuth = Firebase.auth
    private val utenteDB = UtenteDB()
    private var _utente = MutableLiveData(Utente())
    val utente: LiveData<Utente>
        get() = _utente

    suspend fun signUp(email: String, password: String): FirebaseUser? {
        return try {
            val response = auth.createUserWithEmailAndPassword(email, password).await()
            response.user
        } catch (e: Exception) {
            null
        }
    }

    suspend fun signIn(email: String, password: String): FirebaseUser? {
        return try {
            val signin = auth.signInWithEmailAndPassword(email, password).await()
            signin.user
        } catch (e: Exception) {
            null
        }
    }


    suspend fun addAuthUtenteOnDB(nome:String, cognome:String, email:String,
                                  sesso:String, data_nascita:String,
                                  intolleranze:String?,contesto: Context) {
        try {
            val user = auth.currentUser
            val profileUpdates = userProfileChangeRequest {
                displayName = nome + ' ' + cognome
            }
            user!!.updateProfile(profileUpdates)
            utenteDB.addUtente(nome, cognome, email, sesso, data_nascita, intolleranze, contesto)

        } catch (e: Exception) {
        }
    }

    fun checkUtenteisLoggato() : Boolean{
        if(auth.currentUser != null)
            return true
        return false
    }

    fun resetPassword(email : String) : Task<Void>{
        return auth.sendPasswordResetEmail(email)
    }
}

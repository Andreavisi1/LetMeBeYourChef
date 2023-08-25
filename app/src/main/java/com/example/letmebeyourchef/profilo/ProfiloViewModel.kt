package com.example.letmebeyourchef.profilo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.UtenteDB
import com.example.letmebeyourchef.model.Utente
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class ProfiloViewModel : ViewModel() {

    private val utenteDB = UtenteDB()
    val db = Firebase.firestore

    private var _profilo = MutableLiveData<Utente>()

    val profilo: LiveData<Utente>
        get() = _profilo

    val _dispensaUpdated = MutableLiveData<Boolean>()
    val dispensaUpdated: LiveData<Boolean>
        get() = _dispensaUpdated

    private val auth = FirebaseAuth.getInstance()

    fun changePassword(email: String): Task<Void> {
        return auth.sendPasswordResetEmail(email)
    }

    fun getDataProfilo() {
        viewModelScope.launch {
            _profilo.value = utenteDB.getUtente(auth.currentUser!!.email!!)
        }
    }

    fun updateAuthUtenteOnDB(
        nome: String, cognome: String, email: String,
        sesso: String, data_nascita: String,
        intolleranze: ArrayList<String>?, contesto: Context
    ) {
        try {
            val user = auth.currentUser
            val profileUpdates = userProfileChangeRequest {
                displayName = "$nome $cognome"
            }
            user!!.updateProfile(profileUpdates)



            viewModelScope.launch {
                val docRef = db.collection("Utente").document("email")

                // Remove the 'intolleranze' field from the document
                val updates = hashMapOf<String, Any>(
                    "intolleranze" to FieldValue.delete(),
                )

                docRef.update(updates)

                utenteDB.updateUtente(
                    nome, cognome, email, sesso, data_nascita,
                    intolleranze!!, contesto
                )

            }
            } catch (e: Exception) {
            }
        }
    }

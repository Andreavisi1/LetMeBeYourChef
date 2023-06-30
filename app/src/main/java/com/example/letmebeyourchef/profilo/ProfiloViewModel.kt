package com.example.letmebeyourchef.profilo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.DiarioDB
import com.example.letmebeyourchef.databaseFB.UtenteDB
import com.example.letmebeyourchef.model.Utente
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period

class ProfiloViewModel : ViewModel() {

    private val utenteDB = UtenteDB()
    private val diarioDB = DiarioDB()

    private var _profilo = MutableLiveData<Utente>()

    val profilo: LiveData<Utente>
        get() = _profilo

    val _diarioUpdated = MutableLiveData<Boolean>()
    val diarioUpdated: LiveData<Boolean>
        get() = _diarioUpdated

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
        intolleranze: String?, contesto: Context
    ) {
        try {
            val user = auth.currentUser
            val profileUpdates = userProfileChangeRequest {
                displayName = "$nome $cognome"
            }
            user!!.updateProfile(profileUpdates)
            viewModelScope.launch {
                utenteDB.updateUtente(
                    nome, cognome, email, sesso, data_nascita,
                    intolleranze, contesto
                )
                val diario = diarioDB.getUserDiario(auth.currentUser!!.email!!)!!
                /*diarioDB.setDiario(auth.currentUser!!.email!!, LocalDate.now().toString(),fabbisogno.toInt(),diario.grassiTot,
                diario.proteineTot,diario.carboidratiTot,diario.chiloCalorieEsercizio,diario.chiloCalorieColazione,
                diario.chiloCaloriePranzo,diario.chiloCalorieCena,diario.chiloCalorieSpuntino,diario.acqua)
                _diarioUpdated.value = true*/
            }
            } catch (e: Exception) {
            }
        }
    }

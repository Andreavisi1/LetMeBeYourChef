package com.example.letmebeyourchef.homepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.UtenteDB
import com.example.letmebeyourchef.model.Utente
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class HomepageViewModel : ViewModel() {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val utenteDB = UtenteDB()

    private var _utente = MutableLiveData(Utente())
    val utente: LiveData<Utente>
        get() = _utente


    fun getUtente() {
        viewModelScope.launch {
            _utente.value = utenteDB.getUtente(auth.currentUser!!.email!!)
        }

    }

    fun logOut() {
        auth.signOut()
    }

}
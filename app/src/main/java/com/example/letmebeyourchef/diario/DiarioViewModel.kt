package com.example.letmebeyourchef.diario

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.*
import com.example.letmebeyourchef.model.Diario
import com.example.letmebeyourchef.model.Json_Parsing.Esercizio
import com.example.letmebeyourchef.model.Pasto
import com.example.letmebeyourchef.model.Utente
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period
import kotlin.collections.ArrayList

class DiarioViewModel : ViewModel() {

    private val diarioDB = DiarioDB()
    private val utenteDB = UtenteDB()
    private val dietaDB = DietaDB()
    private val prodottoDB = ProdottoDB()
    private val esercizioDB = EsercizioDB()
    private val auth = FirebaseAuth.getInstance()

    private var _carboidratiMax = MutableLiveData<Int>()
    val carboidratiMax : LiveData<Int>
        get() = _carboidratiMax
    private var _proteineMax = MutableLiveData<Int>()
    val proteineMax : LiveData<Int>
        get() = _proteineMax
    private var _grassiMax = MutableLiveData<Int>()
    val grassiMax : LiveData<Int>
        get() = _grassiMax


    private var _diario = MutableLiveData<Diario>()
    val diario: LiveData<Diario>
        get() = _diario

    private var _acqua = MutableLiveData<String>()

    val acqua : LiveData<String>
        get() = _acqua

    private var _assunte = MutableLiveData<String>()
    val assunte : LiveData<String>
        get() = _assunte

    private var _rimanenti = MutableLiveData<String>()
    val rimanenti : LiveData<String>
        get() = _rimanenti

    val result : LiveData<String> = Transformations.map(rimanenti){
        x -> if (x.toInt() < 0) "Nessuna" else x
    }

    private var _selezionati = MutableLiveData<List<Pasto>>()
    val selezionati : LiveData<List<Pasto>>
        get() = _selezionati

    private var _eserciziSelezionati = MutableLiveData<List<Esercizio>>()
    val esercizioSelezionati : LiveData<List<Esercizio>>
        get() = _eserciziSelezionati

    private var _diarioSettato = MutableLiveData<Boolean>()
    val diarioSettato : LiveData<Boolean>
        get() = _diarioSettato



   fun getUserDiarioDB(){
       viewModelScope.launch {
           _diario.value = diarioDB.getUserDiario(auth.currentUser?.email!!)
       }

    }

    //Variabile usata per la visualizzazione, altrimenti avrei visualizzato solamente il numero
    fun setAcqua(acqua : Double){
        var water = acqua.toString() + " L"
        _acqua.value = water
    }

    fun setAssunte(){
        _diarioSettato.value = false
        val assunte = diario.value!!.chiloCalorieCena + diario.value!!.chiloCalorieColazione + diario.value!!.chiloCaloriePranzo + diario.value!!.chiloCalorieSpuntino
        _assunte.value = assunte.toString()
        _diarioSettato.value = !(_diarioSettato.value)!!
    }

    fun setRimanenti(){
        val rimanenti = diario.value!!.fabbisogno - assunte.value!!.toInt()
        _rimanenti.value = rimanenti.toString()
        _diarioSettato.value = !(_diarioSettato.value)!!


    }

    fun setMacro(){
        viewModelScope.launch {
            val utente = utenteDB.getUtente(auth.currentUser?.email!!)
            val dieta = dietaDB.getDieta(utente.dieta)
            _carboidratiMax.value = ((diario.value!!.fabbisogno*(dieta.perc_carb.toDouble()/100.0)) / 4).toInt() //1gr di carbo = 4Kcal
            _proteineMax.value = ((diario.value!!.fabbisogno*(dieta.perc_prot.toDouble()/100.0)) / 4).toInt() //1gr di prot = 4Kcal
            _grassiMax.value = ((diario.value!!.fabbisogno*(dieta.perc_prot.toDouble()/100.0)) / 9).toInt()//1gr di grassi = 9Kcal
            _diarioSettato.value = !(_diarioSettato.value)!!
        }
    }


    fun getItemSelezionati(pasto: String){
        viewModelScope.launch {
            _selezionati.value = prodottoDB.getProdotti(LocalDate.now().toString(),auth.currentUser!!.email.toString(),pasto)
        }
    }
    fun getEsercizioSelezionati(){
        viewModelScope.launch {
            _eserciziSelezionati.value = esercizioDB.getEsercizi(LocalDate.now().toString())
        }
    }

}

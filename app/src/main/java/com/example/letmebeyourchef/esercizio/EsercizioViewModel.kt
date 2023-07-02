package com.example.letmebeyourchef.esercizio

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.DispensaDB
import com.example.letmebeyourchef.databaseFB.EsercizioDB
import com.example.letmebeyourchef.databaseFB.PreferitiDB
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.time.LocalDate

class EsercizioViewModel : ViewModel(){

    private val esercizioDB = EsercizioDB()
    private val preferitiDB = PreferitiDB()
    private val dispensaDB = DispensaDB()
    private val auth = FirebaseAuth.getInstance()

    private var _dispensaChanged = MutableLiveData<Boolean>()
    val dispensaChanged : LiveData<Boolean>
        get() = _dispensaChanged

    fun setEsercizioOnDB(nome: String/*label*/, calorieOra: Int, durata: Int, context: Context) {
        viewModelScope.launch {
            if (esercizioDB.setEsercizio(auth.currentUser?.email!!, LocalDate.now().toString(), nome/*label*/,calorieOra, durata)) {
                Toast.makeText(context, "$nome svolto per $durata minuti aggiunto al tuo Diario", Toast.LENGTH_LONG).show()
                setChiloCalorie()
            } else
                Toast.makeText(context, "Aggiunta esercizio al Diario fallita", Toast.LENGTH_LONG).show()
        }
    }



    fun setEsercizioPreferitiOnDB(nome: String/*label*/, calorieOra: Int, context: Context)
    {
        viewModelScope.launch {
            if (preferitiDB.setEsercizioPreferiti(auth.currentUser?.email!!, nome/*label*/, calorieOra)) {
                Toast.makeText(context, "$nome aggiunto/i ai Preferiti", Toast.LENGTH_LONG).show()
                setChiloCalorie()
            } else
                Toast.makeText(context, "Aggiunta esercizip ai Preferiti fallita", Toast.LENGTH_LONG).show()
        }
    }
    fun deleteEsercizio(nome : String, context : Context){
        viewModelScope.launch {
            if(esercizioDB.deleteEsercizio(LocalDate.now().toString(), nome)){
                Toast.makeText(context,"Esercizio eliminato con successo",Toast.LENGTH_LONG).show()
                setChiloCalorie()
            }
            else
                Toast.makeText(context,"ATTENZIONE, eliminazione esercizio fallita",Toast.LENGTH_LONG).show()
        }
    }

    fun updateEsercizio(nome: String, durata:Int, context: Context){
        viewModelScope.launch {
            if(esercizioDB.updateEsercizio(auth.currentUser!!.email!!,LocalDate.now().toString(), nome, durata)){
                Toast.makeText(context,"Esercizio modificato con successo",Toast.LENGTH_LONG).show()
                setChiloCalorie()
            }
            else
                Toast.makeText(context,"ATTENZIONE, eliminazione esercizio fallita",Toast.LENGTH_LONG).show()
        }
    }

    private fun setChiloCalorie() {
        viewModelScope.launch {
            val dispensa = dispensaDB.getUserDispensa(auth.currentUser!!.email!!)
            val arrayEsercizi = esercizioDB.getEsercizi(LocalDate.now().toString())
            var calorie_esercizio = 0.0
            if (arrayEsercizi != null) {
                if (arrayEsercizi.isNotEmpty()) {
                    for (esercizio in arrayEsercizi){
                        Log.d("esercizio",calorie_esercizio.toString())
                        calorie_esercizio += esercizio.calorieOra * (esercizio.durata/60.0)
                    }
                }
            }
            dispensaDB.setDispensa( auth.currentUser?.email!!, LocalDate.now().toString(), dispensa!!.fabbisogno,
                dispensa.grassiTot,  dispensa.proteineTot,  dispensa.carboidratiTot, calorie_esercizio.toInt(),
                dispensa.chiloCalorieColazione, dispensa.chiloCaloriePranzo, dispensa.chiloCalorieCena,dispensa.chiloCalorieSpuntino
                , dispensa.acqua)
            _dispensaChanged.value = true
        }
    }

}
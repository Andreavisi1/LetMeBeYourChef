package com.example.letmebeyourchef.pasto

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.DiarioDB
import com.example.letmebeyourchef.databaseFB.ProdottoDB
import com.example.letmebeyourchef.model.Pasto
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

import java.time.LocalDate

class PastoViewModel : ViewModel() {

    private val prodottoDB = ProdottoDB()
    private val diarioDB = DiarioDB()
    private var hashMapCalorie = HashMap<String, Int>()
    private var hashMapMacro = HashMap<String, Int>()

    private var _diarioChanged = MutableLiveData<Boolean>()
    val diarioChanged : LiveData<Boolean>
        get() = _diarioChanged

    private val auth = FirebaseAuth.getInstance()

    fun deletePasto(tipologiaPasto : String, id : String, context : Context){
        viewModelScope.launch {
            if(prodottoDB.deleteProdotti(LocalDate.now().toString(),auth.currentUser!!.email!!, tipologiaPasto,id)){
                Toast.makeText(context,"Prodotto eliminato con successo",Toast.LENGTH_LONG).show()
                setChiloCalorie()
            }
            else
                Toast.makeText(context,"ATTENZIONE, eliminazione prodotto fallita",Toast.LENGTH_LONG).show()
        }
    }

    fun updatePasto(tipologiaPasto: String, id:String,quantita : Double, context: Context){
        viewModelScope.launch {
            if(prodottoDB.updatePasto(auth.currentUser!!.email!!,LocalDate.now().toString(), tipologiaPasto,id,quantita)){
                Toast.makeText(context,"Prodotto modificato con successo",Toast.LENGTH_LONG).show()
                setChiloCalorie()
            }
            else
                Toast.makeText(context,"ATTENZIONE, eliminazione prodotto fallita",Toast.LENGTH_LONG).show()
        }
    }

    private fun setChiloCalorie() {
        viewModelScope.launch {
            val diario = diarioDB.getUserDiario(auth.currentUser!!.email!!)
            Log.d("Pasto", diario!!.utente.toString())
            val tipologiaPasto = arrayListOf<String>("COLAZIONE", "PRANZO", "CENA", "SPUNTINO")
            val macroNutrienti = arrayListOf<String>("proteine", "carboidrati", "grassi")
            for (pasto in tipologiaPasto)
                hashMapCalorie.put(pasto, 0)
            for(macro in macroNutrienti)
                hashMapMacro.put(macro,0)
            Log.d("Pasto", hashMapCalorie.toString())
            for (pasto in tipologiaPasto) {
                val arrayProdotti = prodottoDB.getProdotti(LocalDate.now().toString(), auth.currentUser?.email!!, pasto)
                setMacroDiario(arrayProdotti, pasto)
            }
            Log.d("Diario", hashMapCalorie.toString())
            diarioDB.setDiario(
                auth.currentUser?.email!!, LocalDate.now().toString(), diario.fabbisogno,
                hashMapMacro["grassi"]!!,  hashMapMacro["proteine"]!!,  hashMapMacro["carboidrati"]!!,
                diario.chiloCalorieEsercizio, hashMapCalorie["COLAZIONE"]!!,
                hashMapCalorie["PRANZO"]!!, hashMapCalorie["CENA"]!!,
                hashMapCalorie["SPUNTINO"]!!, diario.acqua
            )
            _diarioChanged.value = true
        }
    }


    private fun setMacroDiario(arrayProdotti: List<Pasto>?, pasto: String) {
        if (arrayProdotti != null)
            if (arrayProdotti.isNotEmpty()) {
                var calorie = 0.0
                var proteine = 0.0
                var carboidrati = 0.0
                var grassi = 0.0
                for (prodotto in arrayProdotti) {
                    calorie += prodotto.calorie * prodotto.quantita
                    proteine += prodotto.proteine * prodotto.quantita
                    carboidrati += prodotto.carboidrati * prodotto.quantita
                    grassi += prodotto.grassi * prodotto.quantita

                }
                hashMapCalorie.put(pasto, calorie.toInt())
                hashMapMacro.put("proteine", (hashMapMacro.get("proteine")!! + proteine).toInt())
                hashMapMacro.put("carboidrati", (hashMapMacro.get("carboidrati")!! + carboidrati).toInt())
                hashMapMacro.put("grassi", (hashMapMacro.get("grassi")!! + grassi).toInt())

            }
    }


}
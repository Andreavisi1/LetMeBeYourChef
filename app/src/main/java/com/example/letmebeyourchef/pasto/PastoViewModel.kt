package com.example.letmebeyourchef.pasto

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.DispensaDB
import com.example.letmebeyourchef.databaseFB.ProdottoDB
import com.example.letmebeyourchef.model.Pasto
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

import java.time.LocalDate

class PastoViewModel : ViewModel() {

    private val prodottoDB = ProdottoDB()
    private val dispensaDB = DispensaDB()
    private var hashMapCalorie = HashMap<String, Int>()
    private var hashMapMacro = HashMap<String, Int>()

    private var _dispensaChanged = MutableLiveData<Boolean>()
    val dispensaChanged : LiveData<Boolean>
        get() = _dispensaChanged

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

    fun updatePasto(tipologiaPasto: String, id:String, context: Context){
        viewModelScope.launch {
            if(prodottoDB.updatePasto(auth.currentUser!!.email!!,LocalDate.now().toString(), tipologiaPasto,id)){
                Toast.makeText(context,"Prodotto modificato con successo",Toast.LENGTH_LONG).show()
                setChiloCalorie()
            }
            else
                Toast.makeText(context,"ATTENZIONE, eliminazione prodotto fallita",Toast.LENGTH_LONG).show()
        }
    }

    private fun setChiloCalorie() {
        viewModelScope.launch {
            val dispensa = dispensaDB.getUserDispensa(auth.currentUser!!.email!!)
            Log.d("Pasto", dispensa!!.utente.toString())
            val tipologiaPasto = arrayListOf<String>("COLAZIONE", "PRANZO", "CENA", "SPUNTINO")
            val macroNutrienti = arrayListOf<String>("proteine", "carboidrati", "grassi")
            for (pasto in tipologiaPasto)
                hashMapCalorie.put(pasto, 0)
            for(macro in macroNutrienti)
                hashMapMacro.put(macro,0)
            Log.d("Pasto", hashMapCalorie.toString())
            for (pasto in tipologiaPasto) {
                val arrayProdotti = prodottoDB.getProdotti(LocalDate.now().toString(), auth.currentUser?.email!!, pasto)
                setMacroDispensa(arrayProdotti, pasto)
            }
            Log.d("Dispensa", hashMapCalorie.toString())
            dispensaDB.setDispensa(
                auth.currentUser?.email!!, LocalDate.now().toString(), dispensa.fabbisogno,
                hashMapMacro["grassi"]!!,  hashMapMacro["proteine"]!!,  hashMapMacro["carboidrati"]!!,
                dispensa.chiloCalorieEsercizio, hashMapCalorie["COLAZIONE"]!!,
                hashMapCalorie["PRANZO"]!!, hashMapCalorie["CENA"]!!,
                hashMapCalorie["SPUNTINO"]!!, dispensa.acqua
            )
            _dispensaChanged.value = true
        }
    }


    private fun setMacroDispensa(arrayProdotti: List<Pasto>?, pasto: String) {
        if (arrayProdotti != null)
            if (arrayProdotti.isNotEmpty()) {
                var calorie = 0.0
                var proteine = 0.0
                var carboidrati = 0.0
                var grassi = 0.0
                for (prodotto in arrayProdotti) {
                    calorie += prodotto.calorie
                    proteine += prodotto.proteine
                    carboidrati += prodotto.carboidrati
                    grassi += prodotto.grassi

                }
                hashMapCalorie.put(pasto, calorie.toInt())
                hashMapMacro.put("proteine", (hashMapMacro.get("proteine")!! + proteine).toInt())
                hashMapMacro.put("carboidrati", (hashMapMacro.get("carboidrati")!! + carboidrati).toInt())
                hashMapMacro.put("grassi", (hashMapMacro.get("grassi")!! + grassi).toInt())

            }
    }


}
package com.example.letmebeyourchef.prodotto

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.DispensaDB
import com.example.letmebeyourchef.databaseFB.PreferitiDB
import com.example.letmebeyourchef.databaseFB.ProdottoDB
import com.example.letmebeyourchef.model.Pasto
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.time.LocalDate

class ProdottoViewModel : ViewModel() {

    private val prodottoDB = ProdottoDB()
    private val preferitiDB = PreferitiDB()
    private val dispensaDB = DispensaDB()
    private val auth = FirebaseAuth.getInstance()
    private var hashMapCalorie = HashMap<String, Int>()
    private var hashMapMacro = HashMap<String, Int>()


    fun setPastoOnDB(
        tipologiaPasto: String, foodId: String, image: String, nome: String/*label*/, calorie: Double, proteine: Double,
        carboidrati: Double, grassi: Double, context: Context
    ) {
        viewModelScope.launch {
            if (prodottoDB.setPasto(
                    auth.currentUser?.email!!,
                    LocalDate.now().toString(), tipologiaPasto, foodId, image, nome/*label*/, calorie, proteine,
                    carboidrati, grassi,
                )
            ) {
                Toast.makeText(context, "$nome added to your storage", Toast.LENGTH_LONG).show()
                setChiloCalorie()
            } else
                Toast.makeText(context, "Adding ingredient to storage failed", Toast.LENGTH_LONG).show()
        }
    }

    fun setPastoPreferitiOnDB(tipologiaPasto: String, foodId: String, image: String, nome: String/*label*/,
                              calorie: Double, proteine: Double, carboidrati: Double, grassi: Double, context: Context)
    {
        viewModelScope.launch {
            if (preferitiDB.setPastoPreferiti(auth.currentUser?.email!!, tipologiaPasto,
                                            foodId, image,  nome/*label*/, calorie, proteine,  carboidrati, grassi
                                            )
            ) {
                Toast.makeText(context, "$nome added to favourites", Toast.LENGTH_LONG).show()
                setChiloCalorie()
            } else
                Toast.makeText(context, "Adding ingredient to favourites failed", Toast.LENGTH_LONG).show()
        }
    }

    private fun setChiloCalorie() {
        viewModelScope.launch {
            val dispensa = dispensaDB.getUserDispensa(auth.currentUser!!.email!!)
            val tipologiaPasto = arrayListOf<String>("COLAZIONE", "PRANZO", "CENA", "SPUNTINO")
            val macroNutrienti = arrayListOf<String>("proteine", "carboidrati", "grassi")
            for (pasto in tipologiaPasto)
                hashMapCalorie.put(pasto, 0)
            for(macro in macroNutrienti)
                hashMapMacro.put(macro,0)
            for (pasto in tipologiaPasto) {
                val arrayProdotti = prodottoDB.getProdotti(LocalDate.now().toString(), auth.currentUser?.email!!, pasto)
                setMacroDispensa(arrayProdotti, pasto)
            }
            Log.d("Pasto", hashMapCalorie.toString())
            if (dispensa != null) {
                dispensaDB.setDispensa(
                    auth.currentUser?.email!!, LocalDate.now().toString(), dispensa.fabbisogno,
                    hashMapMacro["grassi"]!!,  hashMapMacro["proteine"]!!,  hashMapMacro["carboidrati"]!!,
                    dispensa.chiloCalorieEsercizio, hashMapCalorie["COLAZIONE"]!!,
                    hashMapCalorie["PRANZO"]!!, hashMapCalorie["CENA"]!!,
                    hashMapCalorie["SPUNTINO"]!!, dispensa.acqua
                )
            }
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

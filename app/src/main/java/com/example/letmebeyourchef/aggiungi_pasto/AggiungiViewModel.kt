package com.example.letmebeyourchef.aggiungi_pasto

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.DiarioDB
import com.example.letmebeyourchef.databaseFB.PersonalizzatiDB
import com.example.letmebeyourchef.databaseFB.PreferitiDB
import com.example.letmebeyourchef.databaseFB.ProdottoDB
import com.example.letmebeyourchef.model.Json_Parsing.Json_FoodList
import com.example.letmebeyourchef.model.Json_Parsing.Prodotto
import com.example.letmebeyourchef.model.Pasto
import com.example.letmebeyourchef.retrofit.RetrofitInstance
import com.example.letmebeyourchef.utils.APICredentials
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.InputStreamReader
import java.time.LocalDate


class AggiungiViewModel : ViewModel() {

    private val preferitiDB = PreferitiDB()
    private val prodottoDB = ProdottoDB()
    private val personalizzatiDB = PersonalizzatiDB()
    private val diarioDB = DiarioDB()
    private val auth = FirebaseAuth.getInstance()
    private var hashMapCalorie = HashMap<String, Int>()
    private var hashMapMacro = HashMap<String, Int>()

    private var _foodLiveData = MutableLiveData<ArrayList<Prodotto>>()

    val foodLiveData : LiveData<ArrayList<Prodotto>>
        get() = _foodLiveData

    private var _preferitiLiveData = MutableLiveData<List<Pasto>>()
    val preferitiLiveData: LiveData<List<Pasto>>
        get() = _preferitiLiveData

    private var _personalizzatiLiveData = MutableLiveData<List<Pasto>>()
    val personalizzatiLiveData: LiveData<List<Pasto>>
        get() = _personalizzatiLiveData



    private var _ingredientiLiveData = MutableLiveData<List<String>>()
    val ingredientiLiveData: MutableLiveData<List<String>>
        get() = _ingredientiLiveData




    fun getFoodFromNameorUPC(ingr : String, upc : String, context: Context) {





        /*val inputStream = context.assets.open("ingredienti.txt")
        val reader = BufferedReader(InputStreamReader(inputStream))
        val ingredienti = mutableListOf<String>()
        var line: String? = reader.readLine()
        while (line != null) {
            val parts = line.split(";")
            if (parts.size == 2) {
                val nome = parts[0]
                ingredienti.add(nome)
            }
            line = reader.readLine()
        }
        reader.close()

        _ingredientiLiveData.postValue(ingredienti)*/





        RetrofitInstance.api.getFoodFromNameOrUPC(APICredentials.API_ID,APICredentials.API_KEY,ingr,upc)
            .enqueue(object : Callback<Json_FoodList> {
                override fun onResponse(call: Call<Json_FoodList>, response: Response<Json_FoodList>) {
                    if (response.body() != null && response.body()!!.hints!!.isNotEmpty()) {
                        var hints = response.body()!!.hints!!
                        var foods : ArrayList<Prodotto> = arrayListOf()
                        for (i in 0..hints.size - 1)
                            foods.add(hints[i].food!!)
                        _foodLiveData.value = foods
                    } else {
                        Toast.makeText(context,"Spiacenti nessun risultato",Toast.LENGTH_LONG).show()
                        return
                    }
                }

                override fun onFailure(call: Call<Json_FoodList>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }
            })
    }

    fun getPreferiti(tipologiaPasto : String){
        viewModelScope.launch {
            _preferitiLiveData.value =
                preferitiDB.getPastiPreferiti(auth.currentUser!!.email!!, tipologiaPasto)
        }
    }

    fun deletePreferiti(id : String, tipologiaPasto: String, context:Context){
        viewModelScope.launch {
            if(preferitiDB.deletePreferiti(auth.currentUser!!.email!!, id, tipologiaPasto)){
                Toast.makeText(context,"Prodotto eliminato correttamente",Toast.LENGTH_LONG).show()
                getPreferiti(tipologiaPasto)
            }else{
                Toast.makeText(context,"ATTENZIONE!\nProdotto non eliminato",Toast.LENGTH_LONG).show()
            }

        }
    }

    fun setPastoOnDB(
        tipologiaPasto: String, foodId: String, image: String, nome: String/*label*/, calorie: Double, proteine: Double,
        carboidrati: Double, grassi: Double, quantita: Double, context: Context
    ) {
        viewModelScope.launch {
            if (prodottoDB.setPasto(
                    auth.currentUser?.email!!,
                    LocalDate.now().toString(), tipologiaPasto, foodId, image, nome/*label*/, calorie, proteine,
                    carboidrati, grassi, quantita
                )
            ) {
                Toast.makeText(context, "$quantita $nome aggiunto/i al tuo Diario", Toast.LENGTH_LONG).show()
                setChiloCalorie()
            } else
                Toast.makeText(context, "Aggiunta pasto al Diario fallita", Toast.LENGTH_LONG).show()
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
            Log.d("Pasto", hashMapCalorie.toString())
            diarioDB.setDiario(
                auth.currentUser?.email!!, LocalDate.now().toString(), diario.fabbisogno,
                hashMapMacro["grassi"]!!,  hashMapMacro["proteine"]!!,  hashMapMacro["carboidrati"]!!,
                diario.chiloCalorieEsercizio, hashMapCalorie["COLAZIONE"]!!,
                hashMapCalorie["PRANZO"]!!, hashMapCalorie["CENA"]!!,
                hashMapCalorie["SPUNTINO"]!!, diario.acqua
            )
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

    fun getPersonalizzati(tipologiaPasto : String){
        viewModelScope.launch {
            _personalizzatiLiveData.value =
                personalizzatiDB.getPastiPersonalizzati(auth.currentUser!!.email!!, tipologiaPasto)
        }
    }

    fun setPersonalizzatiOnDB(tipologiaPasto: String, nome: String, calorie: Double,proteine: Double,carboidrati: Double,grassi:Double,context: Context){
        viewModelScope.launch {
            if(personalizzatiDB.setPastoPersonalizzati(auth.currentUser!!.email!!,tipologiaPasto,nome,calorie,proteine,carboidrati,grassi))
                Toast.makeText(context,"Pasto personalizzato registrato con successo",Toast.LENGTH_LONG).show()
            else
                Toast.makeText(context,"ATTENZIONE, registrazione pasto personalizzato fallita",Toast.LENGTH_LONG).show()
        }
    }

    fun deletePersonalizzato(id : String, tipologiaPasto: String, context:Context) {
        viewModelScope.launch {
            if (personalizzatiDB.deletePersonalizzato(auth.currentUser!!.email!!, id, tipologiaPasto)) {
                Toast.makeText(context, "Prodotto eliminato correttamente", Toast.LENGTH_LONG).show()
                getPersonalizzati(tipologiaPasto)
            } else {
                Toast.makeText(context, "ATTENZIONE!\nProdotto non eliminato", Toast.LENGTH_LONG).show()
            }

        }
    }

    fun updatePersonalizzatoOnDB(id : String, tipologiaPasto: String, nome: String, calorie: Double,proteine: Double,carboidrati: Double,
                                 grassi:Double,context: Context){
        viewModelScope.launch {
            if(personalizzatiDB.updatePastoPersonalizzato(id,auth.currentUser!!.email!!,tipologiaPasto,nome,calorie,proteine,carboidrati,grassi))
                Toast.makeText(context,"Pasto personalizzato aggiornato con successo",Toast.LENGTH_LONG).show()
            else
                Toast.makeText(context,"ATTENZIONE, aggiornamento pasto personalizzato fallita",Toast.LENGTH_LONG).show()
        }
    }

}
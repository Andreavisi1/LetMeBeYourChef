package com.example.letmebeyourchef.aggiungi_esercizio

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.*
import com.example.letmebeyourchef.model.Json_Parsing.Esercizio
import com.example.letmebeyourchef.retrofit.RetrofitEserciziInstance
import com.example.letmebeyourchef.utils.APICredentials

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class AggiungiEserciziViewModel : ViewModel() {

    private val preferitiDB = PreferitiDB()
    private val esercizioDB = EsercizioDB()
    private val personalizzatiDB = PersonalizzatiDB()
    private val diarioDB = DiarioDB()
    private val auth = FirebaseAuth.getInstance()

    private var _eserciziLiveData = MutableLiveData<ArrayList<Esercizio>>()

    val eserciziLiveData: LiveData<ArrayList<Esercizio>>
        get() = _eserciziLiveData

    private var _preferitiLiveData = MutableLiveData<List<Esercizio>>()
    val preferitiLiveData: LiveData<List<Esercizio>>
        get() = _preferitiLiveData

    private var _personalizzatiLiveData = MutableLiveData<List<Esercizio>>()
    val personalizzatiLiveData: LiveData<List<Esercizio>>
        get() = _personalizzatiLiveData




    fun getEsercizi(activity : String, context: Context) {
        RetrofitEserciziInstance.api_esercizi.getEsercizi(APICredentials.API_KEY_ESERCIZI,activity)
            .enqueue(object : Callback<List<Esercizio>> {
            override fun onResponse(call: Call<List<Esercizio>>, response: Response<List<Esercizio>>) {
                if (response.body() != null && response.body()!!.isNotEmpty()) {
                    _eserciziLiveData.value = response.body()!! as ArrayList<Esercizio>
                } else {
                    Toast.makeText(context, "Spiacenti nessun risultato", Toast.LENGTH_LONG).show()
                    return
                }
            }

            override fun onFailure(call: Call<List<Esercizio>>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }

        })

    }


/*
    fun getEsercizi(name : String) {
        RetrofitEserciziInstance.api_esercizi.getEsercizi(name)
            .enqueue(object : Callback<EserciziList> {
                override fun onResponse(call: Call<EserciziList>, response: Response<EserciziList>) {
                    if (response.body() != null) {
                        var esercizi : EserciziList = response.body()!!
                        for (i in 0..response.body()!! .size - 1)
                            esercizi.add(response.body()!![i])
                        _eserciziLiveData.value = esercizi
                    } else {
                        return
                    }
                }
                override fun onFailure(call: Call<EserciziList>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }
            })

    }

 */

    fun getEserciziPreferiti(){
        viewModelScope.launch {
            _preferitiLiveData.value =  preferitiDB.getEserciziPreferiti(auth.currentUser!!.email!!)
            Log.d("preferiti", _preferitiLiveData.value.toString())
        }
    }

    fun deleteEserciziPreferiti(nome : String, context: Context){
        viewModelScope.launch {
            if(preferitiDB.deleteEserciziPreferiti(auth.currentUser!!.email!!, nome)){
                Toast.makeText(context,"Esercizio eliminato correttamente", Toast.LENGTH_LONG).show()
                getEserciziPreferiti()
            }else{
                Toast.makeText(context,"ATTENZIONE!\nEsercizio non eliminato", Toast.LENGTH_LONG).show()
            }

        }
    }

    fun setEsercizioOnDB( nome: String, calorieOra: Int, durata: Int, context: Context
    ) {
        viewModelScope.launch {
            if (esercizioDB.setEsercizio(auth.currentUser?.email!!,LocalDate.now().toString(), nome, calorieOra, durata)
            ) {
                Toast.makeText(context, "$nome svolto per $durata min è stato aggiunto al tuo Diario", Toast.LENGTH_LONG).show()
                setChiloCalorie()
            } else
                Toast.makeText(context, "Aggiunta pasto al Diario fallita", Toast.LENGTH_LONG).show()
        }
    }

    private fun setChiloCalorie() {
        viewModelScope.launch {
            val diario = diarioDB.getUserDiario(auth.currentUser!!.email!!)
            val arrayEsercizi = esercizioDB.getEsercizi(LocalDate.now().toString())
            var calorie_esercizio = 0.0
            if (arrayEsercizi != null) {
                if (arrayEsercizi.isNotEmpty()) {
                    for (esercizio in arrayEsercizi)
                        calorie_esercizio += esercizio.calorieOra * (esercizio.durata/60.0)
                }
            }
            diarioDB.setDiario( auth.currentUser?.email!!, LocalDate.now().toString(), diario!!.fabbisogno,
                diario.grassiTot,  diario.proteineTot,  diario.carboidratiTot, calorie_esercizio.toInt(),
                diario.chiloCalorieColazione, diario.chiloCaloriePranzo, diario.chiloCalorieCena,diario.chiloCalorieSpuntino
                , diario.acqua)
        }
    }


    fun getEserciziPersonalizzati(){
        viewModelScope.launch {
            _personalizzatiLiveData.value =
                personalizzatiDB.getEserciziPersonalizzati(auth.currentUser!!.email!!)
        }
    }

    fun setEserciziPersonalizzatiOnDB(nome: String, calorieOra: Double,context: Context){
        viewModelScope.launch {
            if(personalizzatiDB.setEsercizioPersonalizzati(auth.currentUser!!.email!!,nome,calorieOra))
                Toast.makeText(context,"Esercizio personalizzato registrato con successo", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(context,"ATTENZIONE, registrazione esercizio personalizzato fallita", Toast.LENGTH_LONG).show()
        }
    }

    fun deleteEsercizioPersonalizzato(id : String, context: Context) {
        viewModelScope.launch {
            if (personalizzatiDB.deleteEsercizioPersonalizzato(auth.currentUser!!.email!!, id)) {
                Toast.makeText(context, "Esercizio eliminato correttamente", Toast.LENGTH_LONG).show()
                getEserciziPersonalizzati()
            } else {
                Toast.makeText(context, "ATTENZIONE!\nEsercizio non eliminato", Toast.LENGTH_LONG).show()
            }

        }
    }

    fun updateEsercizioPersonalizzatoOnDB(id : String, nome: String, calorieOra: Double,context: Context){
        viewModelScope.launch {
            if(personalizzatiDB.updateEsercizioPersonalizzato(id,auth.currentUser!!.email!!,nome,calorieOra))
                Toast.makeText(context,"Esercizio personalizzato aggiornato con successo", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(context,"ATTENZIONE, aggiornamento esercizio personalizzato fallita", Toast.LENGTH_LONG).show()
        }
    }

}
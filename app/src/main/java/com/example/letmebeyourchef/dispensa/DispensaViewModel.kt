package com.example.letmebeyourchef.dispensa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.DietaDB
import com.example.letmebeyourchef.databaseFB.DispensaDB
import com.example.letmebeyourchef.databaseFB.EsercizioDB
import com.example.letmebeyourchef.databaseFB.ProdottoDB
import com.example.letmebeyourchef.databaseFB.UtenteDB
import com.example.letmebeyourchef.model.Dispensa
import com.example.letmebeyourchef.model.Json_Parsing.Esercizio
import com.example.letmebeyourchef.model.Pasto
import com.example.letmebeyourchef.model.Utente
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period

class DispensaViewModel : ViewModel() {

    private val dispensaDB = DispensaDB()
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


    private var _dispensa = MutableLiveData<Dispensa>()
    val dispensa: LiveData<Dispensa>
        get() = _dispensa

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

    private var _dispensaSettata = MutableLiveData<Boolean>()
    val dispensaSettata : LiveData<Boolean>
        get() = _dispensaSettata

    fun setDispensaOnDB(grassiTot:Int = 0, proteineTot:Int = 0,
                      carboidratiTot:Int = 0, chiloCalorieEsercizio:Int = 0, chiloCalorieColazione:Int = 0,
                      chiloCaloriePranzo:Int = 0, chiloCalorieCena:Int = 0, chiloCalorieSpuntino:Int = 0,
                      acqua: ArrayList<Boolean> = arrayListOf(false, false, false, false, false, false, false, false)){
        viewModelScope.launch {
            val utente = utenteDB.getUtente(auth.currentUser?.email!!)
            var fabbisogno = calculateFabbisogno(utente)
            dispensaDB.setDispensa(auth.currentUser?.email!!,
                LocalDate.now().toString(), fabbisogno.toInt(), grassiTot, proteineTot, carboidratiTot, chiloCalorieEsercizio,
                chiloCalorieColazione, chiloCaloriePranzo, chiloCalorieCena, chiloCalorieSpuntino, acqua)
        }
    }

    fun getUserDispensaDB(){
        viewModelScope.launch {
            _dispensa.value = dispensaDB.getUserDispensa(auth.currentUser?.email!!)
        }

    }

    //Variabile usata per la visualizzazione, altrimenti avrei visualizzato solamente il numero
    fun setAcqua(acqua : Double){
        var water = acqua.toString() + " L"
        _acqua.value = water
    }

    fun setAssunte(){
        _dispensaSettata.value = false
        val assunte = dispensa.value!!.chiloCalorieCena + dispensa.value!!.chiloCalorieColazione + dispensa.value!!.chiloCaloriePranzo + dispensa.value!!.chiloCalorieSpuntino
        _assunte.value = assunte.toString()
        _dispensaSettata.value = !(_dispensaSettata.value)!!
    }

    fun setRimanenti(){
        val rimanenti = dispensa.value!!.fabbisogno - assunte.value!!.toInt()
        _rimanenti.value = rimanenti.toString()
        _dispensaSettata.value = !(_dispensaSettata.value)!!


    }

    fun setMacro(){
        viewModelScope.launch {
            val utente = utenteDB.getUtente(auth.currentUser?.email!!)
/*            val dieta = dietaDB.getDieta(utente.dieta)
            _carboidratiMax.value = ((dispensa.value!!.fabbisogno*(dieta.perc_carb.toDouble()/100.0)) / 4).toInt() //1gr di carbo = 4Kcal
            _proteineMax.value = ((dispensa.value!!.fabbisogno*(dieta.perc_prot.toDouble()/100.0)) / 4).toInt() //1gr di prot = 4Kcal
            _grassiMax.value = ((dispensa.value!!.fabbisogno*(dieta.perc_prot.toDouble()/100.0)) / 9).toInt()//1gr di grassi = 9Kcal
            _dispensaSettata.value = !(_dispensaSettata.value)!!*/
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

    private fun calculateFabbisogno(utente: Utente) : Double{
        val today = LocalDate.now()
        val birthday: LocalDate = LocalDate.parse(utente.data_nascita)
        val period: Period = Period.between(birthday, today)
        return 88.0




    }

}



    /*private val _ingredientiList = MutableLiveData<List<String>>()
    val ingredientiList: LiveData<List<String>>
        get() = _ingredientiList

    fun loadIngredienti(context: Context) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val inputStream = context.assets.open("ingredienti.txt")
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

                _ingredientiList.postValue(ingredienti)
            } catch (e: Exception) {
                e.printStackTrace()
                // Gestisci l'errore nel caricamento dei dati dal file
            }
        }
    }
}*/
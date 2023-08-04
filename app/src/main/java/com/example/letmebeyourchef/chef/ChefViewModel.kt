package com.example.letmebeyourchef.chef

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.CartDB
import com.example.letmebeyourchef.databaseFB.DispensaDB
import com.example.letmebeyourchef.databaseFB.IngredientiPossedutiDB
import com.example.letmebeyourchef.databaseFB.IngredientiPreferitiDB
import com.example.letmebeyourchef.databaseFB.PersonalizzatiDB
import com.example.letmebeyourchef.databaseFB.ProdottoDB
import com.example.letmebeyourchef.model.Json_Parsing.Prodotto
import com.example.letmebeyourchef.model.Pasto
import com.example.letmebeyourchef.recipeModels.Ingredient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.time.LocalDate


class ChefViewModel : ViewModel() {

    private val ingredientiPossedutiDB = IngredientiPossedutiDB()
    private val preferitiDB = IngredientiPreferitiDB()
    private val prodottoDB = ProdottoDB()
    private val personalizzatiDB = PersonalizzatiDB()
    private val dispensaDB = DispensaDB()
    private val cartDB = CartDB()
    private val auth = FirebaseAuth.getInstance()
    private var hashMapCalorie = HashMap<String, Int>()
    private var hashMapMacro = HashMap<String, Int>()

    private var _foodLiveData = MutableLiveData<ArrayList<Prodotto>>()

    val foodLiveData : LiveData<ArrayList<Prodotto>>
        get() = _foodLiveData

    private var _preferitiLiveData = MutableLiveData<List<Ingredient>>()
    val preferitiLiveData: LiveData<List<Ingredient>>
        get() = _preferitiLiveData

    private var _personalizzatiLiveData = MutableLiveData<List<Pasto>>()
    val personalizzatiLiveData: LiveData<List<Pasto>>
        get() = _personalizzatiLiveData



    private var _ingredientiLiveData = MutableLiveData<List<String>>()
    val ingredientiLiveData: MutableLiveData<List<String>>
        get() = _ingredientiLiveData

    private var _ingredientiPossedutiLiveData = MutableLiveData<List<Ingredient>>()
    val ingredientiPossedutiLiveData: LiveData<List<Ingredient>>
        get() = _ingredientiPossedutiLiveData

    fun getIngredientiPosseduti(){
        viewModelScope.launch {
            _ingredientiPossedutiLiveData.value =
                ingredientiPossedutiDB.getIngredientiPosseduti(auth.currentUser!!.email!!)
        }
    }

    fun getIngredientiPreferiti(){
        viewModelScope.launch {
            _preferitiLiveData.value =
                preferitiDB.getIngredientiPreferiti(auth.currentUser!!.email!!)
        }
    }


    fun setIngredientToCartDB(
        id: Int,
        name: String?,
        image: String?,
        context: Context
    )
    {
        viewModelScope.launch {
            if (cartDB.setIngredientToCart(id, name, image, auth.currentUser?.email!!
                )
            ) {
                Toast.makeText(context, "$name added to cart", Toast.LENGTH_LONG).show()
            } else
                Toast.makeText(context, "Adding ingredient to cart failed", Toast.LENGTH_LONG).show()
        }
    }

    fun setIngredientiPossedutiOnDB(
        id: Int,
        name: String?,
        image: String?,
        context: Context
    )
    {
        viewModelScope.launch {
            if (ingredientiPossedutiDB.setIngredienti(id.toInt(), name, image, auth.currentUser?.email!!
                )
            ) {
                Toast.makeText(context, "$name added to your storage", Toast.LENGTH_LONG).show()
            } else
                Toast.makeText(context, "Adding ingredient to your storage failed", Toast.LENGTH_LONG).show()
        }
    }

    fun removeIngredient(id: Int, context: Context) {
        viewModelScope.launch {
            if(preferitiDB.deleteIngredientPreferito(auth.currentUser!!.email!!, id)){
                Toast.makeText(context,"Ingredient correctly deleted", Toast.LENGTH_LONG).show()
                getIngredientiPreferiti()
            }else{
                Toast.makeText(context,"ATTENTION!\nIngredient not deleted", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun setPastoOnDB(
        tipologiaPasto: String, foodId: String, image: String, nome: String/*label*/, calorie: Double, proteine: Double,
        carboidrati: Double, grassi: Double, context: Context
    ) {
        viewModelScope.launch {
            if (prodottoDB.setPasto(
                    auth.currentUser?.email!!,
                    LocalDate.now().toString(), tipologiaPasto, foodId, image, nome/*label*/, calorie, proteine,
                    carboidrati, grassi
                )
            ) {
                Toast.makeText(context, "$nome added to your storage", Toast.LENGTH_LONG).show()
                setChiloCalorie()
            } else
                Toast.makeText(context, "Adding ingredient to your storage failed", Toast.LENGTH_LONG).show()
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
            Log.d("Pasto", hashMapCalorie.toString())
            dispensaDB.setDispensa(
                auth.currentUser?.email!!, LocalDate.now().toString(), dispensa.fabbisogno,
                hashMapMacro["grassi"]!!,  hashMapMacro["proteine"]!!,  hashMapMacro["carboidrati"]!!,
                dispensa.chiloCalorieEsercizio, hashMapCalorie["COLAZIONE"]!!,
                hashMapCalorie["PRANZO"]!!, hashMapCalorie["CENA"]!!,
                hashMapCalorie["SPUNTINO"]!!, dispensa.acqua
            )
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
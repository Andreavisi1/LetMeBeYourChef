package com.example.letmebeyourchef.ricette_preferite

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.DispensaDB
import com.example.letmebeyourchef.databaseFB.PreferitiDB
import com.example.letmebeyourchef.databaseFB.ProdottoDB
import com.example.letmebeyourchef.databaseFB.RicettePreferiteDB
import com.example.letmebeyourchef.databaseFB.UtenteDB
import com.example.letmebeyourchef.listeners.RicettaClickListener
import com.example.letmebeyourchef.model.Pasto
import com.example.letmebeyourchef.model.Utente
import com.example.letmebeyourchef.recipeModels.FavouriteRecipe
import com.example.letmebeyourchef.recipeModels.Recipe
import com.example.letmebeyourchef.recipeModels.ResponseFromApiDettagliRicetta
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.time.LocalDate

class RicettePreferiteViewModel : ViewModel() {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val utenteDB = UtenteDB()

    private val prodottoDB = ProdottoDB()

    private val ricettePreferiteDB = RicettePreferiteDB()
    private val preferitiDB = PreferitiDB()
    private val dispensaDB = DispensaDB()
    private var hashMapCalorie = HashMap<String, Int>()
    private var hashMapMacro = HashMap<String, Int>()

    private var _utente = MutableLiveData(Utente())
    val utente: LiveData<Utente>
        get() = _utente

    private var _ricettePreferiteLiveData = MutableLiveData<List<FavouriteRecipe>>()
    val ricettePreferiteLiveData: LiveData<List<FavouriteRecipe>>
        get() = _ricettePreferiteLiveData

    private var favoriteRecipes: MutableLiveData<List<FavouriteRecipe>> = MutableLiveData()

    private var ricettePreferite: List<FavouriteRecipe>? = null

    // Metodo per aggiungere una ricetta preferita
    fun addFavoriteRecipe(recipe: FavouriteRecipe?) {
        val currentList = favoriteRecipes.value.orEmpty().toMutableList()
        if (recipe != null) {
            currentList.add(recipe)
        }
        favoriteRecipes.value = currentList
    }

    fun getRicettePreferite(){
        viewModelScope.launch {
            _ricettePreferiteLiveData.value =
                ricettePreferiteDB.getRicettePreferite(auth.currentUser!!.email!!)
        }
    }

    // Metodo per rimuovere una ricetta preferita
    fun removeFavoriteRecipe(id: String, context:Context) {
        viewModelScope.launch {
            if(ricettePreferiteDB.deleteRicettaPreferita(auth.currentUser!!.email!!, id)){
                Toast.makeText(context,"Product correctly deleted",Toast.LENGTH_LONG).show()
                getRicettePreferite()
            }else{
                Toast.makeText(context,"ATTENTION!\nProduct not deleted",Toast.LENGTH_LONG).show()
            }
        }
    }



    // Metodo per ottenere la lista delle ricette preferite
    fun getFavoriteRecipes() {
            viewModelScope.launch {
                _ricettePreferiteLiveData.value =
                    ricettePreferiteDB.getRicettePreferite(auth.currentUser!!.email!!)
            }
    }



/*    fun getPreferiti(tipologiaPasto : String){
        viewModelScope.launch {
            _preferitiLiveData.value =
                preferitiDB.getPastiPreferiti(auth.currentUser!!.email!!, tipologiaPasto)
        }
    }*/



    fun setRicettePreferiteOnDB(foodId: String, title: String, sourceName: String, readyInMinutes: Int, servings: Int,
                              sourceUrl: String, image: String, imageType: String?, instructions: String?, spoonacularSourceUrl: String?, context: Context
    )
    {
        viewModelScope.launch {
            if (ricettePreferiteDB.setRicettePreferite(foodId.toInt(), title, sourceName, readyInMinutes, servings,
                    sourceUrl, image, imageType, instructions, spoonacularSourceUrl, auth.currentUser?.email!!
                )
            ) {
                Toast.makeText(context, "$title added to favourites", Toast.LENGTH_LONG).show()
            } else
                Toast.makeText(context, "Adding receipe to favourites failed", Toast.LENGTH_LONG).show()
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

    fun getUtente() {
        viewModelScope.launch {
            _utente.value = utenteDB.getUtente(auth.currentUser!!.email!!)
        }

    }

    fun logOut() {
        auth.signOut()
    }
}

/*
    private val responseListenerRicetteRandom: ResponseListenerRicetteRandom =
        object : ResponseListenerRicetteRandom {
            public override fun didFetch(
                response: ResponseFromApiRicetteRandom?,
                message: String?
            ) {
                dialog!!.dismiss()
                recyclerView = findViewById(R.id.recycler_random)
                recyclerView.setHasFixedSize(true)
                recyclerView.setLayoutManager(GridLayoutManager(this@HomeActivity, 1))
                ricetteRandomAdapter = RicetteRandomAdapter(
                    this@HomeActivity,
                    response!!.recipes,
                    ricettaClickListener
                )
                recyclerView.setAdapter(ricetteRandomAdapter)
            }

            public override fun didError(message: String?) {
                Toast.makeText(this@HomeActivity, message, Toast.LENGTH_SHORT).show()
            }
        }
    private val spinnerSelectedListener: AdapterView.OnItemSelectedListener =
        object : AdapterView.OnItemSelectedListener {
            public override fun onItemSelected(
                adapterView: AdapterView<*>,
                view: View,
                i: Int,
                l: Long
            ) {
                tags.clear()
                tags.add(adapterView.getSelectedItem().toString())
                manager!!.getRicetteRandom(responseListenerRicetteRandom, tags)
                dialog!!.show()
            }

            public override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    private val ricettaClickListener: RicettaClickListener = object : RicettaClickListener {
        public override fun onClickRicetta(id: String?) {
            startActivity(
                Intent(this@HomeActivity, ActivityDettagliRicetta::class.java)
                    .putExtra("id", id)
            )
        }
    }

*/
package com.example.letmebeyourchef.ricette_preferite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.UtenteDB
import com.example.letmebeyourchef.model.Utente
import com.example.letmebeyourchef.recipeModels.Recipe
import com.example.letmebeyourchef.recipeModels.ResponseFromApiDettagliRicetta
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class RicettePreferiteViewModel : ViewModel() {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val utenteDB = UtenteDB()

    private var _utente = MutableLiveData(Utente())
    val utente: LiveData<Utente>
        get() = _utente

    private val favoriteRecipes: MutableLiveData<List<Recipe>> = MutableLiveData()

    // Metodo per aggiungere una ricetta preferita
    fun addFavoriteRecipe(recipe: Recipe?) {
        val currentList = favoriteRecipes.value.orEmpty().toMutableList()
        if (recipe != null) {
            currentList.add(recipe)
        }
        favoriteRecipes.value = currentList
    }

    // Metodo per rimuovere una ricetta preferita
    fun removeFavoriteRecipe(recipe: Recipe) {
        val currentList = favoriteRecipes.value.orEmpty().toMutableList()
        currentList.remove(recipe)
        favoriteRecipes.value = currentList
    }

    // Metodo per ottenere la lista delle ricette preferite
    fun getFavoriteRecipes(): LiveData<List<Recipe>> {
        return favoriteRecipes
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
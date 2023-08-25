package com.example.letmebeyourchef.ricette_preferite

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.RicettePreferiteDB
import com.example.letmebeyourchef.databaseFB.UtenteDB
import com.example.letmebeyourchef.model.Utente
import com.example.letmebeyourchef.recipeModels.FavouriteRecipe
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class RicettePreferiteViewModel : ViewModel() {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val utenteDB = UtenteDB()

    private val ricettePreferiteDB = RicettePreferiteDB()

    private var _utente = MutableLiveData(Utente())
    val utente: LiveData<Utente>
        get() = _utente

    private var _ricettePreferiteLiveData = MutableLiveData<List<FavouriteRecipe>>()
    val ricettePreferiteLiveData: LiveData<List<FavouriteRecipe>>
        get() = _ricettePreferiteLiveData

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

    // Metodo per aggiungere una ricetta preferita
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


    fun getUtente() {
        viewModelScope.launch {
            _utente.value = utenteDB.getUtente(auth.currentUser!!.email!!)
        }

    }

    fun logOut() {
        auth.signOut()
    }
}

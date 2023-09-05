package com.example.letmebeyourchef.aggiungi_ingrediente

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.IngredientiPossedutiDB
import com.example.letmebeyourchef.databaseFB.IngredientiPreferitiDB
import com.example.letmebeyourchef.databaseFB.UtenteDB
import com.example.letmebeyourchef.model.Utente
import com.example.letmebeyourchef.recipeModels.Ingredient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class RicercaIngredientiViewModel : ViewModel() {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val utenteDB = UtenteDB()
    private val ingredientiPossedutiDB = IngredientiPossedutiDB()
    private val ingredientiPreferitiDB = IngredientiPreferitiDB()


    private var _utente = MutableLiveData(Utente())
    val utente: LiveData<Utente>
        get() = _utente

    private var _ingredientiPossedutiLiveData = MutableLiveData<List<Ingredient>>()
    val ingredientiPossedutiLiveData: LiveData<List<Ingredient>>
        get() = _ingredientiPossedutiLiveData

    fun getIngredientiPosseduti(){
        viewModelScope.launch {
            _ingredientiPossedutiLiveData.value =
                ingredientiPossedutiDB.getIngredientiPosseduti(auth.currentUser!!.email!!)
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
            if (ingredientiPossedutiDB.setIngredienti(id, name, image, auth.currentUser?.email!!
                )
            ) {
                Toast.makeText(context, "$name added to your storage", Toast.LENGTH_LONG).show()
            } else
                Toast.makeText(context, "Adding ingredient to your storage failed", Toast.LENGTH_LONG).show()
        }
    }

    fun setIngredientiPreferitiOnDB(
        id: Int,
        name: String?,
        image: String?,
        context: Context
    )
    {
        viewModelScope.launch {
            if (ingredientiPreferitiDB.setIngredientiPreferiti(id.toInt(), name, image, auth.currentUser?.email!!
                )
            ) {
                Toast.makeText(context, "$name added to favourites", Toast.LENGTH_LONG).show()
            } else
                Toast.makeText(context, "Adding ingredient to favourites failed", Toast.LENGTH_LONG).show()
        }
    }

    fun removeIngredient(id: Int, context:Context) {
        viewModelScope.launch {
            if(ingredientiPossedutiDB.deleteIngredient(auth.currentUser!!.email!!, id)){
                Toast.makeText(context,"Ingredient correctly deleted",Toast.LENGTH_LONG).show()
                getIngredientiPosseduti()
            } else {
                Toast.makeText(context,"ATTENTION!\nIngredient not deleted",Toast.LENGTH_LONG).show()
            }
        }
    }

}

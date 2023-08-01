package com.example.letmebeyourchef.storage

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.DietaDB
import com.example.letmebeyourchef.databaseFB.DispensaDB
import com.example.letmebeyourchef.databaseFB.EsercizioDB
import com.example.letmebeyourchef.databaseFB.IngredientiPossedutiDB
import com.example.letmebeyourchef.databaseFB.IngredientiPreferitiDB
import com.example.letmebeyourchef.databaseFB.ProdottoDB
import com.example.letmebeyourchef.databaseFB.RicettePreferiteDB
import com.example.letmebeyourchef.databaseFB.UtenteDB
import com.example.letmebeyourchef.model.Dispensa
import com.example.letmebeyourchef.model.Json_Parsing.Esercizio
import com.example.letmebeyourchef.model.Pasto
import com.example.letmebeyourchef.model.Utente
import com.example.letmebeyourchef.recipeModels.FavouriteRecipe
import com.example.letmebeyourchef.recipeModels.Ingredient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period

class StorageViewModel : ViewModel() {

    private val dispensaDB = DispensaDB()
    private val utenteDB = UtenteDB()
    private val dietaDB = DietaDB()
    private val prodottoDB = ProdottoDB()
    private val esercizioDB = EsercizioDB()

    private val ingredientiPossedutiDB = IngredientiPossedutiDB()
    private val ingredientiPreferitiDB = IngredientiPreferitiDB()

    private val auth = FirebaseAuth.getInstance()

    private var _dispensa = MutableLiveData<Dispensa>()
    val dispensa: LiveData<Dispensa>
        get() = _dispensa

    private var _dispensaSettata = MutableLiveData<Boolean>()
    val dispensaSettata : LiveData<Boolean>
        get() = _dispensaSettata

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
            if (ingredientiPossedutiDB.setIngredienti(id.toInt(), name, image, auth.currentUser?.email!!
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

    fun removeIngredient(id: Int, context: Context) {
        viewModelScope.launch {
            if(ingredientiPossedutiDB.deleteIngredient(auth.currentUser!!.email!!, id)){
                Toast.makeText(context,"Ingredient correctly deleted", Toast.LENGTH_LONG).show()
                getIngredientiPosseduti()
            }else{
                Toast.makeText(context,"ATTENTION!\nIngredient not deleted", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun getUserDispensaDB(){
        viewModelScope.launch {
            _dispensa.value = dispensaDB.getUserDispensa(auth.currentUser?.email!!)
        }

    }

}

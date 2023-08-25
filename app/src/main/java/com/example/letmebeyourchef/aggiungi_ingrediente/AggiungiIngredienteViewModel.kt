package com.example.letmebeyourchef.aggiungi_ingrediente

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.IngredientiPossedutiDB
import com.example.letmebeyourchef.databaseFB.IngredientiPreferitiDB
import com.example.letmebeyourchef.recipeModels.Ingredient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch


class AggiungiIngredienteViewModel : ViewModel() {

    private val ingredientiPossedutiDB = IngredientiPossedutiDB()
    private val preferitiDB = IngredientiPreferitiDB()
    private val auth = FirebaseAuth.getInstance()


    private var _preferitiLiveData = MutableLiveData<List<Ingredient>>()
    val preferitiLiveData: LiveData<List<Ingredient>>
        get() = _preferitiLiveData



    fun getIngredientiPreferiti(){
        viewModelScope.launch {
            _preferitiLiveData.value =
                preferitiDB.getIngredientiPreferiti(auth.currentUser!!.email!!)
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
            if (preferitiDB.setIngredientiPreferiti(id, name, image, auth.currentUser?.email!!
                )
            ) {
                Toast.makeText(context, "$name added to favourites", Toast.LENGTH_LONG).show()
            } else
                Toast.makeText(context, "Adding ingredient to favouirtes failed", Toast.LENGTH_LONG).show()
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


}
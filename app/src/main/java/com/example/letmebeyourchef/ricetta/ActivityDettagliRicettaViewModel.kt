package com.example.letmebeyourchef.ricetta

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.DispensaDB
import com.example.letmebeyourchef.databaseFB.PreferitiDB
import com.example.letmebeyourchef.databaseFB.ProdottoDB
import com.example.letmebeyourchef.databaseFB.RicettePreferiteDB
import com.example.letmebeyourchef.model.Json_Parsing.Prodotto
import com.example.letmebeyourchef.recipeModels.Recipe
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class ActivityDettagliRicettaViewModel: ViewModel() {

    private val prodottoDB = ProdottoDB()
    private val ricette_preferiteDB = RicettePreferiteDB()
    private val dispensaDB = DispensaDB()
    private val auth = FirebaseAuth.getInstance()
    private var hashMapCalorie = HashMap<String, Int>()
    private var hashMapMacro = HashMap<String, Int>()

    private var _foodLiveData = MutableLiveData<ArrayList<Recipe>>()
    val foodLiveData : LiveData<ArrayList<Recipe>>
        get() = _foodLiveData

    fun setRicettaPreferitaOnDB(foodId: Int, title: String?,  sourceName: String?, readyInMinutes: Int, servings: Int, sourceUrl: String?, image: String, imageType: String?,
                                instructions: String?, spoonacularSourceUrl: String?, context: Context
    )
    {
        viewModelScope.launch {
            if (ricette_preferiteDB.setRicettePreferite(foodId, title!!, sourceName!!, readyInMinutes, servings, sourceUrl!!, image!!, imageType!!,
                    instructions!!, spoonacularSourceUrl!!, auth.currentUser?.email!!
                )
            ) {
                Toast.makeText(context, "$title added to favourites", Toast.LENGTH_LONG).show()
                //setChiloCalorie()
            } else
                Toast.makeText(context, "Adding ingredient to favourites failed", Toast.LENGTH_LONG).show()
        }
    }

}
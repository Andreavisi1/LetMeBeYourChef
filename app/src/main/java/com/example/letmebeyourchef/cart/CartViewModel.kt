package com.example.letmebeyourchef.cart

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.CartDB
import com.example.letmebeyourchef.databaseFB.UtenteDB
import com.example.letmebeyourchef.model.Utente
import com.example.letmebeyourchef.recipeModels.FavouriteRecipe
import com.example.letmebeyourchef.recipeModels.Ingredient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val cartDB = CartDB()

    private var _utente = MutableLiveData(Utente())
    val utente: LiveData<Utente>
        get() = _utente

    private var _ingredientiCartLiveData = MutableLiveData<List<Ingredient>>()
    val ingredientiCartLiveData: LiveData<List<Ingredient>>
        get() = _ingredientiCartLiveData

    private var favoriteRecipes: MutableLiveData<List<FavouriteRecipe>> = MutableLiveData()

    // Metodo per aggiungere una ricetta preferita
    fun addFavoriteRecipe(recipe: FavouriteRecipe?) {
        val currentList = favoriteRecipes.value.orEmpty().toMutableList()
        if (recipe != null) {
            currentList.add(recipe)
        }
        favoriteRecipes.value = currentList
    }

    fun getIngredientiCart(){
        viewModelScope.launch {
            _ingredientiCartLiveData.value =
                cartDB.getCart(auth.currentUser!!.email!!)
        }
    }

    // Metodo per rimuovere una ricetta preferita
    fun removeIngredientFromCart(id: Int, context:Context) {
        viewModelScope.launch {
            if(cartDB.deleteIngredientFromCart(auth.currentUser!!.email!!, id)){
                Toast.makeText(context,"You bought the ingredient!",Toast.LENGTH_LONG).show()
                getIngredientiCart()
            }else{
                Toast.makeText(context,"ATTENTION!\nIngredient not bought",Toast.LENGTH_LONG).show()
            }
        }
    }

}


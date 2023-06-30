package com.example.letmebeyourchef.dispensa

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.recipeModels.Ingredient
import com.example.letmebeyourchef.retrofit.ApiClient
import com.example.letmebeyourchef.retrofit.SpoonacularApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response


class DispensaViewModel : ViewModel() {

        val ingredientsLiveData: MutableLiveData<List<Ingredient>> = MutableLiveData()

        fun fetchIngredients() {
            viewModelScope.launch {
                val apiClient = ApiClient.create()
                val ingredients = apiClient.getIngredients()
                ingredientsLiveData.postValue(ingredients)
            }
        }
    }
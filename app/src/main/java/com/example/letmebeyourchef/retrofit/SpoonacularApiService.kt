package com.example.letmebeyourchef.retrofit

import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.recipeModels.Ingredient
import com.example.letmebeyourchef.recipeModels.Recipe
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface SpoonacularApiService {
    @GET("ingredients")
    suspend fun getIngredients(): List<Ingredient>

    // Aggiungi altri metodi API necessari per ottenere le ricette
}

object ApiClient {
    private const val BASE_URL = "https://api.spoonacular.com/"

    fun create(): SpoonacularApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(SpoonacularApiService::class.java)
    }
}
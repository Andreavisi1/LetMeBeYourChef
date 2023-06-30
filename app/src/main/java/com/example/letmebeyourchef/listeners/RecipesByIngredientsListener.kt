package com.example.letmebeyourchef.listeners

import com.example.letmebeyourchef.recipeModels.ResponseFromApiRecipesByIngredients

open interface RecipesByIngredientsListener {
    fun didFetch(response: List<ResponseFromApiRecipesByIngredients>, message: String?)
    fun didError(message: String?)
}
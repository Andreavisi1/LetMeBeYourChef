package com.example.letmebeyourchef.listeners

import com.example.letmebeyourchef.recipeModels.ResponseFromApiSearchIngredients

open interface SearchIngredientsListener {
    fun didFetch(response: ResponseFromApiSearchIngredients, message: String?)
    fun didError(message: String?)
}
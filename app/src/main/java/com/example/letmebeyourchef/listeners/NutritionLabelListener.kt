package com.example.letmebeyourchef.listeners

import com.example.letmebeyourchef.recipeModels.ResponseFromApiNutritionLabel

open interface NutritionLabelListener {
    fun didFetch(response: ResponseFromApiNutritionLabel?, message: String?)
    fun didError(message: String?)
}
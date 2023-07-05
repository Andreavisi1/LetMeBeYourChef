package com.example.letmebeyourchef.listeners

import com.example.letmebeyourchef.recipeModels.Recipe
import com.example.letmebeyourchef.recipeModels.ResponseFromApiDettagliRicetta
import com.example.letmebeyourchef.recipeModels.ResponseFromApiRicetteRandom

open interface RicettePreferiteListener {
    fun didFetch(response: Recipe?, message: String?)
    fun didError(message: String?)
}
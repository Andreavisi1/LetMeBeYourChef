package com.example.letmebeyourchef.listeners

import com.example.letmebeyourchef.recipeModels.Recipe

open interface RicettePreferiteListener {

    fun onItemClick(position: Int)
    fun didFetch(response: Recipe?, message: String?)
    fun didError(message: String?)
}
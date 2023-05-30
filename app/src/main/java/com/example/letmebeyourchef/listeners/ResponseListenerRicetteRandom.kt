package com.example.letmebeyourchef.listeners

import com.example.letmebeyourchef.recipeModels.ResponseFromApiRicetteRandom

open interface ResponseListenerRicetteRandom {
    fun didFetch(response: ResponseFromApiRicetteRandom?, message: String?)
    fun didError(message: String?)
}
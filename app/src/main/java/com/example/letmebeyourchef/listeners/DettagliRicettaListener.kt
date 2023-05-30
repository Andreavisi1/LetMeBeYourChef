package com.example.letmebeyourchef.listeners

import com.example.letmebeyourchef.recipeModels.ResponseFromApiDettagliRicetta

open interface DettagliRicettaListener {
    fun didFetch(response: ResponseFromApiDettagliRicetta?, message: String?)
    fun didError(message: String?)
}
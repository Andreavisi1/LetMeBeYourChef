package com.example.letmebeyourchef.listeners

import com.example.letmebeyourchef.recipeModels.ResponseFromApiRicetteSimili

open interface RicetteSimiliListener {
    fun didFetch(response: List<ResponseFromApiRicetteSimili>, message: String?)
    fun didError(message: String?)
}
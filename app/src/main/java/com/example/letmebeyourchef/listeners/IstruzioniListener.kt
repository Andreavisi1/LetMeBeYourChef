package com.example.letmebeyourchef.listeners

import com.example.letmebeyourchef.recipeModels.ResponseFromApiIstruzioni

open interface IstruzioniListener {
    fun didFetch(response: List<ResponseFromApiIstruzioni>, message: String?)
    fun didError(message: String?)
}
package com.example.letmebeyourchef.Listeners

import com.example.letmebeyourchef.Models.ResponseFromApiRicetteRandom

open interface ResponseListenerRicetteRandom {
    fun didFetch(response: ResponseFromApiRicetteRandom?, message: String?)
    fun didError(message: String?)
}
package com.example.letmebeyourchef.Listeners

import com.example.letmebeyourchef.Models.ResponseFromApiDettagliRicetta

open interface DettagliRicettaListener {
    fun didFetch(response: ResponseFromApiDettagliRicetta?, message: String?)
    fun didError(message: String?)
}
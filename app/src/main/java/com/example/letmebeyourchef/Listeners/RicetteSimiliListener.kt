package com.example.letmebeyourchef.Listeners

import com.example.letmebeyourchef.Models.ResponseFromApiRicetteSimili

open interface RicetteSimiliListener {
    fun didFetch(response: List<ResponseFromApiRicetteSimili>, message: String?)
    fun didError(message: String?)
}
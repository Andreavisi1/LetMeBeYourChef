package com.example.letmebeyourchef.Listeners

import com.example.letmebeyourchef.Models.ResponseFromApiIstruzioni

open interface IstruzioniListener {
    fun didFetch(response: List<ResponseFromApiIstruzioni>, message: String?)
    fun didError(message: String?)
}
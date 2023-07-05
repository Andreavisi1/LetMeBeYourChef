package com.example.letmebeyourchef.listeners

import android.content.Context

open interface RicettaClickListener {
    fun onClickRicetta(id: String, title: String?, sourceName: String?, readyInMinutes: Int, servings: Int,
                       sourceUrl: String?, image: String, imageType: String?, instructions: String?, spoonacularSourceUrl: String?
    )
}
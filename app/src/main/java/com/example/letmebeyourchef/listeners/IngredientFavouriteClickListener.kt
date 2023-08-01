package com.example.letmebeyourchef.listeners

import android.content.Context

open interface IngredientFavouriteClickListener {
    fun onClickFavouriteIngredient(id: Int, name: String?, image: String?)

}
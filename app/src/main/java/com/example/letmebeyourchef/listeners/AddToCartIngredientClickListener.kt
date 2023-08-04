package com.example.letmebeyourchef.listeners

import android.content.Context

open interface AddToCartIngredientClickListener {
    fun onClickAddToCartIngredient(id: Int, name: String?, image: String?)

}
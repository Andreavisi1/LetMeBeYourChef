package com.example.letmebeyourchef.listeners

import android.content.Context

open interface IngredientClickListener {
    fun onClickIngredient(id: Int, name: String?, image: String?)

}
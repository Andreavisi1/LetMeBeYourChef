package com.example.letmebeyourchef.listeners

import android.content.Context

open interface IngredientDeleteClickListener {
    fun onClickDeleteIngredient(id: Int, name: String?, image: String?)


}
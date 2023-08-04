package com.example.letmebeyourchef.recipeModels

import java.io.Serializable

class Ingredient : Serializable {
    var id = 0
    var name: String? = null
    var localizedName: String? = null
    var image: String? = null
}
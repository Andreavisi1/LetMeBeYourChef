package com.example.letmebeyourchef.recipeModels


class ResponseFromApiRecipesByIngredients {
    var id = 0
    var image: String? = null
    var imageType: String? = null
    var likes = 0
    var missedIngredientCount = 0
    var missedIngredients: ArrayList<MissedIngredient>? = null
    var title: String? = null
    var unusedIngredients: ArrayList<UnusedIngredient>? = null
    var usedIngredientCount = 0
    var usedIngredients: ArrayList<UsedIngredient>? = null
}
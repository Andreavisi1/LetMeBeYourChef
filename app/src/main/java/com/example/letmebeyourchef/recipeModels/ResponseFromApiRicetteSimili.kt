package com.example.letmebeyourchef.recipeModels

class ResponseFromApiRicetteSimili {
    var id = 0
    var title: String? = null
    var image: String? = null
    var imageType: String? = null
    var servings = 0
    var readyInMinutes = 0
    var license: String? = null
    var sourceName: String? = null
    var sourceUrl: String? = null
    var spoonacularSourceUrl: String? = null
    var aggregateLikes = 0
    var healthScore = 0.0
    var spoonacularScore = 0.0
    var pricePerServing = 0.0
    var analyzedInstructions: ArrayList<Any>? = null
    var cheap = false
    var creditsText: String? = null
    var cuisines: ArrayList<Any>? = null
    var dairyFree = false
    var diets: ArrayList<Any>? = null
    var gaps: String? = null
    var glutenFree = false
    var instructions: String? = null
    var ketogenic = false
    var lowFodmap = false
    var occasions: ArrayList<Any>? = null
    var sustainable = false
    var vegan = false
    var vegetarian = false
    var veryHealthy = false
    var veryPopular = false
    var whole30 = false
    var weightWatcherSmartPoints = 0
    var dishTypes: ArrayList<String>? = null
    var extendedIngredients: ArrayList<ExtendedIngredient>? = null
    var summary: String? = null
    var winePairing: WinePairing? = null
}
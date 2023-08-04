package com.example.letmebeyourchef

import android.content.Context
import com.example.letmebeyourchef.listeners.DettagliRicettaListener
import com.example.letmebeyourchef.listeners.IstruzioniListener
import com.example.letmebeyourchef.listeners.NutritionLabelListener
import com.example.letmebeyourchef.listeners.RecipesByIngredientsListener
import com.example.letmebeyourchef.listeners.ResponseListenerRicetteRandom
import com.example.letmebeyourchef.listeners.RicetteSimiliListener
import com.example.letmebeyourchef.listeners.SearchIngredientsListener
import com.example.letmebeyourchef.recipeModels.ResponseFromApiDettagliRicetta
import com.example.letmebeyourchef.recipeModels.ResponseFromApiIstruzioni
import com.example.letmebeyourchef.recipeModels.ResponseFromApiNutritionLabel
import com.example.letmebeyourchef.recipeModels.ResponseFromApiRecipesByIngredients
import com.example.letmebeyourchef.recipeModels.ResponseFromApiRicetteRandom
import com.example.letmebeyourchef.recipeModels.ResponseFromApiRicetteSimili
import com.example.letmebeyourchef.recipeModels.ResponseFromApiSearchIngredients
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class RequestManager constructor(var context: Context) {
    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.spoonacular.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRicetteRandom(listener: ResponseListenerRicetteRandom, tags: List<String>?) {
        val callRicetteRandom: CallRicetteRandom = retrofit.create(
            CallRicetteRandom::class.java
        )
        val call: Call<ResponseFromApiRicetteRandom> = callRicetteRandom.callRicetteRandom(
            context.getString(R.string.api_key), "10", tags
        )
        call.enqueue(object : Callback<ResponseFromApiRicetteRandom?> {
            override fun onResponse(
                call: Call<ResponseFromApiRicetteRandom?>,
                response: Response<ResponseFromApiRicetteRandom?>
            ) {
                if (!response.isSuccessful) {
                    listener.didError(response.message())
                    return
                }
                listener.didFetch(response.body(), response.message())
            }

            override fun onFailure(call: Call<ResponseFromApiRicetteRandom?>, t: Throwable) {
                listener.didError(t.message)
            }
        })
    }

    fun getDettagliRicetta(listener: DettagliRicettaListener, id: Int) {
        val callDettagliRicetta: CallDettagliRicetta = retrofit.create(
            CallDettagliRicetta::class.java
        )
        val call: Call<ResponseFromApiDettagliRicetta> =
            callDettagliRicetta.callDettagliRicetta(id, context.getString((R.string.api_key)))
        call.enqueue(object : Callback<ResponseFromApiDettagliRicetta?> {
            override fun onResponse(
                call: Call<ResponseFromApiDettagliRicetta?>,
                response: Response<ResponseFromApiDettagliRicetta?>
            ) {
                if (!response.isSuccessful) {
                    listener.didError(response.message())
                    return
                }
                listener.didFetch(response.body(), response.message())
            }

            override fun onFailure(
                call: Call<ResponseFromApiDettagliRicetta?>,
                t: Throwable
            ) {
                listener.didError(t.message)
            }
        })
    }

    fun callDettagliRicettaChef(listener: RecipesByIngredientsListener, id: Int) {
        val callDettagliRicettaChef: CallDettagliRicettaChef = retrofit.create(
            CallDettagliRicettaChef::class.java
        )
        val call: Call<List<ResponseFromApiRecipesByIngredients>> =
            callDettagliRicettaChef.callDettagliRicettaChef(id, context.getString(R.string.api_key), "min-missing-ingredients")
        call.enqueue(object : Callback<List<ResponseFromApiRecipesByIngredients>> {
            override fun onResponse(
                call: Call<List<ResponseFromApiRecipesByIngredients>>,
                response: Response<List<ResponseFromApiRecipesByIngredients>>
            ) {
                if (!response.isSuccessful) {
                    listener.didError(response.message())
                    return
                }
                listener.didFetch(response.body()!!, response.message())
            }

            override fun onFailure(
                call: Call<List<ResponseFromApiRecipesByIngredients>>,
                t: Throwable
            ) {
                listener.didError(t.message)
            }
        })
    }

    fun getRicetteSimili(listener: RicetteSimiliListener, id: Int) {
        val callRicetteSimili: CallRicetteSimili = retrofit.create(
            CallRicetteSimili::class.java
        )
        val call: Call<List<ResponseFromApiRicetteSimili>> =
            callRicetteSimili.callRicetteSimili(id, "8", context.getString(R.string.api_key))
        call.enqueue(object : Callback<List<ResponseFromApiRicetteSimili>> {
            override fun onResponse(
                call: Call<List<ResponseFromApiRicetteSimili>>,
                response: Response<List<ResponseFromApiRicetteSimili>>
            ) {
                if (!response.isSuccessful) {
                    listener.didError(response.message())
                    return
                }
                listener.didFetch((response.body())!!, response.message())
            }

            override fun onFailure(
                call: Call<List<ResponseFromApiRicetteSimili>>,
                t: Throwable
            ) {
                listener.didError(t.message)
            }
        })
    }

    fun getIstruzioni(listener: IstruzioniListener, id: Int) {
        val callIstruzioni: CallIstruzioni = retrofit.create(CallIstruzioni::class.java)
        val call: Call<List<ResponseFromApiIstruzioni>> =
            callIstruzioni.callIstruzioni(id, context.getString(R.string.api_key))
        call.enqueue(object : Callback<List<ResponseFromApiIstruzioni>> {
            override fun onResponse(
                call: Call<List<ResponseFromApiIstruzioni>>,
                response: Response<List<ResponseFromApiIstruzioni>>
            ) {
                if (!response.isSuccessful) {
                    listener.didError(response.message())
                    return
                }
                listener.didFetch((response.body())!!, response.message())
            }

            override fun onFailure(
                call: Call<List<ResponseFromApiIstruzioni>>,
                t: Throwable
            ) {
                listener.didError(t.message)
            }
        })
    }

    fun searchIngredient(listener: SearchIngredientsListener, name: String) {
        val callSearchIngredients: CallSearchIngredients = retrofit.create(
            CallSearchIngredients::class.java
        )
        val call: Call<ResponseFromApiSearchIngredients> =
            callSearchIngredients.callSearchIngredients(
                name, "en", context.getString(R.string.api_key)
            )
        call.enqueue(object : Callback<ResponseFromApiSearchIngredients> {
            override fun onResponse(
                call: Call<ResponseFromApiSearchIngredients>,
                response: Response<ResponseFromApiSearchIngredients>
            ) {
                if (!response.isSuccessful) {
                    listener.didError(response.message())
                    return
                }
                listener.didFetch((response.body())!!, response.message())
            }

            override fun onFailure(
                call: Call<ResponseFromApiSearchIngredients>,
                t: Throwable
            ) {
                listener.didError(t.message)
            }
        })
    }

    fun getNutritionLabel(listener: NutritionLabelListener, id: Int) {
        val callNutritionLabel: CallNutritionLabel = retrofit.create(
            CallNutritionLabel::class.java
        )
        val call: Call<ResponseFromApiNutritionLabel> =
            callNutritionLabel.callNutritionLabel(id, context.getString(R.string.api_key))
        call.enqueue(object : Callback<ResponseFromApiNutritionLabel?> {
            override fun onResponse(
                call: Call<ResponseFromApiNutritionLabel?>,
                response: Response<ResponseFromApiNutritionLabel?>
            ) {
                if (!response.isSuccessful) {
                    listener.didError(response.message())
                    return
                }
                listener.didFetch(response.body(), response.message())
            }

            override fun onFailure(
                call: Call<ResponseFromApiNutritionLabel?>,
                t: Throwable
            ) {
                listener.didError(t.message)
            }
        })
    }

    fun getRecipesByIngredients(listener: RecipesByIngredientsListener, tags: String) {
        val callRecipesByIngredients: CallRecipesByIngredients = retrofit.create(
            CallRecipesByIngredients::class.java
        )
        val call: Call<List<ResponseFromApiRecipesByIngredients>> =
            callRecipesByIngredients.callRecipesByIngredients(
                tags, 2, context.getString(R.string.api_key)
            )
        call.enqueue(object : Callback<List<ResponseFromApiRecipesByIngredients>> {
            override fun onResponse(
                call: Call<List<ResponseFromApiRecipesByIngredients>>,
                response: Response<List<ResponseFromApiRecipesByIngredients>>
            ) {
                if (!response.isSuccessful) {
                    listener.didError(response.message())
                    return
                }
                listener.didFetch((response.body())!!, response.message())
            }

            override fun onFailure(
                call: Call<List<ResponseFromApiRecipesByIngredients>>,
                t: Throwable
            ) {
                listener.didError(t.message)
            }
        })
    }

    private open interface CallRicetteRandom {
        @GET("recipes/random")
        fun callRicetteRandom(
            @Query("apiKey") apiKey: String?,
            @Query("number") number: String?,
            @Query("tags") tags: List<String>?
        ): Call<ResponseFromApiRicetteRandom>
    }

    private open interface CallDettagliRicetta {
        @GET("recipes/{id}/information")
        fun callDettagliRicetta(
            @Path("id") id: Int,
            @Query("apiKey") apiKey: String?
        ): Call<ResponseFromApiDettagliRicetta>
    }

    private open interface CallDettagliRicettaChef {
        @GET("recipes/{id}/information")
        fun callDettagliRicettaChef(
            @Path("id") id: Int,
            @Query("apiKey") apiKey: String?,
            @Query("sort") sort: String?
        ): Call<List<ResponseFromApiRecipesByIngredients>>
    }

    private open interface CallRicetteSimili {
        @GET("recipes/{id}/similar")
        fun callRicetteSimili(
            @Path("id") id: Int,
            @Query("number") number: String?,
            @Query("apiKey") apiKey: String?
        ): Call<List<ResponseFromApiRicetteSimili>>
    }

    private open interface CallIstruzioni {
        @GET("recipes/{id}/analyzedInstructions")
        fun callIstruzioni(
            @Path("id") id: Int,
            @Query("apiKey") apiKey: String?
        ): Call<List<ResponseFromApiIstruzioni>>
    }

    private open interface CallSearchIngredients {
        @GET("food/ingredients/search")
        fun callSearchIngredients(
            @Query("query") query: String,
            @Query("language") language: String,
            @Query("apiKey") apiKey: String?
        ): Call<ResponseFromApiSearchIngredients>
    }

    private open interface CallNutritionLabel {
        @GET("recipes/{id}/nutritionLabel.png")
        fun callNutritionLabel(
            @Path("id") id: Int,
            @Query("apiKey") apiKey: String?
        ): Call<ResponseFromApiNutritionLabel>
    }

    private open interface CallRecipesByIngredients {
        @GET("recipes/findByIngredients")
        fun callRecipesByIngredients(
            @Query("ingredients") ingredients: String,
            @Query("ranking") ranking: Int,
            @Query("apiKey") apiKey: String

        ): Call<List<ResponseFromApiRecipesByIngredients>>
    }


}
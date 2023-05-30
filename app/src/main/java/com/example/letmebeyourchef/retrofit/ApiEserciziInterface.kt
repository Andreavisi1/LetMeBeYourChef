package com.example.letmebeyourchef.retrofit

import com.example.letmebeyourchef.model.Json_Parsing.Esercizio
import retrofit2.Call
import retrofit2.http.*

interface ApiEserciziInterface {


    @GET("/v1/caloriesburned")
    fun getEsercizi(@Header("X-Api-Key") key : String,
                    @Query("activity") activity : String): Call<List<Esercizio>>
}
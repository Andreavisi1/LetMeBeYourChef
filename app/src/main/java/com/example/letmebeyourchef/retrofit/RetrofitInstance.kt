package com.example.letmebeyourchef.retrofit

import com.example.letmebeyourchef.utils.APICredentials
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {
    val api : ApiInterface by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory
                                .create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build())
                                )
            .baseUrl(APICredentials.BASE_URL)
            .build().create(ApiInterface::class.java)
        }
    }
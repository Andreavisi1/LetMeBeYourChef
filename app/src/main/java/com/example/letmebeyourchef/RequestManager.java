package com.example.letmebeyourchef;

import android.content.Context;

import com.example.letmebeyourchef.Listeners.ResponseListenerRicetteRandom;
import com.example.letmebeyourchef.Models.ResponseFromApiRicetteRandom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getRicetteRandom(ResponseListenerRicetteRandom listener) {
        CallRicetteRandom callRicetteRandom = retrofit.create(CallRicetteRandom.class);
        Call<ResponseFromApiRicetteRandom> call = callRicetteRandom.callRicetteRandom(context.getString(R.string.api_key), "10");
        call.enqueue(new Callback<ResponseFromApiRicetteRandom>() {
            @Override
            public void onResponse(Call<ResponseFromApiRicetteRandom> call, Response<ResponseFromApiRicetteRandom> response) {
                if(!response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<ResponseFromApiRicetteRandom> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    private interface CallRicetteRandom {
        @GET("recipes/random")
        Call<ResponseFromApiRicetteRandom> callRicetteRandom(
                @Query("apiKey") String apiKey,
                @Query("number") String number
        );
    }
}

package com.example.letmebeyourchef;

import android.content.Context;

import com.example.letmebeyourchef.Listeners.DettagliRicettaListener;
import com.example.letmebeyourchef.Listeners.ResponseListenerRicetteRandom;
import com.example.letmebeyourchef.Listeners.RicetteSimiliListener;
import com.example.letmebeyourchef.Models.ResponseFromApiDettagliRicetta;
import com.example.letmebeyourchef.Models.ResponseFromApiRicetteRandom;
import com.example.letmebeyourchef.Models.ResponseFromApiRicetteSimili;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    public void getRicetteRandom(ResponseListenerRicetteRandom listener, List<String> tags) {
        CallRicetteRandom callRicetteRandom = retrofit.create(CallRicetteRandom.class);
        Call<ResponseFromApiRicetteRandom> call = callRicetteRandom.callRicetteRandom(context.getString(R.string.api_key), "10", tags);
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

    public void getDettagliRicetta(DettagliRicettaListener listener, int id) {
        CallDettagliRicetta callDettagliRicetta = retrofit.create(CallDettagliRicetta.class);
        Call<ResponseFromApiDettagliRicetta> call = callDettagliRicetta.callDettagliRicetta(id, context.getString((R.string.api_key)));
        call.enqueue(new Callback<ResponseFromApiDettagliRicetta>() {
            @Override
            public void onResponse(Call<ResponseFromApiDettagliRicetta> call, Response<ResponseFromApiDettagliRicetta> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<ResponseFromApiDettagliRicetta> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getRicetteSimili(RicetteSimiliListener listener, int id) {
        CallRicetteSimili callRicetteSimili = retrofit.create(CallRicetteSimili.class);
        Call<List<ResponseFromApiRicetteSimili>> call = callRicetteSimili.callRicetteSimili(id, "8", context.getString(R.string.api_key));
        call.enqueue(new Callback<List<ResponseFromApiRicetteSimili>>() {
            @Override
            public void onResponse(Call<List<ResponseFromApiRicetteSimili>> call, Response<List<ResponseFromApiRicetteSimili>> response) {
                if(!response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<ResponseFromApiRicetteSimili>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    private interface CallRicetteRandom {
        @GET("recipes/random")
        Call<ResponseFromApiRicetteRandom> callRicetteRandom(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags")List<String> tags
        );
    }

    private interface CallDettagliRicetta {
        @GET("recipes/{id}/information")
        Call<ResponseFromApiDettagliRicetta> callDettagliRicetta(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }

    private interface CallRicetteSimili {
        @GET("recipes/{id}/similar")
        Call<List<ResponseFromApiRicetteSimili>> callRicetteSimili(
                @Path("id") int id,
                @Query("number") String number,
                @Query("apiKey") String apiKey
        );
    }
}

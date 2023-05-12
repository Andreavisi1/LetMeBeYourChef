package com.example.letmebeyourchef.Listeners;

import com.example.letmebeyourchef.Models.ResponseFromApiRicetteRandom;

public interface ResponseListenerRicetteRandom {
    void didFetch(ResponseFromApiRicetteRandom response, String message);
    void didError(String message);
}

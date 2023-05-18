package com.example.letmebeyourchef.Listeners;

import com.example.letmebeyourchef.Models.ResponseFromApiDettagliRicetta;

public interface DettagliRicettaListener {
    void didFetch(ResponseFromApiDettagliRicetta response, String message);
    void didError(String message);
}

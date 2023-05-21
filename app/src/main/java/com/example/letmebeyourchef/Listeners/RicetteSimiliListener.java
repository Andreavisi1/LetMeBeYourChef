package com.example.letmebeyourchef.Listeners;

import com.example.letmebeyourchef.Models.ResponseFromApiRicetteSimili;

import java.util.List;

public interface RicetteSimiliListener {
    void didFetch(List<ResponseFromApiRicetteSimili> response, String message);
    void didError(String message);
}

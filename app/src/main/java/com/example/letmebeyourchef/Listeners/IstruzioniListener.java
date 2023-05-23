package com.example.letmebeyourchef.Listeners;

import com.example.letmebeyourchef.Models.ResponseFromApiIstruzioni;

import java.util.List;

public interface IstruzioniListener {
    void didFetch(List<ResponseFromApiIstruzioni> response, String message);
    void didError(String message);
}

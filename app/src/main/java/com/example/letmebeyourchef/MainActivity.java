package com.example.letmebeyourchef;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letmebeyourchef.Adapters.RicetteRandomAdapter;
import com.example.letmebeyourchef.Listeners.ResponseListenerRicetteRandom;
import com.example.letmebeyourchef.Models.ResponseFromApiRicetteRandom;

public class MainActivity extends AppCompatActivity {

    ProgressDialog dialog;
    RequestManager manager;
    RicetteRandomAdapter ricetteRandomAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Caricamento ricette in corso...");

        manager = new RequestManager(this);
        manager.getRicetteRandom(responseListenerRicetteRandom);
        dialog.show();
    }

    private final ResponseListenerRicetteRandom responseListenerRicetteRandom = new ResponseListenerRicetteRandom() {
        @Override
        public void didFetch(ResponseFromApiRicetteRandom response, String message) {
            dialog.dismiss();
            recyclerView = findViewById(R.id.recycler_random);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
            ricetteRandomAdapter = new RicetteRandomAdapter(MainActivity.this, response.recipes);
            recyclerView.setAdapter(ricetteRandomAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}

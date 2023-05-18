package com.example.letmebeyourchef;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letmebeyourchef.Adapters.RicetteRandomAdapter;
import com.example.letmebeyourchef.Listeners.ResponseListenerRicetteRandom;
import com.example.letmebeyourchef.Listeners.RicettaClickListener;
import com.example.letmebeyourchef.Models.ResponseFromApiRicetteRandom;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    ProgressDialog dialog;
    RequestManager manager;
    RicetteRandomAdapter ricetteRandomAdapter;
    RecyclerView recyclerView;
    Spinner spinner;
    List<String> tags = new ArrayList<>();

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Caricamento ricette in corso...");

        searchView = findViewById(R.id.searchview_home);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                manager.getRicetteRandom(responseListenerRicetteRandom, tags);
                dialog.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        spinner = findViewById(R.id.spinner_tags);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.tags,
                R.layout.spinner_text
        );
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(spinnerSelectedListener);

        manager = new RequestManager(this);
//        manager.getRicetteRandom(responseListenerRicetteRandom);
//        dialog.show();
    }

    private final ResponseListenerRicetteRandom responseListenerRicetteRandom = new ResponseListenerRicetteRandom() {
        @Override
        public void didFetch(ResponseFromApiRicetteRandom response, String message) {
            dialog.dismiss();
            recyclerView = findViewById(R.id.recycler_random);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
            ricetteRandomAdapter = new RicetteRandomAdapter(MainActivity.this, response.recipes, ricettaClickListener);
            recyclerView.setAdapter(ricetteRandomAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            tags.clear();
            tags.add(adapterView.getSelectedItem().toString());
            manager.getRicetteRandom(responseListenerRicetteRandom, tags);
            dialog.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private final RicettaClickListener ricettaClickListener = new RicettaClickListener() {
        @Override
        public void onClickRicetta(String id) {
            startActivity(new Intent(MainActivity.this, ActivityDettagliRicetta.class)
                    .putExtra("id", id));
        }
    };

}

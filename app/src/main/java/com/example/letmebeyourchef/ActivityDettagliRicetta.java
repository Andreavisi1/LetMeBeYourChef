package com.example.letmebeyourchef;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.letmebeyourchef.Adapters.IngredientiAdapter;
import com.example.letmebeyourchef.Listeners.DettagliRicettaListener;
import com.example.letmebeyourchef.Models.ResponseFromApiDettagliRicetta;
import com.squareup.picasso.Picasso;

public class ActivityDettagliRicetta extends AppCompatActivity {
    int id;
    TextView textView_nome_ricetta, textView_source_ricetta, textView_descrizione_ricetta;
    ImageView imageView_immagine_ricetta;
    RecyclerView recycler_ingredienti_ricetta;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientiAdapter ingredientiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli_ricetta);

        findViews();

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);
        manager.getDettagliRicetta(dettagliRicettaListener, id);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Caricamento dettagli...");
        dialog.show();
    }

    private void findViews() {
        textView_nome_ricetta = findViewById(R.id.textView_nome_ricetta);
        textView_source_ricetta = findViewById(R.id.textView_source_ricetta);
        textView_descrizione_ricetta = findViewById(R.id.textView_descrizione_ricetta);
        imageView_immagine_ricetta = findViewById(R.id.imageView_immagine_ricetta);
        recycler_ingredienti_ricetta = findViewById(R.id.recycler_ingredienti_ricetta);
    }

    private final DettagliRicettaListener dettagliRicettaListener = new DettagliRicettaListener() {
        @Override
        public void didFetch(ResponseFromApiDettagliRicetta response, String message) {
            dialog.dismiss();
            textView_nome_ricetta.setText(response.title);
            textView_source_ricetta.setText(response.sourceName);
            textView_descrizione_ricetta.setText(response.summary);
            Picasso.get().load(response.image).into(imageView_immagine_ricetta);

            recycler_ingredienti_ricetta.setHasFixedSize(true);
            recycler_ingredienti_ricetta.setLayoutManager(new LinearLayoutManager(ActivityDettagliRicetta.this, LinearLayoutManager.HORIZONTAL, false));
            ingredientiAdapter = new IngredientiAdapter(ActivityDettagliRicetta.this, response.extendedIngredients);
            recycler_ingredienti_ricetta.setAdapter(ingredientiAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(ActivityDettagliRicetta.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}
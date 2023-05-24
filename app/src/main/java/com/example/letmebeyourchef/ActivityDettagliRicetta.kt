package com.example.letmebeyourchef

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.Adapters.IngredientiAdapter
import com.example.letmebeyourchef.Adapters.IstruzioniAdapter
import com.example.letmebeyourchef.Adapters.RicetteSimiliAdapter
import com.example.letmebeyourchef.Listeners.DettagliRicettaListener
import com.example.letmebeyourchef.Listeners.IstruzioniListener
import com.example.letmebeyourchef.Listeners.RicettaClickListener
import com.example.letmebeyourchef.Listeners.RicetteSimiliListener
import com.example.letmebeyourchef.Models.ResponseFromApiDettagliRicetta
import com.example.letmebeyourchef.Models.ResponseFromApiIstruzioni
import com.example.letmebeyourchef.Models.ResponseFromApiRicetteSimili
import com.squareup.picasso.Picasso

class ActivityDettagliRicetta constructor() : AppCompatActivity() {
    var id: Int = 0
    var textView_nome_ricetta: TextView? = null
    var textView_source_ricetta: TextView? = null
    var textView_descrizione_ricetta: TextView? = null
    var imageView_immagine_ricetta: ImageView? = null
    var recycler_ingredienti_ricetta: RecyclerView? = null
    var recycler_ricette_simili: RecyclerView? = null
    var recycler_istruzioni: RecyclerView? = null
    var manager: RequestManager? = null
    var dialog: ProgressDialog? = null
    var ingredientiAdapter: IngredientiAdapter? = null
    var ricetteSimiliAdapter: RicetteSimiliAdapter? = null
    var istruzioniAdapter: IstruzioniAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dettagli_ricetta)
        findViews()
        id = getIntent().getStringExtra("id")!!.toInt()
        manager = RequestManager(this)
        manager!!.getDettagliRicetta(dettagliRicettaListener, id)
        manager!!.getRicetteSimili(ricetteSimiliListener, id)
        manager!!.getIstruzioni(istruzioniListener, id)
        dialog = ProgressDialog(this)
        dialog!!.setTitle("Caricamento dettagli...")
        dialog!!.show()
    }

    private fun findViews() {
        textView_nome_ricetta = findViewById(R.id.textView_nome_ricetta)
        textView_source_ricetta = findViewById(R.id.textView_source_ricetta)
        textView_descrizione_ricetta = findViewById(R.id.textView_descrizione_ricetta)
        imageView_immagine_ricetta = findViewById(R.id.imageView_immagine_ricetta)
        recycler_ingredienti_ricetta = findViewById(R.id.recycler_ingredienti_ricetta)
        recycler_ricette_simili = findViewById(R.id.recycler_ricette_simili)
        recycler_istruzioni = findViewById(R.id.recycler_istruzioni)
    }

    private val dettagliRicettaListener: DettagliRicettaListener =
        object : DettagliRicettaListener {
            public override fun didFetch(
                response: ResponseFromApiDettagliRicetta?,
                message: String?
            ) {
                dialog!!.dismiss()
                textView_nome_ricetta!!.setText(response!!.title)
                textView_source_ricetta!!.setText(response.sourceName)
                textView_descrizione_ricetta!!.setText(response.summary)
                Picasso.get().load(response.image).into(imageView_immagine_ricetta)
                recycler_ingredienti_ricetta!!.setHasFixedSize(true)
                recycler_ingredienti_ricetta!!.setLayoutManager(
                    LinearLayoutManager(
                        this@ActivityDettagliRicetta,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                )
                ingredientiAdapter =
                    IngredientiAdapter(this@ActivityDettagliRicetta, response.extendedIngredients)
                recycler_ingredienti_ricetta!!.setAdapter(ingredientiAdapter)
            }

            public override fun didError(message: String?) {
                Toast.makeText(this@ActivityDettagliRicetta, message, Toast.LENGTH_SHORT).show()
            }
        }
    private val ricetteSimiliListener: RicetteSimiliListener = object : RicetteSimiliListener {
        public override fun didFetch(
            response: List<ResponseFromApiRicetteSimili>,
            message: String?
        ) {
            recycler_ricette_simili!!.setHasFixedSize(true)
            recycler_ricette_simili!!.setLayoutManager(
                LinearLayoutManager(
                    this@ActivityDettagliRicetta,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            )
            ricetteSimiliAdapter =
                RicetteSimiliAdapter(this@ActivityDettagliRicetta, response, ricettaClickListener)
            recycler_ricette_simili!!.setAdapter(ricetteSimiliAdapter)
        }

        public override fun didError(message: String?) {
            Toast.makeText(this@ActivityDettagliRicetta, message, Toast.LENGTH_SHORT).show()
        }
    }
    private val ricettaClickListener: RicettaClickListener = object : RicettaClickListener {
        public override fun onClickRicetta(id: String?) {
            startActivity(
                Intent(this@ActivityDettagliRicetta, ActivityDettagliRicetta::class.java)
                    .putExtra("id", id)
            )
        }
    }
    private val istruzioniListener: IstruzioniListener = object : IstruzioniListener {
        public override fun didFetch(response: List<ResponseFromApiIstruzioni>, message: String?) {
            recycler_istruzioni!!.setHasFixedSize(true)
            recycler_istruzioni!!.setLayoutManager(
                LinearLayoutManager(
                    this@ActivityDettagliRicetta,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            )
            istruzioniAdapter = IstruzioniAdapter(this@ActivityDettagliRicetta, response)
            recycler_istruzioni!!.setAdapter(istruzioniAdapter)
        }

        public override fun didError(message: String?) {}
    }
}
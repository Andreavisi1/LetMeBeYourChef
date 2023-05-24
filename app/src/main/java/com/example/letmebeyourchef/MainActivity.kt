package com.example.letmebeyourchef

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.Adapters.RicetteRandomAdapter
import com.example.letmebeyourchef.Listeners.ResponseListenerRicetteRandom
import com.example.letmebeyourchef.Listeners.RicettaClickListener
import com.example.letmebeyourchef.Models.ResponseFromApiRicetteRandom

class MainActivity constructor() : AppCompatActivity() {
    var dialog: ProgressDialog? = null
    var manager: RequestManager? = null
    var ricetteRandomAdapter: RicetteRandomAdapter? = null
    lateinit var recyclerView: RecyclerView
    lateinit var spinner: Spinner
    var tags: MutableList<String> = ArrayList()
    lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dialog = ProgressDialog(this)
        dialog!!.setTitle("Caricamento ricette in corso...")
        searchView = findViewById(R.id.searchview_home)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            public override fun onQueryTextSubmit(query: String): Boolean {
                tags.clear()
                tags.add(query)
                manager!!.getRicetteRandom(responseListenerRicetteRandom, tags)
                dialog!!.show()
                return true
            }

            public override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })
        spinner = findViewById(R.id.spinner_tags)
        val arrayAdapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(
            this,
            R.array.tags,
            R.layout.spinner_text
        )
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text)
        spinner.setAdapter(arrayAdapter)
        spinner.setOnItemSelectedListener(spinnerSelectedListener)
        manager = RequestManager(this)
        //        manager.getRicetteRandom(responseListenerRicetteRandom);
//        dialog.show();
    }

    private val responseListenerRicetteRandom: ResponseListenerRicetteRandom =
        object : ResponseListenerRicetteRandom {
            public override fun didFetch(
                response: ResponseFromApiRicetteRandom?,
                message: String?
            ) {
                dialog!!.dismiss()
                recyclerView = findViewById(R.id.recycler_random)
                recyclerView.setHasFixedSize(true)
                recyclerView.setLayoutManager(GridLayoutManager(this@MainActivity, 1))
                ricetteRandomAdapter = RicetteRandomAdapter(
                    this@MainActivity,
                    response!!.recipes,
                    ricettaClickListener
                )
                recyclerView.setAdapter(ricetteRandomAdapter)
            }

            public override fun didError(message: String?) {
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }
        }
    private val spinnerSelectedListener: AdapterView.OnItemSelectedListener =
        object : AdapterView.OnItemSelectedListener {
            public override fun onItemSelected(
                adapterView: AdapterView<*>,
                view: View,
                i: Int,
                l: Long
            ) {
                tags.clear()
                tags.add(adapterView.getSelectedItem().toString())
                manager!!.getRicetteRandom(responseListenerRicetteRandom, tags)
                dialog!!.show()
            }

            public override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    private val ricettaClickListener: RicettaClickListener = object : RicettaClickListener {
        public override fun onClickRicetta(id: String?) {
            startActivity(
                Intent(this@MainActivity, ActivityDettagliRicetta::class.java)
                    .putExtra("id", id)
            )
        }
    }
}
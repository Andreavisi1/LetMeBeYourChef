package com.example.letmebeyourchef.aggiungi_esercizio

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.FragmentRicercaBinding
import com.example.letmebeyourchef.esercizio.EsercizioAddPrefActivity
import com.example.letmebeyourchef.model.Json_Parsing.Esercizio

class RicercaEserciziFragment: Fragment() {

    private lateinit var binding: FragmentRicercaBinding
    private var model = AggiungiEserciziViewModel()

    //Prova adapter
    private lateinit var newRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ricerca, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnScanner.visibility = View.GONE


        val searchBar = binding.searchBar1
        searchBar.queryHint = "Cerca il tuo esercizio"
        searchBar.onActionViewCollapsed()
        searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                model.getEsercizi(query!!,requireContext())
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return true
            }
        })

        newRecyclerView = binding.gridProdotto
        newRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        newRecyclerView.setHasFixedSize(true)

        val eserciziObserver = Observer<ArrayList<Esercizio>> {

            val adapter = MyAdapterRicercaEsercizio(model.eserciziLiveData.value!!)
            newRecyclerView.adapter = adapter
            adapter.setOnItemClickListener(object : MyAdapterRicercaEsercizio.onItemClickListener {
                override fun onItemClick(position: Int) {
                    binding.searchBar1.clearFocus()
                    val intent = prepareIntent(position)
                    startActivity(intent)
                }
            })
        }
        model.eserciziLiveData.observe(viewLifecycleOwner, eserciziObserver)



    }

    private fun prepareIntent(position: Int) : Intent {
        var intent = Intent(requireContext(), EsercizioAddPrefActivity::class.java)
        intent.putExtra("nome", model.eserciziLiveData.value!![position].nome)
        intent.putExtra("calorieOra", model.eserciziLiveData.value!![position].calorieOra)
        return intent
    }

}

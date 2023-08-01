package com.example.letmebeyourchef.aggiungi_pasto

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.FragmentRicercaBinding
import com.example.letmebeyourchef.model.Json_Parsing.Prodotto
import com.example.letmebeyourchef.prodotto.ProdottoActivity
import com.example.letmebeyourchef.scanner.ScannerActivity


class RicercaFragment : Fragment() {

    private lateinit var binding: FragmentRicercaBinding
    private var model = AggiungiViewModel()

    //Prova adapter
    private lateinit var newRecyclerView: RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ricerca, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (requireArguments().getString("upc") != null)
            model.getFoodFromNameorUPC("", requireArguments().getString("upc")!!,requireContext())


        val searchBar = binding.searchBar1
        searchBar.queryHint = "What are you looking for?"
        searchBar.onActionViewCollapsed()
        searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                    model.getFoodFromNameorUPC(query!!,"",requireContext())
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return true
            }
        })

        newRecyclerView = binding.gridProdotto
        newRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        newRecyclerView.setHasFixedSize(true)
        val foodObserver = Observer<ArrayList<Prodotto>> {

            val adapter = MyAdapterRicerca(model.foodLiveData.value!!)
            newRecyclerView.adapter = adapter
            adapter.setOnItemClickListener(object : MyAdapterRicerca.onItemClickListener {
                override fun onItemClick(position: Int) {
                    binding.searchBar1.clearFocus()
                    val intent = prepareIntent(position)
                    startActivity(intent)
                }
            })
        }
        model.foodLiveData.observe(viewLifecycleOwner, foodObserver)


        binding.btnScanner.setOnClickListener {
            val intent = Intent(requireContext(), ScannerActivity::class.java)
            Log.d("bottone", requireArguments().getString("bottone")!!)
            intent.putExtra("bottone", requireArguments().getString("bottone"))
            startActivity(intent)
            requireActivity().finish()
        }

    }

    private fun prepareIntent(position: Int) : Intent{
        var intent = Intent(requireContext(), ProdottoActivity::class.java)
        intent.putExtra("tipologiaPasto", requireArguments().getString("bottone"))
        intent.putExtra("brand", model.foodLiveData.value!![position].brand)
        intent.putExtra("category", model.foodLiveData.value!![position].category)
        intent.putExtra("foodContents", model.foodLiveData.value!![position].foodContentsLabel)
        intent.putExtra("foodId", model.foodLiveData.value!![position].foodId)
        intent.putExtra("image", model.foodLiveData.value!![position].image)
        intent.putExtra("knownAs", model.foodLiveData.value!![position].knownAs)
        intent.putExtra("label", model.foodLiveData.value!![position].label)
        intent.putExtra("nutrients", model.foodLiveData.value!![position].nutrients)
        return intent
    }


}

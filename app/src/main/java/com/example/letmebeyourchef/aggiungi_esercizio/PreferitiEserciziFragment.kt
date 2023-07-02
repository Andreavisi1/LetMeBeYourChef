package com.example.letmebeyourchef.aggiungi_esercizio

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.FragmentPreferitiBinding
import com.example.letmebeyourchef.model.Json_Parsing.Esercizio
import com.example.letmebeyourchef.model.Json_Parsing.Prodotto
import kotlinx.android.synthetic.main.add_delete_layout.*
import kotlinx.android.synthetic.main.add_delete_layout.btnAddDispensa
import kotlinx.android.synthetic.main.add_delete_layout.btnAnnulla
import kotlinx.android.synthetic.main.add_delete_layout.btnElimina
import kotlinx.android.synthetic.main.add_delete_layout_esercizi.*

class PreferitiEserciziFragment : Fragment() {

    lateinit private var binding: FragmentPreferitiBinding


    //Prova adapter
    private lateinit var recyclerViewPreferiti: RecyclerView
    private lateinit var preferitiList: ArrayList<Prodotto>

    private val model = AggiungiEserciziViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_preferiti, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.getEserciziPreferiti()
        recyclerViewPreferiti = binding.gridPreferiti
        recyclerViewPreferiti.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewPreferiti.setHasFixedSize(true)


        val eserciziPreferitiObserver = Observer<List<Esercizio>>{
            val adapter = MyAdapterPrefPersEsercizio(model.preferitiLiveData.value!! as ArrayList<Esercizio>)
            recyclerViewPreferiti.adapter = adapter
            adapter.setOnItemClickListener(object : MyAdapterPrefPersEsercizio.onItemClickListener {
                override fun onItemClick(position: Int) {
                    openUpdateDeleteDialog(position)
                }
            })
        }
        model.preferitiLiveData.observe(viewLifecycleOwner,eserciziPreferitiObserver)


    }


    @SuppressLint("ResourceAsColor")
    private fun openUpdateDeleteDialog(position: Int){
        var dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.add_delete_layout_esercizi)
        dialog.window?.setBackgroundDrawable(ColorDrawable(R.color.transparent))
        dialog.esercizioNome.text = model.preferitiLiveData.value!![position].nome
        dialog.textViewMessaggioEsercizio.text = "Scegli cosa vuoi fare! "


        var flag = false
        dialog.btnAddDispensa.setOnClickListener {
            dialog.layout_durata.visibility = View.VISIBLE
            val durata = dialog.editTextDurata.text.toString()
            if(durata != "0" && durata != "") {
                model.setEsercizioOnDB(model.preferitiLiveData.value!![position].nome,model.preferitiLiveData.value!![position].calorieOra, durata.toDouble().toInt(),requireContext())
                dialog.dismiss()
            }
            else {
                if (flag)
                    Toast.makeText(requireContext(),"Per favore inserisci una durata diversa da $durata se desideri aggiungere l\'esercizio al Diario",
                        Toast.LENGTH_LONG).show()
                flag = true
            }
        }

        dialog.btnElimina.setOnClickListener {
            model.deleteEserciziPreferiti(model.preferitiLiveData.value!![position].nome, requireContext() )
            dialog.dismiss()
        }

        dialog.btnAnnulla.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }


}
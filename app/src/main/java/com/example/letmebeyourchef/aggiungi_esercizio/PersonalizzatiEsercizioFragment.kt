package com.example.letmebeyourchef.aggiungi_esercizio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.FragmentPersonalizzatiBinding
import com.example.letmebeyourchef.model.Json_Parsing.Esercizio

class PersonalizzatiEsercizioFragment : Fragment() {

    lateinit private var binding: FragmentPersonalizzatiBinding

    private lateinit var recyclerViewPersonalizzati: RecyclerView
    val model = AggiungiEserciziViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_personalizzati, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.getEserciziPersonalizzati()

        recyclerViewPersonalizzati = binding.gridProdotto
        recyclerViewPersonalizzati.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewPersonalizzati.setHasFixedSize(true)
        val personalizzatiObserver = Observer<List<Esercizio>>{

            val adapter = MyAdapterPrefPersEsercizio(model.personalizzatiLiveData.value!! as ArrayList<Esercizio>)
            recyclerViewPersonalizzati.adapter = adapter
            adapter.setOnItemClickListener(object : MyAdapterPrefPersEsercizio.onItemClickListener {
                override fun onItemClick(position: Int) {
                    aggiungiEsercizio(position,"clickItem",model.personalizzatiLiveData.value!![position].id)
                }
            })
        }
        model.personalizzatiLiveData.observe(viewLifecycleOwner,personalizzatiObserver)


        binding.btnAggiungi.setOnClickListener {
                aggiungiEsercizio(0,"clickAggiungi","")
        }



    }

    private fun aggiungiEsercizio(position : Int, bottone:String, id : String){
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.calorie_semplici_layout, null)
        val nome = dialogLayout.findViewById<EditText>(R.id.eT_nomeEs)
        val kcal_h = dialogLayout.findViewById<EditText>(R.id.eT_kcal_h)
        val durata_min = dialogLayout.findViewById<EditText>(R.id.eT_durata_min)
        val btnAggiungiLista = dialogLayout.findViewById<Button>(R.id.btnAggiungiEsercizio)
        val btnElimina = dialogLayout.findViewById<Button>(R.id.buttonEliminaEsercizio)
        val btnAggiungiDiario = dialogLayout.findViewById<Button>(R.id.btnAggiungiDiarioEsercizio)
        val layout_info = dialogLayout.findViewById<LinearLayout>(R.id.layout_info_esercizio)
        val layout_durata  = dialogLayout.findViewById<LinearLayout>(R.id.llchooseDurata)
        if(bottone == "clickItem"){
            btnAggiungiLista.text = "AGGIORNA\nNELLA LISTA"
            layout_info.visibility = View.GONE
            layout_durata.visibility = View.GONE
            btnElimina.visibility = View.VISIBLE
            btnAggiungiDiario.visibility = View.VISIBLE
            setText(arrayOf(nome,kcal_h,durata_min),position)

        }else{
            btnAggiungiLista.text = "AGGIUNGI\nALLA LISTA"
            btnElimina.visibility = View.GONE
            btnAggiungiDiario.visibility = View.GONE
            layout_durata.visibility = View.GONE
        }
        var flag_2 = false
        btnAggiungiDiario.setOnClickListener {
            layout_durata.visibility = View.VISIBLE
            layout_info.visibility = View.GONE
            val durata = durata_min.text.toString().toInt()
            if(durata != 0 && durata.toString() != ""){
                model.setEsercizioOnDB(model.personalizzatiLiveData.value!![position].nome, model.personalizzatiLiveData.value!![position].calorieOra, durata,requireContext())
                dialogLayout.visibility = View.GONE
            }else {
                if (flag_2)
                    Toast.makeText(requireContext(),"Per favore inserisci una durata diversa da $durata se desideri aggiungere l\'eserczio al Diario",
                        Toast.LENGTH_LONG).show()
                flag_2 = true
            }

        }



        var flag=false
        btnAggiungiLista.setOnClickListener {
            layout_info.visibility = View.VISIBLE
            layout_durata.visibility = View.GONE
            var kcalOra_salva = kcal_h.text.toString()
            var nome = nome.text.toString().trim()
            if(kcalOra_salva != "" && nome != "") {
                if(bottone == "clickItem"){
                    if (flag && valueAreChanged(position,kcalOra_salva,nome)){
                        model.updateEsercizioPersonalizzatoOnDB(id, nome, kcalOra_salva.toDouble(), requireContext())
                        flag=false
                        dialogLayout.visibility = View.GONE
                    }else{
                        Toast.makeText(requireContext(),"Cambia i valori prima di salvare",Toast.LENGTH_LONG).show()
                        flag=true
                    }
                }else {
                    model.setEserciziPersonalizzatiOnDB(nome, kcalOra_salva.toDouble(), requireContext())
                    dialogLayout.visibility = View.GONE
                }
                model.getEserciziPersonalizzati()
            }
            else
                Toast.makeText(requireContext(),"Per favore completa tutti i campi prima di salvare", Toast.LENGTH_LONG).show()

        }

        builder.setNegativeButton("Esci"){ dialog, which ->
            dialog.dismiss()
        }

        btnElimina.setOnClickListener {
            model.deleteEsercizioPersonalizzato(id,requireContext())
            dialogLayout.visibility = View.GONE
        }
        builder.setView(dialogLayout)
        builder.show()
    }

    private fun valueAreChanged(position: Int, kcalOra_salva : String, nome : String): Boolean {
        return (kcalOra_salva.toInt() != model.personalizzatiLiveData.value!![position].calorieOra
                || nome != model.personalizzatiLiveData.value!![position].nome)

    }



    private fun setText(edit : Array<EditText>, position : Int){
        edit[0].setText(model.personalizzatiLiveData.value!![position].nome)
        edit[1].setText(model.personalizzatiLiveData.value!![position].calorieOra.toString())
        edit[2].setText(model.personalizzatiLiveData.value!![position].durata.toString())
    }


}
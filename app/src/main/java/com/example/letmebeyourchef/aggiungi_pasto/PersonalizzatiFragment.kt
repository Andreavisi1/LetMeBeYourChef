package com.example.letmebeyourchef.aggiungi_pasto

import android.os.Bundle
import android.util.Log
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
import com.example.letmebeyourchef.model.Pasto


class PersonalizzatiFragment : Fragment() {

    lateinit private var binding: FragmentPersonalizzatiBinding


    //Prova adapter
    private lateinit var recyclerViewPersonalizzati: RecyclerView

    val model = AggiungiViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_personalizzati, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.getPersonalizzati(requireArguments().getString("bottone")!!)

        recyclerViewPersonalizzati = binding.gridProdotto
        recyclerViewPersonalizzati.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewPersonalizzati.setHasFixedSize(true)
        val personalizzatiObserver = Observer<List<Pasto>>{

            val adapter = MyAdapterPrefPers(model.personalizzatiLiveData.value!! as ArrayList<Pasto>)
            recyclerViewPersonalizzati.adapter = adapter
            adapter.setOnItemClickListener(object : MyAdapterPrefPers.onItemClickListener {
                override fun onItemClick(position: Int) {
                    aggiungiProdotto(position,"clickItem", model.personalizzatiLiveData.value!![position].id)
                }
            })
        }
        model.personalizzatiLiveData.observe(viewLifecycleOwner,personalizzatiObserver)


        if(requireArguments().getString("bottone") == "ESERCIZIO")
            binding.btnAggiungi.setOnClickListener {
                aggiungiEsercizio()
            }
        else
            binding.btnAggiungi.setOnClickListener {
                aggiungiProdotto(0,"clickAggiungi","")//idEsercizio)
            }



    }


    private fun aggiungiEsercizio() {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.calorie_semplici_layout, null)
        val kcal = dialogLayout.findViewById<EditText>(R.id.eT_kcal)
        val titolo = dialogLayout.findViewById<EditText>(R.id.eT_nomeEs)


        with(builder){
            setTitle("AGGIUNGI UN ESERCIZIO PERSONALIZZATO")
            setPositiveButton("Registra"){dialog, which ->
                var kcal_salva = kcal.text.toString()
                var titolo = titolo.text.toString().trim()


            }
            setNegativeButton("Annulla"){ dialog, which ->
                Log.d("Main", "Negative button clicked")
            }
            setView(dialogLayout)
            show()
        }

    }

    private fun aggiungiProdotto(position : Int, bottone:String, id : String){
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.registrazione_rapida_layout, null)
        val kcal = dialogLayout.findViewById<EditText>(R.id.eT_kcal)
        val carbo = dialogLayout.findViewById<EditText>(R.id.eT_carb)
        val proteine = dialogLayout.findViewById<EditText>(R.id.eT_proteine)
        val grassi = dialogLayout.findViewById<EditText>(R.id.eT_grassi)
        val titolo = dialogLayout.findViewById<EditText>(R.id.eT_nomeEs)
        val btnAggiungiLista = dialogLayout.findViewById<Button>(R.id.btnAggiungi)
        val btnElimina = dialogLayout.findViewById<Button>(R.id.buttonElimina)
        val btnAggiungiDispensa = dialogLayout.findViewById<Button>(R.id.btnAggiungiDispensa)
        val layout_info = dialogLayout.findViewById<LinearLayout>(R.id.layout_info)
//        val layout_quantita  = dialogLayout.findViewById<LinearLayout>(R.id.layout_quantita)
        if(bottone == "clickItem"){
            btnAggiungiLista.text = "AGGIORNA\nNELLA LISTA"
            layout_info.visibility = View.GONE
            btnElimina.visibility = View.VISIBLE
            btnAggiungiDispensa.visibility = View.VISIBLE
            setText(arrayOf(titolo,kcal,carbo,grassi,proteine),position)

        }else{
            btnAggiungiLista.text = "AGGIUNGI\nALLA LISTA"
            btnElimina.visibility = View.GONE
            btnAggiungiDispensa.visibility = View.GONE
        }
        var flag_2 = false
        var flag=false
        btnAggiungiDispensa.setOnClickListener {
    //        layout_quantita.visibility = View.VISIBLE
            layout_info.visibility = View.GONE
            //val quantita = dialogLayout.findViewById<EditText>(R.id.etNumQuantita).text.toString().toDouble()
            //if(quantita != 0.0 && quantita.toString() != ""){
                model.setPastoOnDB(requireArguments().getString("bottone")!!,model.personalizzatiLiveData.value!![position].id,
                    model.personalizzatiLiveData.value!![position].image,model.personalizzatiLiveData.value!![position].nome,
                    model.personalizzatiLiveData.value!![position].calorie,model.personalizzatiLiveData.value!![position].proteine,
                    model.personalizzatiLiveData.value!![position].carboidrati,model.personalizzatiLiveData.value!![position].grassi, requireContext())

                //dialogLayout.visibility = View.GONE
            /*}else {
                if (flag_2)
                    Toast.makeText(requireContext(),"Per favore inserisci una quantità diversa da $quantita se desideri aggiungere il prodotto alla Dispensa",Toast.LENGTH_LONG).show()
                flag_2 = true
            }*/
            //flag=false

        }




        btnAggiungiLista.setOnClickListener {
            layout_info.visibility = View.VISIBLE
//            layout_quantita.visibility = View.GONE
            var kcal_salva = kcal.text.toString()
            var carbo_salva = carbo.text.toString()
            var proteine_salva = proteine.text.toString()
            var grassi_salva = grassi.text.toString()
            var titolo = titolo.text.toString().trim()
            if(kcal_salva != "" && carbo_salva != "" && proteine_salva != "" && grassi_salva != "" && titolo != "" &&
                kcal_salva.toDouble() != 0.0 && (carbo_salva.toDouble() != 0.0 || proteine_salva.toDouble() != 0.0 && grassi_salva.toDouble() != 0.0)) {
                if(bottone == "clickItem"){
                    if (flag && valueAreChanged(position,kcal_salva,carbo_salva, proteine_salva,grassi_salva,titolo)){
                        model.updatePersonalizzatoOnDB(
                            id,requireArguments().getString("bottone")!!, titolo, kcal_salva.toDouble(),
                            proteine_salva.toDouble(), carbo_salva.toDouble(), grassi_salva.toDouble(), requireContext())
                        flag=false
                        dialogLayout.visibility = View.GONE
                    }else{
                        Toast.makeText(requireContext(),"Cambia i valori prima di salvare",Toast.LENGTH_LONG).show()
                        flag=true
                    }
                }else {
                    model.setPersonalizzatiOnDB(
                        requireArguments().getString("bottone")!!, titolo, kcal_salva.toDouble(),
                        proteine_salva.toDouble(), carbo_salva.toDouble(), grassi_salva.toDouble(), requireContext()
                    )
                    dialogLayout.visibility = View.GONE
                }
                model.getPersonalizzati(requireArguments().getString("bottone")!!)
            }
            else
                Toast.makeText(requireContext(),"Per favore completa tutti i campi o modifica i valori prima di salvare",Toast.LENGTH_LONG).show()
            flag_2 = false
        }

        builder.setNegativeButton("Esci"){ dialog, which ->
            dialog.dismiss()
        }

        btnElimina.setOnClickListener {
            model.deletePersonalizzato(id, requireArguments().getString("bottone")!!,requireContext())
            dialogLayout.visibility = View.GONE
            flag=false
            flag_2=false
        }



        builder.setView(dialogLayout)
        builder.show()


    }

    private fun valueAreChanged(position: Int, kcalSalva: String, carboSalva: String, proteineSalva: String, grassiSalva: String, titolo: String): Boolean {
        return (kcalSalva.toDouble() != model.personalizzatiLiveData.value!![position].calorie
                || carboSalva.toDouble() != model.personalizzatiLiveData.value!![position].carboidrati
                || proteineSalva.toDouble() != model.personalizzatiLiveData.value!![position].proteine
                || grassiSalva.toDouble() != model.personalizzatiLiveData.value!![position].grassi)

    }


    private fun setText(edit : Array<EditText>,position : Int){
        edit[0].setText(model.personalizzatiLiveData.value!![position].nome)
        edit[1].setText(model.personalizzatiLiveData.value!![position].calorie.toString())
        edit[2].setText(model.personalizzatiLiveData.value!![position].carboidrati.toString())
        edit[3].setText(model.personalizzatiLiveData.value!![position].grassi.toString())
        edit[4].setText(model.personalizzatiLiveData.value!![position].proteine.toString())
    }


}
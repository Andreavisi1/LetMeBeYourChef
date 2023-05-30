package com.example.letmebeyourchef.autenticazione

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.FragmentStileVitaBinding
import com.example.letmebeyourchef.model.Utente



class StileVitaFragment : Fragment() {
    lateinit var binding: FragmentStileVitaBinding
    val utente = Utente()
    var checked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_stile_vita, container, false)
        binding.liniette.isVisible = true
        return binding.root
    }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)
       utente.LAF = 0.0
       utente.agonistico = false
       binding.imageView16.isVisible = false
       var listener = View.OnClickListener { v ->
           when(v.id){
               R.id.rB_sedentario -> utente.LAF = 1.2

               R.id.rB_pocoattivo -> utente.LAF = 1.375

               R.id.rB_attivo -> utente.LAF = 1.55

               R.id.rB_moltoattivo -> utente.LAF = 1.725
           }
       }

       binding.rBAttivo.setOnClickListener(listener)
       binding.rBMoltoattivo.setOnClickListener(listener)
       binding.rBPocoattivo.setOnClickListener(listener)
       binding.rBSedentario.setOnClickListener(listener)

       // Get radio group selected status and text using button click event
       binding.btAvantiObb.setOnClickListener { view : View->
           // Get the checked radio button id from radio group
           if (utente.LAF != 0.0) {
               val action = StileVitaFragmentDirections.actionObbiettivoFragmentToSessoFragment(utente)
               view.findNavController().navigate(action) //navigazione da obiettivo a sesso
           } else {
               // If no radio button checked in this radio group
               Toast.makeText(context, "Per favore, seleziona un'opzione",Toast.LENGTH_SHORT).show()
           }
       }

       binding.sBAgonistico.setOnCheckedChangeListener { _, isChecked ->
           binding.imageView16.isVisible = isChecked
           checked = isChecked
       }

   }
    override fun onStop() {
        super.onStop()
        binding.GruppoRadioObbiettivo.clearCheck()
        utente.agonistico = checked
        binding.imageView16.isVisible = checked
        binding.sBAgonistico.isChecked = false
        binding.imageView16.isVisible = false
    }

}



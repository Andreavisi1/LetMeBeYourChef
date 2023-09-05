package com.example.letmebeyourchef.autenticazione

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.FragmentSessoBinding
import com.example.letmebeyourchef.model.Utente

class SessoFragment : Fragment() {
    lateinit var binding: FragmentSessoBinding
    val utente = Utente()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sesso, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        utente.sesso=""
        var listener = View.OnClickListener { v ->
            when(v.id){
                R.id.rB_uomo -> utente.sesso = "Man"
                R.id.rB_donna -> utente.sesso = "Woman"
            }
        }

        binding.rBUomo.setOnClickListener(listener)
        binding.rBDonna.setOnClickListener(listener)

        // Get radio group selected status and text using button click event
        binding.btAvantiSesso.setOnClickListener { view : View->
            // Get the checked radio button id from radio group
            if (utente.sesso != "") {
                val action = SessoFragmentDirections.actionSessoFragmentToDatiPersonaliFragment(utente)
                view.findNavController().navigate(action) //navigazione

            } else {
                // If no radio button checked in this radio group
                Toast.makeText(context, "Please, select an option",Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onStop() {
        super.onStop()
        binding.GruppoRadioSesso.clearCheck()
    }


}
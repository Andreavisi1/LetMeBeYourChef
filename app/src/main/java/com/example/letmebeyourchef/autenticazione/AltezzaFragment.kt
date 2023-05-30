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
import androidx.navigation.fragment.navArgs
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.FragmentAltezzaBinding


class AltezzaFragment : Fragment() {
    lateinit var binding: FragmentAltezzaBinding
    val args: AltezzaFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_altezza, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var utente = args.utente
        utente.altezza=0
        binding.imageView36.isVisible = utente.agonistico
        binding.btAvantiAltezza.setOnClickListener {
            var altezza = binding.eTAltezza.text.toString()
            if(altezza != ""){
                if(altezza.toInt()<=300 && altezza.toInt()>=130){
                    utente.altezza = altezza.toInt()
                    val action =AltezzaFragmentDirections.actionAltezzaFragmentToPesoAttualeFragment(utente)
                    view.findNavController().navigate(action)
                }else{
                    Toast.makeText(context, "Inserisci un valore compreso tra 300 e 130",Toast.LENGTH_LONG).show()
                    binding.eTAltezza.setText("")
                }
            }else
                Toast.makeText(context, "Per favore, completa il campo", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onStop() {
        super.onStop()
        binding.eTAltezza.setText("")
    }


    
}
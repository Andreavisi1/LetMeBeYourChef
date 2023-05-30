package com.example.letmebeyourchef.autenticazione

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.FragmentPesoAttualeBinding
import com.example.letmebeyourchef.model.Utente


class PesoAttualeFragment : Fragment() {
    lateinit var binding: FragmentPesoAttualeBinding
    private lateinit var utente: Utente
    val args: PesoAttualeFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_peso_attuale, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        utente = args.utente
        utente.peso_attuale=0.0
        binding.imageView44.isVisible = utente.agonistico
        Log.d("Utente",utente.toString())
        val action = setNavigation(utente.agonistico)
            binding.btAvantiPesoAttuale.setOnClickListener {
                val pesoAttuale = binding.eTPesoAttuale.text.toString()
                if (pesoAttuale != "") {
                    if(pesoAttuale.toDouble()<=200.0 && pesoAttuale.toDouble()>=30.0){
                        utente.peso_attuale = pesoAttuale.toDouble()
                        view.findNavController().navigate(action)
                    }else{
                        Toast.makeText(context, "Per favore inserisci un peso compreso tra 30 Kg e 200 Kg", Toast.LENGTH_SHORT).show()
                        binding.eTPesoAttuale.setText("")
                    }
                } else
                    Toast.makeText(context, "Per favore, completa il campo", Toast.LENGTH_SHORT).show()
            }
    }
    fun setNavigation(isAgonista: Boolean): NavDirections {
        if(isAgonista) return PesoAttualeFragmentDirections.actionPesoAttualeFragmentToPesoObbFragment(utente)
        return PesoAttualeFragmentDirections.actionPesoAttualeFragmentToRegisterActivity(utente)
    }

    override fun onStop() {
        super.onStop()
        binding.eTPesoAttuale.setText("")
    }

}

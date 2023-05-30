package com.example.letmebeyourchef.autenticazione

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.FragmentSportBinding
import com.example.letmebeyourchef.model.Utente
import kotlinx.android.synthetic.main.fragment_sport.*


class SportFragment : Fragment() {
    lateinit var binding: FragmentSportBinding
    private lateinit var utente: Utente
    private val args: SportFragmentArgs by navArgs()
    private lateinit var cbSport : ArrayList<RadioButton>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sport, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cbSport = arrayListOf(binding.cBCalcio,binding.cBBasket ,binding.cBBaseball, binding.cBNuoto, binding.cBJudo,
            binding.cBGolf, binding.cBTennis, binding.cBPingPong, binding.cBFootball,
            binding.cBCricket, binding.cBRugby, binding.cBKarate, binding.cBHockey, binding.cBGinnasticaArt,
            binding.cBGinnasticaRitm,  binding.cBAtletica,  binding.cBCiclismo,  binding.cBPallavolo,
            binding.cBPattinaggio,  binding.cBPallanuoto,  binding.cBAltro)

        utente = args.utente
        utente.sport=""
        takeChecked()
        binding.btAvantiPesoObb.setOnClickListener {
            if(utente.sport == "")
                Toast.makeText(context,"Per favore, completa il campo", Toast.LENGTH_SHORT).show()
            else {
                val intent = Intent(context, RegisterActivity::class.java)
                intent.putExtra("utente", utente)
                startActivity(intent)
            }
        }
    }

    private fun takeChecked() {
        var listener = View.OnClickListener { v ->
            when(v.id){
                R.id.cB_calcio -> utente.sport = cB_calcio.text.toString()
                R.id.cB_basket -> utente.sport = cB_basket.text.toString()
                R.id.cB_baseball -> utente.sport = cB_baseball.text.toString()
                R.id.cB_nuoto -> utente.sport = cB_nuoto.text.toString()
                R.id.cB_judo -> utente.sport = cB_judo.text.toString()
                R.id.cB_golf -> utente.sport = cB_golf.text.toString()
                R.id.cB_tennis-> utente.sport = cB_tennis.text.toString()
                R.id.cB_ping_pong -> utente.sport = cB_ping_pong.text.toString()
                R.id.cB_football -> utente.sport = cB_football.text.toString()
                R.id.cB_cricket -> utente.sport = cB_cricket.text.toString()
                R.id.cB_karate -> utente.sport = cB_karate.text.toString()
                R.id.cB_hockey -> utente.sport = cB_hockey.text.toString()
                R.id.cB_ginnastica_art -> utente.sport = cB_ginnastica_art.text.toString()
                R.id.cB_ginnastica_ritm -> utente.sport = cB_ginnastica_ritm.text.toString()
                R.id.cB_rugby -> utente.sport = cB_rugby.text.toString()
                R.id.cB_atletica -> utente.sport = cB_atletica.text.toString()
                R.id.cB_ciclismo -> utente.sport = cB_ciclismo.text.toString()
                R.id.cB_pallavolo -> utente.sport = cB_pallavolo.text.toString()
                R.id.cB_pattinaggio -> utente.sport = cB_pattinaggio.text.toString()
                R.id.cB_pallanuoto -> utente.sport = cB_pallanuoto.text.toString()
                R.id.cB_altro -> utente.sport = cB_altro.text.toString()

            }
        }

        for(radioButton in cbSport){
            radioButton.setOnClickListener(listener)
        }

    }

    override fun onStop() {
        super.onStop()
        binding.GruppoRadioSport.clearCheck()
    }


}
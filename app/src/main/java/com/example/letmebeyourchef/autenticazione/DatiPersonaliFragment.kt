package com.example.letmebeyourchef.autenticazione

import android.app.DatePickerDialog
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
import com.example.letmebeyourchef.databinding.FragmentDatiPersonaliBinding
import java.time.LocalDate


class DatiPersonaliFragment : Fragment() {
    lateinit var binding : FragmentDatiPersonaliBinding
    val args: DatiPersonaliFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_dati_personali, container, false)
        return binding.root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var utente = args.utente
        utente.data_nascita=""
        utente.nome=""
        utente.cognome=""
        //calendario
        val oggi = LocalDate.now()
        val minima = LocalDate.of(oggi.year-15,oggi.monthValue,oggi.dayOfMonth)

        var date= LocalDate.now()
        var data_selezionata = date.toString()
        var year = oggi.year
        var month = oggi.monthValue-1
        var day = oggi.dayOfMonth



        //selezione data
        binding.tvDataNascita.setOnClickListener{
            val dpd = DatePickerDialog(requireContext(), { view, mYear, mMonth, mDay ->
                date = LocalDate.of(mYear,(mMonth+1),mDay)
                year = date.year
                month = date.monthValue-1
                day = date.dayOfMonth
                if (date.year <= minima.year && date<=oggi){
                    binding.tvDataNascita.text = "$mDay-"+(mMonth +1)+"-$mYear"
                    data_selezionata = date.toString()
                    binding.tvDataNascita.error = null
                    binding.btAvantiDati.isEnabled=true
                } else{
                    binding.tvDataNascita.text = ""
                    binding.tvDataNascita.error = "Seleziona una data corretta"
                    binding.btAvantiDati.isEnabled=false
                }

            }, year, month, day)
            dpd.show()
        }

        binding.btAvantiDati.setOnClickListener{
            if (binding.tvDataNascita.text != "" )
                utente.data_nascita = data_selezionata
            else binding.tvDataNascita.error = "Seleziona una data corretta"
            utente.nome = binding.tEName.text.toString()
            utente.cognome = binding.tESurname.text.toString()
            if(utente.data_nascita != "" && utente.nome != "" && utente.cognome != ""){
                val action = DatiPersonaliFragmentDirections.actionDatiPersonaliFragmentToIntolleranzeFragment(utente)
                view.findNavController().navigate(action)
            }
            else
                Toast.makeText(context, "Please, correctly fill all the required fields",Toast.LENGTH_SHORT).show()

        }



    }

    override fun onStop() {
        super.onStop()
        binding.tvDataNascita.text=""
        binding.tEName.setText("")
        binding.tESurname.setText("")
    }


}

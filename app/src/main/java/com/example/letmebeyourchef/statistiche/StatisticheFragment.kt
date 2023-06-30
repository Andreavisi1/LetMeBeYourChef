package com.example.letmebeyourchef.statistiche

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.room.InvalidationTracker
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.FragmentStatisticheBinding
import com.example.letmebeyourchef.model.Diario
import kotlinx.android.synthetic.main.fragment_statistiche.*
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class StatisticheFragment : Fragment() {

    private var model = StatisticheViewModel()
    private lateinit var binding: FragmentStatisticheBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_statistiche, container, false)
        //aggiornamento automatico view
        binding.viewModel = model
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDate()




        binding.btnFiltra.setOnClickListener{
            binding.progress.visibility=View.VISIBLE
            Toast.makeText(context,"Sto sfogliando le tue pagine...",Toast.LENGTH_SHORT).show()
            binding.tVDataFine.error = null
            binding.tVDataiInizio.error = null
            if(binding.tVDataFine.text == "" && binding.tVDataiInizio.text ==""){
                val data_inizio = "2022-10-01"
                val data_fine = LocalDate.now().toString()
                model.ottieniStatistihe(data_inizio,data_fine)
            }else{
                val data_inizio = binding.tVDataiInizio.text.toString()
                val data_fine = binding.tVDataFine.text.toString()
                model.ottieniStatistihe(data_inizio,data_fine)
            }



        }

        binding.btnReset.setOnClickListener{
            binding.tVDataFine.text = ""
            binding.tVDataiInizio.text =""
            binding.tVDataiInizio.error = null
            binding.tVDataFine.error = null
        }

        val getObserver = Observer<Boolean> {
            binding.progress.visibility = View.GONE
            Toast.makeText(context,"Filtraggio completato",Toast.LENGTH_SHORT).show()
        }

        model.getStatistiche.observe(viewLifecycleOwner,getObserver)


    }

    private fun setDate(){
        //calendario
        var date_fine = LocalDate.now()
        var date_inizio = LocalDate.of(date_fine.year, date_fine.month, date_fine.dayOfMonth)
        var oggi = LocalDate.now()
        var year = oggi.year
        var month = oggi.monthValue - 1
        var day = oggi.dayOfMonth
        var year_inizio = year
        var month_inizio = month
        var day_inizio = day

        //selezione data fine
        binding.tVDataFine.setOnClickListener {

            val dpd = DatePickerDialog(requireContext(), { view, mYear, mMonth, mDay ->
                date_fine = LocalDate.of(mYear, (mMonth + 1), mDay)
                year = date_fine.year
                month = date_fine.monthValue - 1
                day = date_fine.dayOfMonth
                if (date_fine <= oggi) {
                    if (date_fine <= date_inizio) {
                        binding.tVDataFine.text = date_fine.toString()
                        binding.tVDataiInizio.text = date_fine.toString()
                        date_inizio=date_fine
                        year_inizio = date_fine.year
                        month_inizio = date_fine.monthValue - 1
                        day_inizio = date_fine.dayOfMonth
                        binding.tVDataFine.error = null
                        binding.tVDataiInizio.error = null
                    } else {
                        binding.tVDataFine.text = date_fine.toString()
                        binding.tVDataFine.error = null
                        binding.tVDataiInizio.error = null
                    }
                } else {
                    binding.tVDataFine.error = "Seleziona una data corretta."
                    date_fine=oggi
                    date_inizio=oggi
                    binding.tVDataFine.text = ""
                    binding.tVDataiInizio.text = ""
                }
            }, year, month, day)
            dpd.show()
            Log.d("Data_Fine", date_fine.toString())
        }
            //selezione data inizio
            binding.tVDataiInizio.setOnClickListener {

                val dpd = DatePickerDialog(requireContext(), { view, mYear, mMonth, mDay ->
                    date_inizio = LocalDate.of(mYear, (mMonth + 1), mDay)
                    year_inizio = date_inizio.year
                    month_inizio = date_inizio.monthValue - 1
                    day_inizio = date_inizio.dayOfMonth
                    if (date_inizio <= oggi) {
                        if (binding.tVDataFine.text == "") {
                            binding.tVDataFine.text = date_inizio.toString()
                            date_fine = date_inizio
                            year = date_inizio.year
                            month = date_inizio.monthValue - 1
                            day = date_inizio.dayOfMonth
                            binding.tVDataiInizio.error = null
                            binding.tVDataFine.error = null
                        }
                        if (date_inizio >= date_fine) {
                            binding.tVDataFine.text = date_inizio.toString()
                            date_fine = date_inizio
                            year = date_inizio.year
                            month = date_inizio.monthValue - 1
                            day = date_inizio.dayOfMonth
                            binding.tVDataiInizio.error = null
                            binding.tVDataFine.error = null
                        }
                        binding.tVDataiInizio.text = date_inizio.toString()
                        binding.tVDataiInizio.error = null
                        binding.tVDataFine.error = null
                    } else {
                        binding.tVDataiInizio.error = "Seleziona una data corretta."
                        date_inizio=oggi
                        date_fine=oggi
                        binding.tVDataiInizio.text = ""
                        binding.tVDataFine.text = ""

                    }
                }, year_inizio, month_inizio, day_inizio)
                dpd.show()
                Log.d("Data_Inizio", date_inizio.toString())
            }



    }



}

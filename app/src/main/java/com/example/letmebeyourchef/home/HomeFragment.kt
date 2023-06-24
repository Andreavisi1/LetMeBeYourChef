package com.example.letmebeyourchef.home

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.aggiungi_pasto.AggiungiActivity
import com.example.letmebeyourchef.aggiungi_esercizio.AggiungiEsercizioActivity
import com.example.letmebeyourchef.databinding.FragmentDiarioBinding
import com.example.letmebeyourchef.esercizio.EsercizioRDUActivity
import com.example.letmebeyourchef.model.Diario
import com.example.letmebeyourchef.model.Json_Parsing.Esercizio
import com.example.letmebeyourchef.model.Pasto
import com.example.letmebeyourchef.pasto.PastoActivity
import com.example.letmebeyourchef.RequestManager
import com.example.letmebeyourchef.adapters.RicetteRandomAdapter
import com.example.letmebeyourchef.databinding.FragmentHomeBinding
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import kotlinx.android.synthetic.main.fragment_diario.*
import kotlinx.android.synthetic.main.win_layout_dialog.*

class HomeFragment : Fragment() {

    var dialog: ProgressDialog? = null
    var manager: RequestManager? = null
    var ricetteRandomAdapter: RicetteRandomAdapter? = null
    lateinit var recyclerView: RecyclerView
    lateinit var spinner: Spinner
    var tags: MutableList<String> = ArrayList()
    lateinit var searchView: SearchView

    private lateinit var binding: FragmentHomeBinding
    private val model = HomeViewModel()

    private lateinit var intent : Intent
    private lateinit var glasses : Array<ImageView>
    private var contatore = 0
    private var flag_congratulazioni = false
    private var acqua = arrayListOf(false,false,false,false,false,false,false,false)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        //aggiornamento automatico view
        binding.viewModel = model
        binding.lifecycleOwner = this

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()
        //setDiario()



    }

    override fun onPause() {
        super.onPause()
        //setDiario()

    }


    private fun startAnimation(glass : ImageView){
        glass.setBackgroundResource(R.drawable.filling_animation)
        val frameAnimation: AnimationDrawable = glass.background as AnimationDrawable
        frameAnimation.start()
    }



    @SuppressLint("ResourceAsColor")
    private fun openCongratulazioni() {
        var dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.win_layout_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(R.color.transparent))
        dialog.imageViewClose.setOnClickListener {
            dialog.dismiss()
        }
        dialog.btn_OK.setOnClickListener {
            dialog.dismiss()
        }
        Glide.with(requireContext())
            .load(R.raw.awards)
            .into(dialog.imageViewWin)
        dialog.show()
    }

    private fun vibrate(){
        val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrationEffect1 = VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.cancel()
        vibrator.vibrate(vibrationEffect1)
    }


}

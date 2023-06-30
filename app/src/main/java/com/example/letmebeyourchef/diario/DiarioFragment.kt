package com.example.letmebeyourchef.diario

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
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
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import kotlinx.android.synthetic.main.fragment_diario.*
import kotlinx.android.synthetic.main.win_layout_dialog.*

class DiarioFragment : Fragment() {

    private lateinit var binding: FragmentDiarioBinding
    private val model = DiarioViewModel()

    private lateinit var intent : Intent
    private lateinit var glasses : Array<ImageView>
    private var contatore = 0
    private var flag_congratulazioni = false

    private var acqua = arrayListOf(false,false,false,false,false,false,false,false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_diario, container, false)
        //aggiornamento automatico view
        binding.viewModel = model
        binding.lifecycleOwner = this

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        glasses = arrayOf(binding.glass1,binding.glass2,binding.glass3,binding.glass4,
                            binding.glass5,binding.glass6,binding.glass7,binding.glass8)

        onClickGlass()
        setOnclickPasti()

        model.getUserDiarioDB()

        val diarioObserver = Observer<Diario> {
            if (model.diario.value == null && contatore == 0) {
//                model.setDiarioOnDB()
                model.getUserDiarioDB()
            }else{
                if(contatore < 1) {
                    checkFullGlasses()
                    model.setAssunte()
                    model.setRimanenti()
                    model.setMacro()
                    contatore += 1
                }else {
                    contatore = 0
                }
            }
        }
        model.diario.observe(viewLifecycleOwner,diarioObserver)
        var contatoreProgressi = 0
        val diarioSettatoObserver = Observer<Boolean>{
            if(contatoreProgressi == 3) updateProgressBar(progress_calorie, pgBar_carboidrati, pgBar_proteine, pgBar_grassi)
            contatoreProgressi += 1
        }
        model.diarioSettato.observe(viewLifecycleOwner,diarioSettatoObserver)


    }

    override fun onDestroy() {
        super.onDestroy()
//        setDiario()



    }

    override fun onPause() {
        super.onPause()
//        setDiario()

    }


    private fun startAnimation(glass : ImageView){
        glass.setBackgroundResource(R.drawable.filling_animation)
        val frameAnimation: AnimationDrawable = glass.background as AnimationDrawable
        frameAnimation.start()
    }

    fun checkFullGlasses(){
        for(i in 0..7) {
            if (model.diario.value!!.acqua[i]) {
                acqua[i] = model.diario.value!!.acqua[i]
                startAnimation(glasses[i])
                if(i < 7)
                    glasses[i+1].setBackgroundResource(R.drawable.empty_glass_plus)
            }
        }
        checkAcquaBevuta()
    }

    private fun onClickGlass(){
        for(i in 0..7){
            glasses[i].setOnClickListener {
                if(acqua[i]){
                    glasses[i].setBackgroundResource(R.drawable.empty_glass_plus)
                    for(x in i..7){
                        if(x<7)
                            glasses[x+1].setBackgroundResource(R.drawable.empty_glass)
                        acqua[x] = false
                    }
                }else {
                    for(x in 0..i){
                        startAnimation(glasses[x])
                        acqua[x] = true
                    }
                    if(i < 7){
                        glasses[i+1].setBackgroundResource(R.drawable.empty_glass_plus)
                    }
                }
                checkAcquaBevuta()
            }
        }
    }

    private fun checkAcquaBevuta(){
        var litri_acqua = 0.0
        for (bicchiere in acqua)
            if(bicchiere)
                litri_acqua += 0.25
        model.setAcqua(litri_acqua)
        /*Per fare in modo che il messaggio di congratulazioni venga mostrato una sola volta nel caso di raggiungimento
        dell'obiettivo controllo che il totale sia a 2 litri, se ho gia mostrato il messaggio nella stesso ciclo di vita del fragment
        e se il settimo bicchiere d'acqua era gia presente al momento del caricamento del diario
         */
        if(litri_acqua == 2.0){
            binding.imageViewGoldMedal.isVisible = true
            if(!flag_congratulazioni && !model.diario.value!!.acqua[7]) {
                flag_congratulazioni = true
                openCongratulazioni()
            }
        }else{
            binding.imageViewGoldMedal.isVisible = false
        }

    }

    private fun updateProgressBar(progrssBar : CircularProgressBar,progressBarCarbo : ProgressBar,ProgressBarProteine : ProgressBar,ProgrssBarGrassi: ProgressBar){
        progressBarCarbo.max = model.carboidratiMax.value!!
        progressBarCarbo.progress = model.diario.value!!.carboidratiTot

        ProgressBarProteine.max = model.carboidratiMax.value!!
        ProgressBarProteine.progress = model.diario.value!!.proteineTot

        ProgrssBarGrassi.max = model.grassiMax.value!!
        ProgrssBarGrassi.progress = model.diario.value!!.grassiTot

        progrssBar.apply {
            progressMax = model.diario.value!!.fabbisogno.toFloat()
            setProgressWithAnimation(model.assunte.value!!.toFloat(), 1500)
            progressBarWidth = 7f
            backgroundProgressBarWidth = 5f
            roundBorder = true
            startAngle = 0f
            progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT

        }
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

    private fun setOnclickPasti(){
        intent =  Intent(context, AggiungiActivity::class.java)
        binding.colazione.setOnClickListener {
            intent.putExtra("bottone","COLAZIONE")
            startActivity(intent)
            requireActivity().finish()
        }
        binding.colazione.setOnLongClickListener{
            vibrate()
            openScelti("COLAZIONE")
            true
        }
        binding.pranzo.setOnClickListener {
            intent.putExtra("bottone","PRANZO")
            startActivity(intent)
            requireActivity().finish()
        }
        binding.pranzo.setOnLongClickListener{
            vibrate()
            openScelti("PRANZO")
            true
        }
        binding.cena.setOnClickListener {
            intent.putExtra("bottone","CENA")
            startActivity(intent)
            requireActivity().finish()
        }
        binding.cena.setOnLongClickListener{
            vibrate()
            openScelti("CENA")
            true
        }

        binding.spuntino.setOnClickListener {
            intent.putExtra("bottone","SPUNTINO")
            startActivity(intent)
            requireActivity().finish()
        }
        binding.spuntino.setOnLongClickListener{
            vibrate()
            openScelti("SPUNTINO")
            true
        }

        binding.esercizio.setOnClickListener {
            val intent_esercizio = Intent(requireContext(),AggiungiEsercizioActivity::class.java)
            startActivity(intent_esercizio)
        }
        binding.esercizio.setOnLongClickListener{
            vibrate()
            openSceltiEsercizio()
            true
        }
    }

    private fun openScelti(pasto: String){
        val builder= AlertDialog.Builder(requireContext())
        builder.create()
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.del_mod_selezionati_layout, null)
        model.getItemSelezionati(pasto)
        val rVSelezionati = dialogLayout.findViewById<RecyclerView>(R.id.recyclerViewSelezionati)
            rVSelezionati.layoutManager=LinearLayoutManager(requireContext())
            rVSelezionati.setHasFixedSize(true)
        val selezionatiObserver = Observer<List<Pasto>>{
            val adapter = MyAdapterSelezionati(model.selezionati.value!! as ArrayList<Pasto>)
            rVSelezionati.adapter = adapter
            adapter.setOnItemClickListener(object : MyAdapterSelezionati.onItemClickListener{
                override fun onItemClick(position: Int) {
                    val intent = prepareIntent(position, pasto)
                    startActivity(intent)
                    requireActivity().finish()
                }
            })
        }
        model.selezionati.observe(viewLifecycleOwner, selezionatiObserver)
        with(builder){
            setTitle("$pasto")
            setView(dialogLayout)
            show()
        }
    }

    private fun openSceltiEsercizio(){
        val builder= AlertDialog.Builder(requireContext())
        builder.create()
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.del_mod_selezionati_layout, null)
        model.getEsercizioSelezionati()
        val rVSelezionati = dialogLayout.findViewById<RecyclerView>(R.id.recyclerViewSelezionati)
        rVSelezionati.layoutManager=LinearLayoutManager(requireContext())
        rVSelezionati.setHasFixedSize(true)
        val eserciziSelezionatiObserver = Observer<List<Esercizio>>{
            val adapter = MyAdapterEserciziSel(model.esercizioSelezionati.value!! as ArrayList<Esercizio>)
            rVSelezionati.adapter = adapter
            adapter.setOnItemClickListener(object : MyAdapterEserciziSel.onItemClickListener{
                override fun onItemClick(position: Int) {
                    val intent = prepareIntentEsercizio(position)
                    startActivity(intent)
                    requireActivity().finish()
                }
            })
        }
        model.esercizioSelezionati.observe(viewLifecycleOwner, eserciziSelezionatiObserver)
        with(builder){
            setTitle("ESERCIZI")
            setView(dialogLayout)
            show()
        }
    }



/*    private fun setDiario(){
        model.getUserDiarioDB()
        model.setDiarioOnDB(model.diario.value!!.grassiTot, model.diario.value!!.proteineTot,
            model.diario.value!!.carboidratiTot, model.diario.value!!.chiloCalorieEsercizio,
            model.diario.value!!.chiloCalorieColazione, model.diario.value!!.chiloCaloriePranzo,
            model.diario.value!!.chiloCalorieCena, model.diario.value!!.chiloCalorieSpuntino,
            acqua)
    }*/



    private fun prepareIntent(position: Int, pasto: String) : Intent{
        var intent = Intent(requireContext(), PastoActivity::class.java)
        intent.putExtra("tipologiaPasto", pasto)
        intent.putExtra("id",model.selezionati.value!![position].id)
        intent.putExtra("kcal_pasto", model.selezionati.value!![position].calorie.toString())
        intent.putExtra("carboidrati", model.selezionati.value!![position].carboidrati.toString())
        intent.putExtra("proteine", model.selezionati.value!![position].proteine.toString())
        intent.putExtra("grassi", model.selezionati.value!![position].grassi.toString())
        intent.putExtra("image", model.selezionati.value!![position].image)
        intent.putExtra("prodotto", model.selezionati.value!![position].nome)
        intent.putExtra("quantità", model.selezionati.value!![position].quantita.toString())
        return intent
    }

    private fun prepareIntentEsercizio(position : Int) : Intent{
        var intent = Intent(requireContext(), EsercizioRDUActivity::class.java)
        intent.putExtra("id",model.esercizioSelezionati.value!![position].id)
        intent.putExtra("kcal_h", model.esercizioSelezionati.value!![position].calorieOra.toString())
        intent.putExtra("nome", model.esercizioSelezionati.value!![position].nome)
        intent.putExtra("durata",  model.esercizioSelezionati.value!![position].durata)
        return intent

    }

    private fun vibrate(){
        val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrationEffect1 = VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.cancel()
        vibrator.vibrate(vibrationEffect1)
    }


}

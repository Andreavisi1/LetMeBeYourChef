package com.example.letmebeyourchef.profilo

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.ActivityProfiloBinding
import com.example.letmebeyourchef.home.HomeActivity
import com.example.letmebeyourchef.model.Utente
import kotlinx.android.synthetic.main.riautenticazione_layout.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

class ProfiloActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding : ActivityProfiloBinding
    private val model = ProfiloViewModel()

    lateinit var sessoSpinner: Spinner
//    lateinit var stileDiVitaSpinner: Spinner
    lateinit var intolleranzeSpinner: Spinner
    lateinit var agonisticoSwitch: Switch

    private var sessoS = arrayOf<String>("Man", "Woman")
//    private var stile = arrayOf<String>("Sedentario","Poco attivo", "Attivo", "Molto attivo")
    private var intolleranze = arrayOf<String>("Dairy","Gluten", "Peanut", "Grain", "Seafood","Egg","Sesame","Shellfish",
                                "Soy","Sulfite","Tree Nut","Wheat", "Other")


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profilo)


        model.getDataProfilo()

        //aggiornamento automatico view
        binding.viewModel = model
        binding.lifecycleOwner = this


        //L'utente non può cambiare email perchè questo per come
        // è stato impostato il db comporterebbe una perdita di tutti i dati
        binding.eTCambioEmail.isEnabled=false

        //Di seguito inizializziamo gli spinner
        sessoSpinner = binding.sWSesso
//        stileDiVitaSpinner = binding.sWStileDiVita
        intolleranzeSpinner = binding.sWIntolerances
        agonisticoSwitch = binding.switchAgonistico

        agonisticoSwitch.setOnCheckedChangeListener{ _, isChecked ->
            binding.selezioneIntolleranze.isVisible = isChecked
        }



        //Diamo ad ogni spinner il suo selected listener
        sessoSpinner.onItemSelectedListener = this
//        stileDiVitaSpinner.onItemSelectedListener = this
        intolleranzeSpinner.onItemSelectedListener = this


        val adapterSesso: ArrayAdapter<CharSequence> =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, sessoS)
//        val adapterStile: ArrayAdapter<CharSequence> =
//            ArrayAdapter(this, android.R.layout.simple_spinner_item, stile)
        val adapterIntolleranze: ArrayAdapter<CharSequence> =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, intolleranze)

        // Di seguito settiamo il dop down adapter
        adapterSesso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        adapterStile.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterIntolleranze.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        //Di seguito associamo gli adapter
        sessoSpinner.adapter = adapterSesso
//        stileDiVitaSpinner.adapter = adapterStile
        intolleranzeSpinner.adapter = adapterIntolleranze

        val profiloObserver = Observer<Utente> {
            //setto i miei spinner con i valori del db
            setSpinner(adapterSesso, adapterIntolleranze)
            //setto lo switcher con il valore del db
//            agonisticoSwitch.isChecked = model.profilo.value!!.agonistico

/*            //se lo switch è true faccio vedere la selezione dello sport
            binding.selezioneSport.isVisible = model.profilo.value!!.agonistico*/

            //calendario
            val oggi = LocalDate.now()
            val minima = LocalDate.of(oggi.year-15,oggi.monthValue,oggi.dayOfMonth)
            var date = LocalDate.parse(model.profilo.value!!.data_nascita)
            var data_selezionata = LocalDate.parse(model.profilo.value!!.data_nascita).toString()
            var year = date.year
            var month = (date.monthValue - 1)
            var day = date.dayOfMonth

            //selezione data
            binding.tVDataNascita.setOnClickListener {
                val dpd = DatePickerDialog(this, { view, mYear, mMonth, mDay ->
                    date = LocalDate.of(mYear,(mMonth+1),mDay)
                    year = date.year
                    month = (date.monthValue - 1)
                    day = date.dayOfMonth
                    if(date.year <= minima.year && date <= oggi){
                        binding.tVDataNascita.text = "$mDay-"+(mMonth +1)+"-$mYear"
                        data_selezionata = date.toString()
                        binding.tVDataNascita.error = null
                        binding.btnSalva.isEnabled=true
                    }else{
                        binding.tVDataNascita.text = ""
                        binding.tVDataNascita.error = "Select a correct date"
                        binding.btnSalva.isEnabled=false
                    }

                }, year, month, day)
                dpd.show()
            }

            //pulsante cambia password
            binding.btnCambioPass.setOnClickListener {
                val email = model.profilo.value!!.email
                lifecycleScope.launch {
                    val task = model.changePassword(email)
                    task.addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                this@ProfiloActivity,
                                "The link to change your password has been sent to the specified e-mail!",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(
                                this@ProfiloActivity,
                                "Unregistered or incorrect e-mail",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }

            binding.btnSalva.setOnClickListener{
                val nome_up = binding.eTNome.text.toString().trim()
                val cognome_up = binding.etCognome.text.toString().trim()
                val email_up = binding.eTCambioEmail.text.toString().trim()
                /*when(binding.sWStileDiVita.selectedItem.toString()){
                    "Sedentario" -> LAF_up = 1.2
                    "Poco attivo" -> LAF_up = 1.375
                    "Attivo" -> LAF_up = 1.55
                    "Molto attivo" -> LAF_up =1.725
                }*/
                val data_nascita_up = data_selezionata
                val sesso_up = binding.sWSesso.selectedItem.toString()
                var intolleranze_up = ""
                binding.sWIntolerances.selectedItem.toString()
                if(nome_up != "" && cognome_up != "" && email_up != "" && sesso_up != ""){
                        model.updateAuthUtenteOnDB(nome_up, cognome_up, email_up,sesso_up,data_nascita_up,intolleranze_up,this)
                }
                else
                    Toast.makeText(this,"Please fill all the required fields",Toast.LENGTH_LONG).show()

            }

        }
        model.profilo.observe(this,profiloObserver)

        val diarioUpdatedObserver = Observer<Boolean> {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        model.diarioUpdated.observe(this,diarioUpdatedObserver)

}


    private fun setSpinner(
        adapterSesso: ArrayAdapter<CharSequence>,
//        adapterStile: ArrayAdapter<CharSequence>,
        adapterIntolleranze: ArrayAdapter<CharSequence>
    ) {
        // on below line we are creating a variable to which we have to set our spinner item selected.
        val selectionSesso = model.profilo.value!!.sesso
//        var selectionStile = ""
        val selectionIntolleranze = model.profilo.value!!.intolleranze

        // on below line we are getting the position  the item by the item name in our adapter.
        val spinnerPositionSesso: Int = adapterSesso.getPosition(selectionSesso)
//        val spinnerPositionStile: Int = adapterStile.getPosition(selectionStile)
        val spinnerPositionIntolleranze: Int = adapterIntolleranze.getPosition(selectionIntolleranze)

        // on below line we are setting selection for our spinner to spinner position.
        sessoSpinner.setSelection(spinnerPositionSesso)
//        stileDiVitaSpinner.setSelection(spinnerPositionStile)
        intolleranzeSpinner.setSelection(spinnerPositionIntolleranze)

    }



    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HomeActivity::class.java))
        finish()

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

}
package com.example.letmebeyourchef.profilo

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.ActivityProfiloBinding
import com.example.letmebeyourchef.home.HomeActivity
import com.example.letmebeyourchef.model.Utente
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.android.synthetic.main.add_delete_intolleranze_layout.*
import kotlinx.android.synthetic.main.riautenticazione_layout.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

class ProfiloActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityProfiloBinding
    private val model = ProfiloViewModel()
    var dialog: ProgressDialog? = null


    lateinit var sessoSpinner: Spinner

    var intolleranze_utente: MutableList<String>? = ArrayList()
    private lateinit var cbIntolleranze: ArrayList<CheckBox>

    private var sessoS = arrayOf<String>("Man", "Woman")


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profilo)

        dialog = ProgressDialog(this)
        dialog!!.setTitle("Remembering who you are...")
        dialog!!.show()

        model.getDataProfilo()

        //aggiornamento automatico view
        binding.viewModel = model
        binding.lifecycleOwner = this

        //L'utente non può cambiare email perchè questo per come
        // è stato impostato il db comporterebbe una perdita di tutti i dati
        binding.eTCambioEmail.isEnabled = false

        //Di seguito inizializziamo lo spinner
        sessoSpinner = binding.sWSesso

        //Diamo allo spinner il suo selected listener
        sessoSpinner.onItemSelectedListener = this

        val adapterSesso: ArrayAdapter<CharSequence> =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, sessoS)

        // Di seguito settiamo il dop down adapter
        adapterSesso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        //Di seguito associamo l'adapter
        sessoSpinner.adapter = adapterSesso

        val profiloObserver = Observer<Utente> {
            //setto lo spinner con i valori del db
            setSpinner(adapterSesso)

            //calendario
            val oggi = LocalDate.now()
            val minima = LocalDate.of(oggi.year - 15, oggi.monthValue, oggi.dayOfMonth)
            var date = LocalDate.parse(model.profilo.value!!.data_nascita)
            var data_selezionata = LocalDate.parse(model.profilo.value!!.data_nascita).toString()
            var year = date.year
            var month = (date.monthValue - 1)
            var day = date.dayOfMonth

            //selezione data
            binding.tVDataNascita.setOnClickListener {
                val dpd = DatePickerDialog(this, { view, mYear, mMonth, mDay ->
                    date = LocalDate.of(mYear, (mMonth + 1), mDay)
                    year = date.year
                    month = (date.monthValue - 1)
                    day = date.dayOfMonth
                    if (date.year <= minima.year && date <= oggi) {
                        binding.tVDataNascita.text = "$mDay-" + (mMonth + 1) + "-$mYear"
                        data_selezionata = date.toString()
                        binding.tVDataNascita.error = null
                        binding.btnSalva.isEnabled = true
                    } else {
                        binding.tVDataNascita.text = ""
                        binding.tVDataNascita.error = "Select a correct date"
                        binding.btnSalva.isEnabled = false
                    }

                }, year, month, day)
                dpd.show()
            }

            //pulsante cambia password
            if (GoogleSignIn.getLastSignedInAccount(this) != null) {
                binding.btnCambioPass.isEnabled = false
                Toast.makeText(this, "User has signed in with Google, so it's not possible to change password", Toast.LENGTH_SHORT).show()

            }

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

                //pulsante cambia intolleranze

                binding.btnCambioIntolleranze.setOnClickListener {
                    openUpdateIntolleranzeDialog()
                }

                binding.btnSalva.setOnClickListener {
                    val nome_up = binding.eTNome.text.toString().trim()
                    val cognome_up = binding.etCognome.text.toString().trim()
                    val email_up = binding.eTCambioEmail.text.toString().trim()
                    val data_nascita_up = data_selezionata
                    val sesso_up = binding.sWSesso.selectedItem.toString()
                    val intolleranze_up = intolleranze_utente


                    Log.e(intolleranze_utente.toString(), "Intolleranze_up")


                    if (nome_up != "" && cognome_up != "" && email_up != "" && sesso_up != "") {
                        model.updateAuthUtenteOnDB(
                            nome_up, cognome_up, email_up, sesso_up, data_nascita_up,
                            intolleranze_up as ArrayList<String>?, this
                        )

                        val intent = Intent(applicationContext, HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                        finish()
                    } else
                        Toast.makeText(
                            this,
                            "Please fill all the required fields",
                            Toast.LENGTH_LONG
                        ).show()

                }

            }
            model.profilo.observe(this, profiloObserver)

            val dispensaUpdatedObserver = Observer<Boolean> {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }

            model.dispensaUpdated.observe(this, dispensaUpdatedObserver)

        }

        private fun setSpinner(
            adapterSesso: ArrayAdapter<CharSequence>,
        ) {
            // on below line we are creating a variable to which we have to set our spinner item selected.
            val selectionSesso = model.profilo.value!!.sesso

            // on below line we are getting the position  the item by the item name in our adapter.
            val spinnerPositionSesso: Int = adapterSesso.getPosition(selectionSesso)

            // on below line we are setting selection for our spinner to spinner position.
            sessoSpinner.setSelection(spinnerPositionSesso)

            dialog!!.dismiss()

        }

        @SuppressLint("ResourceAsColor")
        private fun openUpdateIntolleranzeDialog() {
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.add_delete_intolleranze_layout)
            dialog.window?.setBackgroundDrawable(ColorDrawable(R.color.transparent))

            cbIntolleranze = arrayListOf(
                dialog.cB_dairy_up,
                dialog.cB_gluten_up,
                dialog.cB_peanut_up,
                dialog.cB_grain_up,
                dialog.cB_seafood_up,
                dialog.cB_egg_up,
                dialog.cB_sesame_up,
                dialog.cB_shellfish_up,
                dialog.cB_soy_up,
                dialog.cB_sulfite_up,
                dialog.cB_tree_nut_up,
                dialog.cB_wheat_up,
                dialog.cB_altro_up
            )

            intolleranze_utente = model.profilo.value!!.intolleranze!!
            for (checkBox in cbIntolleranze) {
                if (intolleranze_utente!!.contains(checkBox.text) == true) checkBox.isChecked = true
            }

            Log.e(model.profilo.value!!.intolleranze.toString(), "INTOLLERANZE")

            dialog.btn_save_intolleranze.setOnClickListener {
                model.profilo.value!!.intolleranze?.clear()
                if (dialog.cB_dairy_up.isChecked) intolleranze_utente!!.add("Dairy")
                if (dialog.cB_gluten_up.isChecked) intolleranze_utente!!.add("Gluten")
                if (dialog.cB_peanut_up.isChecked) intolleranze_utente!!.add("Peanut")
                if (dialog.cB_grain_up.isChecked) intolleranze_utente!!.add("Grain")
                if (dialog.cB_seafood_up.isChecked) intolleranze_utente!!.add("Seafood")
                if (dialog.cB_egg_up.isChecked) intolleranze_utente!!.add("Egg")
                if (dialog.cB_sesame_up.isChecked) intolleranze_utente!!.add("Sesame")
                if (dialog.cB_shellfish_up.isChecked) intolleranze_utente!!.add("Shellfish")
                if (dialog.cB_soy_up.isChecked) intolleranze_utente!!.add("Soy")
                if (dialog.cB_sulfite_up.isChecked) intolleranze_utente!!.add("Sulfite")
                if (dialog.cB_tree_nut_up.isChecked) intolleranze_utente!!.add("Tree Nut")
                if (dialog.cB_wheat_up.isChecked) intolleranze_utente!!.add("Wheat")
                if (dialog.cB_altro_up.isChecked) intolleranze_utente!!.add("Other")
//            else intolleranze_utente!!.add("")

                dialog.dismiss()

            }
            dialog.show()
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

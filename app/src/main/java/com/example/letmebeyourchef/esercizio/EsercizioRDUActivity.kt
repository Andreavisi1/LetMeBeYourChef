package com.example.letmebeyourchef.esercizio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.ActivityEsercizioBinding
import com.example.letmebeyourchef.home.HomeActivity

class EsercizioRDUActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEsercizioBinding
    private var esercizio  = HashMap<String,String>()
    private val model = EsercizioViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_esercizio)
        setContentView(binding.root)

        getExtra()
        setLayout()

        binding.eTDurata.isEnabled = false

        val observerDelete = Observer<Boolean> {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        model.dispensaChanged.observe(this, observerDelete)

        binding.btnElimina.setOnClickListener {
            model.deleteEsercizio(esercizio["nome"]!!, this)
        }

        var flag = false
        binding.btnModifica.setOnClickListener {
            binding.eTDurata.isEnabled = true
            val durata = binding.eTDurata.text.toString()
            if (durata != "0" && durata != "" && durata != esercizio["durata"]!!)
                model.updateEsercizio(esercizio["nome"]!!, durata.toInt(), this)
            else {
                if (flag)
                    Toast.makeText(
                        this,
                        "Inserisci una durata diversa da $durata\naltrimenti se desideri eliminare l\'esercizio clicca su elimina",
                        Toast.LENGTH_LONG
                    ).show()
                flag = true
            }
        }

    }


    private fun getExtra() {
        val bundle = intent.extras!!
        esercizio.put("id", bundle.getString("id")!!)
        esercizio.put("kcal_h", bundle.getString("kcal_h")!!)
        esercizio.put("nome", bundle.getString("nome")!!)
        esercizio.put("durata", bundle.getInt("durata")!!.toString())
    }

    private fun setLayout() {
        Glide.with(this)
            .load("https://cdn.vectorstock.com/i/preview-1x/38/32/square-barbell-icon-vector-5293832.webp")
            .into(binding.imageView51)

        binding.tVEsercizio.text = esercizio["nome"].toString()
        binding.tVKcalH.text = esercizio["kcal_h"].toString()
        binding.eTDurata.setText(esercizio["durata"].toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HomeActivity::class.java))
    }


}
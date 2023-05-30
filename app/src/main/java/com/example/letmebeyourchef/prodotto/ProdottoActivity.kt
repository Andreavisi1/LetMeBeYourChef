package com.example.letmebeyourchef.prodotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.letmebeyourchef.R

import com.example.letmebeyourchef.databinding.ActivityProdottoBinding
import com.example.letmebeyourchef.model.Json_Parsing.Nutrients
import kotlin.math.roundToInt

class ProdottoActivity : AppCompatActivity() {

    private lateinit var binding :  ActivityProdottoBinding
    private var prodotto  = HashMap<String,String?>()
    private var nutrients = HashMap<String,Double?>()
    private val model = ProdottoViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_prodotto)

        getExtra()
        setLayout()

        binding.btnAddDiary.setOnClickListener {
            val quantita = binding.etQuantita.text.toString().toDouble()
            if(quantita != 0.0 && quantita.toString() != ""){
                model.setPastoOnDB(prodotto["tipologiaPasto"]!!, prodotto["foodId"]!!, prodotto["image"]!!,
                    prodotto["label"]!!, nutrients["calorie"]!!, nutrients["proteine"]!!, nutrients["carboidrati"]!!,
                    nutrients["grassi"]!!, quantita, this)
                finish()
            }else{
                Toast.makeText(this, "Per favore inserisci una quantità diversa da $quantita",Toast.LENGTH_LONG).show()
            }

        }

        binding.btnAddPreferiti.setOnClickListener {
            model.setPastoPreferitiOnDB(
                                        prodotto["tipologiaPasto"]!!, prodotto["foodId"]!!, prodotto["image"]!!,
                                        prodotto["label"]!!, nutrients["calorie"]!!, nutrients["proteine"]!!,
                                        nutrients["carboidrati"]!!, nutrients["grassi"]!!, this
                                        )
            finish()
        }




    }
    private fun getExtra(){
        val bundle = intent.extras!!
        prodotto.put("tipologiaPasto",bundle.getString("tipologiaPasto"))
        prodotto.put("brand",bundle.getString("brand"))
        prodotto.put("category",bundle.getString("category"))
        prodotto.put("foodContents",bundle.getString("foodContents"))
        prodotto.put("foodId",bundle.getString("foodId"))
        prodotto.put("image",bundle.getString("image"))
        prodotto.put("knownAs",bundle.getString("knownAs"))
        prodotto.put("label",bundle.getString("label"))
        var nut = bundle.getParcelable<Nutrients>("nutrients")
        nutrients.put("calorie",nut!!.chiloCalorie!!.times(100.0).roundToInt()/100.0)
        nutrients.put("proteine",nut!!.proteine!!.times(100.0).roundToInt()/100.0)
        nutrients.put("carboidrati",nut!!.carboidrati!!.times(100.0).roundToInt()/100.0)
        nutrients.put("grassi",nut!!.grassi!!.times(100.0).roundToInt()/100.0)
    }

    private fun setLayout(){
        Glide.with(this)
            .load(prodotto.get("image"))
            .placeholder(R.drawable.no_image)
            .into(binding.imageProdotto)

        binding.tvProductName.text = prodotto.get("label")
        binding.tvBrand.text = prodotto.get("brand")
        binding.tvCategory.text = prodotto.get("category")
        binding.tvDescription.text = prodotto.get("foodContents")
        binding.tvCalorie.text = (nutrients.get("calorie")!!).toString()
        binding.tvProteine.text = (nutrients.get("proteine")!!).toString()
        binding.tvCarboidrati.text = (nutrients.get("carboidrati")!!).toString()
        binding.tvGrassi.text = (nutrients.get("grassi")!!).toString()
    }

}
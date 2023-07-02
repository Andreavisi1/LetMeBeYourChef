package com.example.letmebeyourchef.pasto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.ActivityPastoBinding
import com.example.letmebeyourchef.home.HomeActivity

class PastoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPastoBinding
    private var pasto  = HashMap<String,String>()
    private val model = PastoViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pasto)
        setContentView(binding.root)

        getExtra()
        setLayout()
 //       binding.eTQuantita.isEnabled = false

        val observerDelete = Observer<Boolean>{
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
        model.dispensaChanged.observe(this,observerDelete)

        binding.btnElimina.setOnClickListener {
            model.deletePasto(pasto["tipologiaPasto"]!!,pasto["id"]!!,this)
        }

        var flag = false
        binding.btnModifica.setOnClickListener {
//            binding.eTQuantita.isEnabled = true
//            val quantita = binding.eTQuantita.text.toString()
            model.updatePasto(pasto["tipologiaPasto"]!!,pasto["id"]!!,this)
            flag = true
            }
        }





    private fun getExtra(){
        val bundle = intent.extras!!
        pasto.put("tipologiaPasto",bundle.getString("tipologiaPasto")!!)
        pasto.put("id", bundle.getString("id")!!)
        pasto.put("kcal_pasto",bundle.getString("kcal_pasto")!!)
        pasto.put("carboidrati",bundle.getString("carboidrati")!!)
        pasto.put("proteine",bundle.getString("proteine")!!)
        pasto.put("grassi",bundle.getString("grassi")!!)
        pasto.put("prodotto",bundle.getString("prodotto")!!)
        pasto.put("image",bundle.getString("image")!!)
        //        pasto.put("quantità",bundle.getString("quantità")!!)
    }

    private fun setLayout(){
        Glide.with(this)
            .load(pasto.get("image"))
            .placeholder((R.drawable.no_image))
            .into(binding.imageView21)

        binding.tVProdotto.text = pasto["prodotto"].toString()
        binding.tVKcal.text = pasto["kcal_pasto"].toString()
        binding.tVCarboidrati.text = pasto["carboidrati"].toString()
        binding.tVProteine.text = pasto["proteine"].toString()
        binding.tVGrassi.text = pasto["grassi"].toString()
//        binding.eTQuantita.setText(pasto["quantità"].toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,HomeActivity::class.java))
    }
}
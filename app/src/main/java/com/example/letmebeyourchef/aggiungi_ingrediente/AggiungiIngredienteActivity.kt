package com.example.letmebeyourchef.aggiungi_ingrediente

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.ActivityAggiungiIngredienteBinding
import nl.joery.animatedbottombar.AnimatedBottomBar

class AggiungiIngredienteActivity : AppCompatActivity() {

    var dialog: ProgressDialog? = null
    private lateinit var binding: ActivityAggiungiIngredienteBinding
    val ricercaFragment = RicercaIngredientiFragment()
    val preferitiFragment = IngredientiPreferitiFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_aggiungi_ingrediente)

        dialog = ProgressDialog(this)
        dialog!!.setTitle("Loading details...")
        dialog!!.show()

        binding.aggToolbar.title =  ("Search for ingredients here:")

        var bottomNav = binding.bottomNavigation
        setContentView(binding.root)
        binding.bottomNavigation.selectTabById(R.id.ricerca,true)
        checkTabToReplace(0)
        dialog!!.dismiss()

        bottomNav.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                checkTabToReplace(newIndex)

            }

        })

    }

    private fun checkTabToReplace(index : Int){
        when(index){
            0 -> {
                val bundle = Bundle()
                if (intent.getStringExtra("bottone") != null)
                    bundle.putString("bottone",intent.getStringExtra("bottone"))
                if (intent.getStringExtra("upc") != null)
                    bundle.putString("upc",intent.getStringExtra("upc"))
                ricercaFragment.arguments = bundle
                replaceFragment(ricercaFragment)
            }
            1 -> {
                val bundle = Bundle()
                if (intent.getStringExtra("bottone") != null)
                    bundle.putString("bottone",intent.getStringExtra("bottone"))
                preferitiFragment.arguments = bundle
                replaceFragment(preferitiFragment)
            }
            else -> {
                val bundle = Bundle()
                if (intent.getStringExtra("bottone") != null)
                    bundle.putString("bottone",intent.getStringExtra("bottone"))
                if (intent.getStringExtra("upc") != null)
                    bundle.putString("upc",intent.getStringExtra("upc"))
                ricercaFragment.arguments = bundle
                replaceFragment(ricercaFragment)
            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransiction = fragmentManager.beginTransaction()
        fragmentTransiction.replace(R.id.frame_layout, fragment)
        fragmentTransiction.commit()

    }

}
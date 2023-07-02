package com.example.letmebeyourchef.aggiungi_pasto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.ActivityAggiungiBinding
import com.example.letmebeyourchef.home.HomeActivity
import nl.joery.animatedbottombar.AnimatedBottomBar

class AggiungiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAggiungiBinding
    val ricercaFragment = RicercaFragment()
    val personalizzatiFragment = PersonalizzatiFragment()
    val preferitiFragment = PreferitiFragment ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_aggiungi)

        binding.aggToolbar.title =  ("Search for ingredients here:")


        var bottomNav = binding.bottomNavigation
        setContentView(binding.root)
        binding.bottomNavigation.selectTabById(R.id.ricerca,true)
        checkTabToReplace(0)

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
                personalizzatiFragment.arguments = bundle
                replaceFragment(personalizzatiFragment)
            }
            2 -> {
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

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,HomeActivity::class.java))
    }






}
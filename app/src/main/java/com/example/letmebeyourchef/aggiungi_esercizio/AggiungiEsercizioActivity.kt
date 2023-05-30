package com.example.letmebeyourchef.aggiungi_esercizio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.ActivityAggiungiBinding
import com.example.letmebeyourchef.home.HomeActivity
import nl.joery.animatedbottombar.AnimatedBottomBar

class AggiungiEsercizioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAggiungiBinding
    val ricercaFragment = RicercaEserciziFragment()
    val personalizzatiFragment = PersonalizzatiEsercizioFragment()
    val preferitiFragment = PreferitiEserciziFragment ()






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_aggiungi)

        binding.aggToolbar.title = "ESERCIZIO"

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
        val bundle = Bundle()
        when(index){
            0 -> replaceFragment(ricercaFragment)

            1 -> replaceFragment(personalizzatiFragment)

            2 -> replaceFragment(preferitiFragment)

            else -> replaceFragment(ricercaFragment)
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
        startActivity(Intent(this, HomeActivity::class.java))
    }






}

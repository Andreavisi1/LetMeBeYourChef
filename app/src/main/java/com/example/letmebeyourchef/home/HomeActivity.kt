package com.example.letmebeyourchef.home

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.RequestManager
import com.example.letmebeyourchef.adapters.RicetteRandomAdapter
import com.example.letmebeyourchef.autenticazione.InizioActivity
import com.example.letmebeyourchef.autenticazione.RegisterActivity
import com.example.letmebeyourchef.cart.CartFragment
import com.example.letmebeyourchef.databinding.ActivityHomeBinding
//import com.example.letmebeyourchef.profilo.ProfiloActivity
import com.example.letmebeyourchef.homepage.HomepageFragment
//import com.example.letmebeyourchef.diete.DieteFragment
//import com.example.letmebeyourchef.funzioni.FunzioniFragment
import com.example.letmebeyourchef.profilo.ProfiloActivity
import com.example.letmebeyourchef.ricette_preferite.RicettePreferiteFragment
import com.example.letmebeyourchef.storage.StorageFragment
//import com.example.letmebeyourchef.statistiche.StatisticheFragment
import nl.joery.animatedbottombar.AnimatedBottomBar

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    val cartFragment = CartFragment()
    val ricettePreferiteFragment = RicettePreferiteFragment()
    val homepageFragment = HomepageFragment()
    val storageFragment = StorageFragment()

    private val model = HomeViewModel()
    private var doubleBackToExitPressedOnce = false

    var dialog: ProgressDialog? = null
    var manager: RequestManager? = null
    var ricetteRandomAdapter: RicetteRandomAdapter? = null
    lateinit var recyclerView: RecyclerView
    lateinit var spinner: Spinner
    var tags: MutableList<String> = ArrayList()
    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        dialog = ProgressDialog(this)
        dialog!!.setTitle("Loading recipes...")



        var bottomNav = binding.bottomNavigation
        setContentView(binding.root)
        binding.bottomNavigation.selectTabById(R.id.ic_home,true)
        replaceFragment(homepageFragment) // La home si aprirà sul fragment principale


        bottomNav.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                //redirecting fragment
                when(newIndex){
                    0 -> replaceFragment(homepageFragment)
                    1 -> replaceFragment(storageFragment)
                    2 -> replaceFragment(ricettePreferiteFragment)
                    3 -> replaceFragment(cartFragment)
                    else -> replaceFragment(this@HomeActivity.homepageFragment)
                }


            }


        })



        binding.aggToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.ic_profilo ->  openProfilo()
                R.id.ic_logout -> {
                    model.logOut()
                    startActivity(Intent(this, InizioActivity::class.java))
                    finish()
                }
            }
            true
        }


    }



    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish()
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Premi due volte indietro per uscire", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, fragment)
            commit()
        }
    }

    private fun openProfilo(){
        startActivity(Intent(this, ProfiloActivity::class.java))
        finish()
    }


    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, RegisterActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

}



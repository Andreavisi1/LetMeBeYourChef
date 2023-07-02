package com.example.letmebeyourchef.home

import DispensaFragment
import android.app.ProgressDialog
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
import com.example.letmebeyourchef.databinding.ActivityHomeBinding
//import com.example.letmebeyourchef.profilo.ProfiloActivity
import com.example.letmebeyourchef.diete.DieteFragment
import com.example.letmebeyourchef.funzioni.FunzioniFragment
import com.example.letmebeyourchef.homepage.HomepageFragment
//import com.example.letmebeyourchef.diete.DieteFragment
//import com.example.letmebeyourchef.funzioni.FunzioniFragment
import com.example.letmebeyourchef.profilo.ProfiloActivity
//import com.example.letmebeyourchef.statistiche.StatisticheFragment
import nl.joery.animatedbottombar.AnimatedBottomBar

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    val dispensaFragment = DispensaFragment()
    val dieteFragment = DieteFragment()
    val funzioniFragment = FunzioniFragment()

    val homepageFragment = HomepageFragment()

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



/*        searchView = findViewById(R.id.searchview_home)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            public override fun onQueryTextSubmit(query: String): Boolean {
                tags.clear()
                tags.add(query)
                manager!!.getRicetteRandom(responseListenerRicetteRandom, tags)
                dialog!!.show()
                return true
            }

            public override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })
        spinner = findViewById(R.id.spinner_tags)
        val arrayAdapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(
            this,
            R.array.tags,
            R.layout.spinner_text
        )
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text)
        spinner.setAdapter(arrayAdapter)
        spinner.setOnItemSelectedListener(spinnerSelectedListener)
        manager = RequestManager(this)
        //        manager.getRicetteRandom(responseListenerRicetteRandom);
//        dialog.show();*/

        bottomNav.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                //redirecting fragment
                when(newIndex){
                    0 -> replaceFragment(homepageFragment);
                    1 -> replaceFragment(this@HomeActivity.dispensaFragment);
                    2 -> replaceFragment(dieteFragment);
                    3 -> replaceFragment(funzioniFragment);
                    else -> replaceFragment(this@HomeActivity.dispensaFragment)
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
    private fun openSettings(){

    }

    private fun openGuida(){

    }

    private fun openProfilo(){
        startActivity(Intent(this, ProfiloActivity::class.java))
        finish()
    }


/*
    private val responseListenerRicetteRandom: ResponseListenerRicetteRandom =
        object : ResponseListenerRicetteRandom {
            public override fun didFetch(
                response: ResponseFromApiRicetteRandom?,
                message: String?
            ) {
                dialog!!.dismiss()
                recyclerView = findViewById(R.id.recycler_random)
                recyclerView.setHasFixedSize(true)
                recyclerView.setLayoutManager(GridLayoutManager(this@HomeActivity, 1))
                ricetteRandomAdapter = RicetteRandomAdapter(
                    this@HomeActivity,
                    response!!.recipes,
                    ricettaClickListener
                )
                recyclerView.setAdapter(ricetteRandomAdapter)
            }

            public override fun didError(message: String?) {
                Toast.makeText(this@HomeActivity, message, Toast.LENGTH_SHORT).show()
            }
        }
    private val spinnerSelectedListener: AdapterView.OnItemSelectedListener =
        object : AdapterView.OnItemSelectedListener {
            public override fun onItemSelected(
                adapterView: AdapterView<*>,
                view: View,
                i: Int,
                l: Long
            ) {
                tags.clear()
                tags.add(adapterView.getSelectedItem().toString())
                manager!!.getRicetteRandom(responseListenerRicetteRandom, tags)
                dialog!!.show()
            }

            public override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    private val ricettaClickListener: RicettaClickListener = object : RicettaClickListener {
        public override fun onClickRicetta(id: String?) {
            startActivity(
                Intent(this@HomeActivity, ActivityDettagliRicetta::class.java)
                    .putExtra("id", id)
            )
        }
    }

*/
}


/*
    private lateinit var binding: ActivityHomeBinding
     val dispensaFragment = DispensaFragment()
     val statisticheFragment = StatisticheFragment()
     val dieteFragment = DieteFragment()
     val funzioniFragment = FunzioniFragment()

    private val model = HomeViewModel()
    private var doubleBackToExitPressedOnce = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)


        var bottomNav = binding.bottomNavigation
        setContentView(binding.root)
        binding.bottomNavigation.selectTabById(R.id.ic_diary,true)
        replaceFragment(diarioFragment) // La home si aprirà sul fragment del diario



        bottomNav.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                //redirecting fragment
                when(newIndex){
                    0 -> replaceFragment(dispensaFragment);
                    1 -> replaceFragment(statisticheFragment);
                    2 -> replaceFragment(dieteFragment);
                    3 -> replaceFragment(funzioniFragment);
                    else -> replaceFragment(homeFragment)
                }


            }


        })



        binding.aggToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.ic_profilo ->  openProfilo()
                R.id.ic_logout -> {
                    model.logOut()
                    startActivity(Intent(this,InizioActivity::class.java))
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
    private fun openSettings(){

    }

    private fun openGuida(){

    }

    private fun openProfilo(){
        startActivity(Intent(this, ProfiloActivity::class.java))
        finish()
    }
}

*/


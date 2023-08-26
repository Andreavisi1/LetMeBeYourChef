package com.example.letmebeyourchef

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.ActivityGoogleMapsBinding
import com.example.letmebeyourchef.databinding.ActivityMainBinding
import com.example.letmebeyourchef.databinding.NavigationDrawerLayoutBinding
import com.example.letmebeyourchef.databinding.ToolbarLayoutBinding
import com.example.letmebeyourchef.model.Utente
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import de.hdodenhof.circleimageview.CircleImageView

class GoogleMapsActivity : AppCompatActivity() {

    private lateinit var navigationDrawerLayoutBinding: NavigationDrawerLayoutBinding
    private lateinit var mainBinding: ActivityGoogleMapsBinding
    private lateinit var toolbarLayoutBinding: ToolbarLayoutBinding
    private lateinit var firebaseAuth: FirebaseAuth
    //private lateinit var imgHeader: CircleImageView
    //private lateinit var txtName: TextView
    //private lateinit var txtEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationDrawerLayoutBinding = NavigationDrawerLayoutBinding.inflate(layoutInflater)
        setContentView(navigationDrawerLayoutBinding.root)
        mainBinding = navigationDrawerLayoutBinding.googleMapsActivity
        toolbarLayoutBinding = mainBinding.toolbarMain

        setSupportActionBar(toolbarLayoutBinding.toolbar)

        firebaseAuth = Firebase.auth

        val toggle = ActionBarDrawerToggle(
            this,
            navigationDrawerLayoutBinding.navDrawer,
            toolbarLayoutBinding.toolbar,
            R.string.open_navigation_drawer,
            R.string.close_navigation_drawer
        )

        navigationDrawerLayoutBinding.navDrawer.addDrawerListener(toggle)
        toggle.syncState()

        val navController = Navigation.findNavController(this, R.id.fragmentContainer)
        NavigationUI.setupWithNavController(
            navigationDrawerLayoutBinding.navigationView,
            navController
        )

        val headerLayout = navigationDrawerLayoutBinding.navigationView.getHeaderView(0)

        //imgHeader = headerLayout.findViewById(R.id.imgHeader)
        //txtName = headerLayout.findViewById(R.id.txtHeaderName)
        //txtEmail = headerLayout.findViewById(R.id.txtHeaderEmail)

        getUserData()
    }

    override fun onBackPressed() {

        if (navigationDrawerLayoutBinding.navDrawer.isDrawerOpen(GravityCompat.START))
            navigationDrawerLayoutBinding.navDrawer.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    private fun getUserData() {
        val database = Firebase.database.getReference("Utente").child(firebaseAuth.uid!!)

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val userModel = snapshot.getValue(Utente::class.java)
                    //Glide.with(this@GoogleMapsActivity).load(R.drawable.ic_map).into(imgHeader)
                    //txtEmail.text = userModel?.email
                    //txtName.text = userModel?.nome
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


}


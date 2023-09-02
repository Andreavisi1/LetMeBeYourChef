package com.example.letmebeyourchef
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.letmebeyourchef.autenticazione.AuthViewModel
import com.example.letmebeyourchef.autenticazione.InizioActivity
import com.example.letmebeyourchef.databinding.ActivityMainBinding
import com.example.letmebeyourchef.home.HomeActivity


class MainActivity : AppCompatActivity() {

    val model = AuthViewModel()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.progressBar.visibility = ProgressBar.VISIBLE



        if (model.checkUtenteisLoggato()) {
            val intent = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()




        } else {
            val intent = Intent(this@MainActivity, InizioActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}


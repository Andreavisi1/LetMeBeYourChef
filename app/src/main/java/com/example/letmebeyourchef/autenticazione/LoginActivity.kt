package com.example.letmebeyourchef.autenticazione

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Patterns
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.letmebeyourchef.databinding.ActivityLoginBinding
import com.example.letmebeyourchef.home.HomeActivity
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val model = AuthViewModel()
    private lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressBar = binding.progressBar2
        progressBar.visibility = ProgressBar.INVISIBLE

        if(model.checkUtenteisLoggato()){
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnLogin.setOnClickListener {
            progressBar.visibility = ProgressBar.VISIBLE
            val email = binding.InputEmailLogin.text.toString()
            val password = binding.InputPasswordLogin.text.toString()


            if (checkFields(email, password)) {
                lifecycleScope.launch {
                    if (model.signIn(email, password) == null) {
                        checkError(isOnline(this@LoginActivity))
                    } else {
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()

                    }
                }
            }
        }

        binding.forgotPass.setOnClickListener {
            val intent = Intent(this@LoginActivity, RecuperoPasswordActivity::class.java)
            startActivity(intent)
        }
    }



    override fun onResume() {
        super.onResume()
        progressBar.visibility = ProgressBar.INVISIBLE
    }

    private fun checkFields(email : String , password: String) : Boolean{
        if(email.isEmpty()){
            binding.InputEmailLogin.error = "Please complete the field to log in"
            binding.InputEmailLogin.requestFocus()
            progressBar.visibility = ProgressBar.INVISIBLE
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.InputEmailLogin.error = "Please insert a valid e-mail format"
            binding.InputEmailLogin.requestFocus()
            progressBar.visibility = ProgressBar.INVISIBLE
            return false
        }

        if(password.isEmpty()){
            binding.InputPasswordLogin.error = "Please fill the field to login"
            binding.InputPasswordLogin.requestFocus()
            progressBar.visibility = ProgressBar.INVISIBLE
            return false
        }

        return true

    }

    private fun checkError(isOnline : Boolean){
        if (isOnline) {
            binding.InputEmailLogin.error = "E-mail or password wrong"
            binding.InputPasswordLogin.error = "E-mail or password wrong"
            progressBar.visibility = ProgressBar.INVISIBLE
        }
        else
            Toast.makeText(this,"Please activate an internet connection", Toast.LENGTH_LONG).show()

    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =  context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }
        progressBar.visibility = ProgressBar.INVISIBLE
        return false
    }

}

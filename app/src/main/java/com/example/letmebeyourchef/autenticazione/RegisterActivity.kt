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
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.ActivityRegisterBinding
import com.example.letmebeyourchef.model.Utente
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var utente: Utente
    private lateinit var binding: ActivityRegisterBinding
    private val model= AuthViewModel()
    val args: RegisterActivityArgs by navArgs()
    private lateinit var progressBar : ProgressBar

    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        utente = args.utente
        progressBar = binding.progressBar3
        progressBar.visibility = ProgressBar.INVISIBLE

        binding.btnRegister.setOnClickListener {
            val email = binding.InputEmail.text.toString().trim()
            val pass = binding.InputPassword.text.toString().trim()
            val confPass = binding.InputCorrectPassword.text.toString().trim()
            progressBar.visibility = ProgressBar.VISIBLE
            val check = checkFields(email, pass, confPass)

            lifecycleScope.launch {
                if (check) {
                    if (model.singUp(email, pass) == null) {
                        checkError(isOnline(this@RegisterActivity))
                    } else {
                        model.addAuthUtenteOnDB(utente.nome, utente.cognome, email, utente.sesso,
                            utente.data_nascita,
                            utente.intolleranze, this@RegisterActivity)
                        val intent = Intent(applicationContext, ConosciamociActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.putExtra("EXIT", true)
                        startActivity(intent)
                        finish()
                    }
                }
            }

            /*signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(
                    BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId(getString(R.string.your_web_client_id))
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                .build()*/

        }
    }

    override fun onResume() {
        super.onResume()
        progressBar.visibility = ProgressBar.INVISIBLE
    }
    private fun checkFields(email: String, pass: String, confPass: String): Boolean {

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.InputEmail.setError("The email format is wrong!")
            binding.InputEmail.requestFocus()
            progressBar.visibility = ProgressBar.INVISIBLE
            return false
        }

        if (pass.isEmpty()) {
            binding.InputPassword.setError("Password is required")
            binding.InputPassword.requestFocus()
            progressBar.visibility = ProgressBar.INVISIBLE
            return false
        }


        if (pass.length < 6) {
            binding.InputPassword.setError("The password should have a minimum of 6 characters")
            binding.InputPassword.requestFocus()
            progressBar.visibility = ProgressBar.INVISIBLE
            return false
        }

        if (confPass.isEmpty()) {
            binding.InputCorrectPassword.setError("Please confirm your password")
            binding.InputCorrectPassword.requestFocus()
            progressBar.visibility = ProgressBar.INVISIBLE
            return false
        }

        if (!pass.equals(confPass)) {
            binding.InputCorrectPassword.setError("The passwords don’t match!")
            binding.InputCorrectPassword.setText(" ")
            progressBar.visibility = ProgressBar.INVISIBLE
            return false
        }
        else
            return true
    }


    private fun checkError(isOnline : Boolean){
        if (isOnline)
            Toast.makeText(this,"The user is already registered", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this,"Please activate an internet connection", Toast.LENGTH_LONG).show()
        progressBar.visibility = ProgressBar.INVISIBLE
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =  context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
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
        }
        return false
    }
}
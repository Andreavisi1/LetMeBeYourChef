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
import com.example.letmebeyourchef.databinding.ActivityRegisterBinding
import com.example.letmebeyourchef.model.Utente
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var utente: Utente
    private lateinit var binding: ActivityRegisterBinding
    private val model= AuthViewModel()
    val args: RegisterActivityArgs by navArgs()
    private lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        utente = args.utente
        progressBar = binding.progressBar3
        progressBar.visibility = ProgressBar.INVISIBLE
        binding.imageView68.isVisible = utente.agonistico

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
                        model.addAuthUtenteOnDB(utente.nome, utente.cognome, email, utente.LAF,utente.agonistico, utente.sesso,
                            utente.data_nascita,utente.altezza,utente.peso_attuale,
                            utente.sport, this@RegisterActivity)
                        val intent = Intent(applicationContext, ConosciamociActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.putExtra("EXIT", true)
                        startActivity(intent)
                        finish()
                    }
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        progressBar.visibility = ProgressBar.INVISIBLE
    }
    private fun checkFields(email: String, pass: String, confPass: String): Boolean {

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.InputEmail.setError("Il formato dell'Email è errato!")
            binding.InputEmail.requestFocus()
            progressBar.visibility = ProgressBar.INVISIBLE
            return false
        }

        if (pass.isEmpty()) {
            binding.InputPassword.setError("La password è richiesta")
            binding.InputPassword.requestFocus()
            progressBar.visibility = ProgressBar.INVISIBLE
            return false
        }


        if (pass.length < 6) {
            binding.InputPassword.setError("La password deve essere di almeno 6 caratteri")
            binding.InputPassword.requestFocus()
            progressBar.visibility = ProgressBar.INVISIBLE
            return false
        }

        if (confPass.isEmpty()) {
            binding.InputCorrectPassword.setError("Conferma la tua password per favore")
            binding.InputCorrectPassword.requestFocus()
            progressBar.visibility = ProgressBar.INVISIBLE
            return false
        }

        if (!pass.equals(confPass)) {
            binding.InputCorrectPassword.setError("Le password non corrispondono!")
            binding.InputCorrectPassword.setText(" ")
            progressBar.visibility = ProgressBar.INVISIBLE
            return false
        }
        else
            return true
    }


    private fun checkError(isOnline : Boolean){
        if (isOnline)
            Toast.makeText(this,"L'utente è già registrato", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this,"Per favore attiva una connessione ad internet", Toast.LENGTH_LONG).show()
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
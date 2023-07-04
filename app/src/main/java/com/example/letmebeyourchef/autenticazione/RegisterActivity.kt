package com.example.letmebeyourchef.autenticazione

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.ActivityRegisterBinding
import com.example.letmebeyourchef.model.Utente
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.sign_in_button
import kotlinx.coroutines.launch


const val RC_SIGN_IN = 123
class RegisterActivity : AppCompatActivity() {
    private lateinit var utente: Utente
    private lateinit var binding: ActivityRegisterBinding
    private val model= AuthViewModel()
    val args: RegisterActivityArgs by navArgs()
    private lateinit var progressBar : ProgressBar

    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest

    private lateinit var signInButton: SignInButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        utente = args.utente
        progressBar = binding.progressBar3
        progressBar.visibility = ProgressBar.INVISIBLE
        signInButton = binding.signInButton
        //setGooglePlusButtonText(binding.signInGoogleButton, "Or sign in with Google")

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.visibility = View.VISIBLE
        signInButton.setSize(SignInButton.SIZE_STANDARD)




        signInButton.setOnClickListener{
            val signInIntent: Intent = mGoogleSignInClient.getSignInIntent()
            startActivityForResult(signInIntent, RC_SIGN_IN)

        }

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {

            signInButton.visibility = View.VISIBLE
        }



        fun handleSignInResult(task: Task<GoogleSignInAccount>) {
            try {



                signInButton.visibility = View.VISIBLE
                // Signed in successfully, show authenticated UI.

            } catch (e: ApiException) {
                // The ApiException status code indicates the detailed failure reason.
                // Please refer to the GoogleSignInStatusCodes class reference for more information

                signInButton.visibility = View.VISIBLE
            }
        }

        fun onActivityResult(requestCode : Int, resultCode : Int, data : Intent) {
            super.onActivityResult(requestCode, resultCode, data)

            // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
            if (requestCode == RC_SIGN_IN) {
                // The Task returned from this call is always completed, no need to attach
                // a listener.
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                handleSignInResult(task);
            }
        }





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
                        .setServerClientId(getString("161508747965-4mfa4ct15dgm9rlqu32t6s92k97ljckr.apps.googleusercontent.com"))
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                .build()*/

        }
    }

    protected fun setGooglePlusButtonText(signInButton: SignInButton, buttonText: String?) {
            // Find the TextView that is inside of the SignInButton and set its text
            for (i in 0 until signInButton.childCount) {
            val v: View = signInButton.getChildAt(i)
            if (v is TextView) {
                val tv = v as TextView
                tv.text = buttonText
                return
            }
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

package com.example.letmebeyourchef.autenticazione

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.ActivityRegisterBinding
import com.example.letmebeyourchef.home.HomeActivity
import com.example.letmebeyourchef.model.Utente
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch


const val RC_SIGN_IN = 1

class RegisterActivity : AppCompatActivity() {
    private lateinit var utente: Utente
    private lateinit var binding: ActivityRegisterBinding
    private val model = AuthViewModel()
    val args: RegisterActivityArgs by navArgs()
    private lateinit var progressBar: ProgressBar
    private lateinit var signInButton: SignInButton
    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest
    private lateinit var firebaseAuth: FirebaseAuth


    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        utente = args.utente
        progressBar = binding.progressBar3
        signInButton = binding.signInButton
        progressBar.visibility = ProgressBar.INVISIBLE
        setGooglePlusButtonText(binding.signInButton, "Sign in with Google")

        /*val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)*/

        signInButton.setSize(SignInButton.SIZE_STANDARD)


        configureGoogleSignIn()
        setupUI()




//        signInButton.setOnClickListener(this);




        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            signInButton.visibility=View.VISIBLE
            val email = acct.email

        }





        binding.btnRegister.setOnClickListener {
            val email = binding.InputEmail.text.toString().trim()
            val pass = binding.InputPassword.text.toString().trim()
            val confPass = binding.InputCorrectPassword.text.toString().trim()
            progressBar.visibility = ProgressBar.VISIBLE
            val check = checkFields(email, pass, confPass)

            lifecycleScope.launch {
                if (check) {
                    if (model.signUp(email, pass) == null) {
                        checkError(isOnline(this@RegisterActivity))
                    } else {
                        model.addAuthUtenteOnDB(
                            utente.nome, utente.cognome, email, utente.sesso,
                            utente.data_nascita,
                            utente.intolleranze, this@RegisterActivity
                        )
                        val intent = Intent(applicationContext, ConosciamociActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.putExtra("EXIT", true)
                        startActivity(intent)
                        finish()
                    }
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

    private fun configureGoogleSignIn() {
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)
    }

    private fun setupUI() {
        signInButton.setOnClickListener {
            signIn()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                Toast.makeText(this, "Google sign in failed :(", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                lifecycleScope.launch {
                            acct.email?.let { it1 ->
                                model.addAuthUtenteOnDB(
                                    utente.nome, utente.cognome, it1, utente.sesso,
                                    utente.data_nascita,
                                    utente.intolleranze, this@RegisterActivity
                                )
                            }
                            val intent = Intent(applicationContext, HomeActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            intent.putExtra("EXIT", true)
                            startActivity(intent)
                            finish()
                        }
            } else {
                Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            startActivity(HomeActivity.getLaunchIntent(this))
            finish()
        }
    }

    fun setGooglePlusButtonText(signInButton: SignInButton, buttonText: String?) {
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
        } else
            return true
    }


    private fun checkError(isOnline: Boolean) {
        if (isOnline)
            Toast.makeText(this, "The user is already registered", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this, "Please activate an internet connection", Toast.LENGTH_LONG).show()
        progressBar.visibility = ProgressBar.INVISIBLE
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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

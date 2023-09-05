package com.example.letmebeyourchef.autenticazione

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Patterns
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.ActivityLoginBinding
import com.example.letmebeyourchef.home.HomeActivity
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


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val model = AuthViewModel()
    private lateinit var progressBar : ProgressBar
    private lateinit var signInButton: SignInButton
    private lateinit var firebaseAuth: FirebaseAuth

    /*private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: androidx.biometric.BiometricPrompt.PromptInfo*/

    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressBar = binding.progressBar2
        progressBar.visibility = ProgressBar.INVISIBLE
        signInButton = binding.signInButton


        signInButton.setSize(SignInButton.SIZE_STANDARD)

        googleBtnUi()
        configureGoogleSignIn()
        setupUI()

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            signInButton.visibility=View.VISIBLE
            val email = acct.email

        }

        if(model.checkUtenteisLoggato()){
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        /*executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = androidx.biometric.BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        this@SplashActivity,
                        "Authentication error $errString",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()

                    Toast.makeText(
                        this@SplashActivity,
                        "Authentication failed ",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Finger print authentication required to login")
            .setNegativeButtonText("Use account password")
            .build()

        Handler(Looper.getMainLooper()).postDelayed({
            if (Firebase.auth.currentUser != null) {

                val finger = getSharedPreferences("user", MODE_PRIVATE)
                    .getBoolean("finger", false)
                if (finger) {
                    biometricPrompt.authenticate(promptInfo)
                } else {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }

            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }, 3000)*/

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

        binding.forgotPass.movementMethod = ScrollingMovementMethod()

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

    private fun googleBtnUi() {
        val googleButton = findViewById<View>(R.id.sign_in_button) as SignInButton
        googleButton.setOnClickListener { }
        for (i in 0 until googleButton.childCount) {
            val v = googleButton.getChildAt(i)
            if (v is TextView) {
                val tv = v
                tv.textSize = 16f
                tv.setTypeface(null, Typeface.NORMAL)
                tv.text = "Sign in with Google"
                tv.setTextColor(Color.parseColor("#198EEE"))
                tv.isSingleLine = true
                tv.setPadding(15, 15, 15, 15)
                return
            }
        }
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

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
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
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                    val intent = Intent(applicationContext, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    intent.putExtra("EXIT", true)
                    startActivity(intent)
                    finish()

            } else {
                Toast.makeText(this, "Google sign in failed :(", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,InizioActivity::class.java))
    }

}

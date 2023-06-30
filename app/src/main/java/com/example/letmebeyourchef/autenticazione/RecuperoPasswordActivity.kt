package com.example.letmebeyourchef.autenticazione

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.letmebeyourchef.databinding.ActivityRecPasswordBinding
import kotlinx.coroutines.launch

class RecuperoPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecPasswordBinding
    private val model = AuthViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIndietro.setOnClickListener(){
            finish()
        }
        binding.btnInvia.setOnClickListener {
            val email = binding.InputRecupero.text.toString().trim { it <= ' '}
            if(checkFields(email)){
                lifecycleScope.launch {
                    val task = model.resetPassword(email)
                    task.addOnCompleteListener{
                        if (it.isSuccessful) {
                            Toast.makeText(this@RecuperoPasswordActivity, "Email successfully sent to the address indicated!", Toast.LENGTH_LONG).show()
                            finish()
                        } else {
                            Toast.makeText(this@RecuperoPasswordActivity,"Unregistered or incorrect email!",Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
    private fun checkFields(TextEmailInfo: String): Boolean {

        if(TextEmailInfo.isEmpty()) {
            binding.InputRecupero.setError("Please complete the field")
            binding.InputRecupero.requestFocus()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(TextEmailInfo).matches()) {
            binding.InputRecupero.setError("Insert a valid email")
            binding.InputRecupero.requestFocus()
            return false
        }

        else
            return true
    }
}
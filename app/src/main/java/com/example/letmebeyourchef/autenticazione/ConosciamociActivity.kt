package com.example.letmebeyourchef.autenticazione


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.letmebeyourchef.databinding.ActivityConosciamociBinding


class ConosciamociActivity : AppCompatActivity(){
    private lateinit var binding: ActivityConosciamociBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConosciamociBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.getBooleanExtra("EXIT", false)) {
            finish()
        }

    }
}
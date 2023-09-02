package com.example.letmebeyourchef.autenticazione

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.ActivityInizioBinding
import kotlinx.coroutines.launch


class InizioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInizioBinding
    private lateinit var uri: Uri
    private lateinit var progresBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_inizio)
        avvioVideo()
        progresBar = binding.ProgressBar01
        progresBar.visibility = ProgressBar.INVISIBLE


        binding.btInizia.setOnClickListener {
            progresBar.visibility = ProgressBar.VISIBLE
            lifecycleScope.launch {
                var a = Intent(this@InizioActivity, ConosciamociActivity::class.java)
                startActivity(a)
            }
        }


        binding.btAccesso.setOnClickListener {
            progresBar.visibility = ProgressBar.VISIBLE
            lifecycleScope.launch {
                var a = Intent(this@InizioActivity, LoginActivity::class.java)
                startActivity(a)
                finish()
            }

        }

    }

    private fun avvioVideo() {
        uri = Uri.parse("android.resource://"+ packageName +"/" + R.raw.videoapp)
        binding.videoView.setVideoURI(uri)
        binding.videoView.start()


        binding.videoView.setOnPreparedListener {
            it.isLooping = true
        }
    }


    override fun onResume(){
        super.onResume()
        binding.videoView.resume()
        progresBar.visibility = ProgressBar.INVISIBLE
    }

    override fun onPause() {
        binding.videoView.suspend()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.videoView.stopPlayback()
        progresBar.visibility = ProgressBar.INVISIBLE
    }


    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, RegisterActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }






}
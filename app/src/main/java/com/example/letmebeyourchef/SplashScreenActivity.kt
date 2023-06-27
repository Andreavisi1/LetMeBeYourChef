package com.example.letmebeyourchef

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.letmebeyourchef.databinding.ActivityInizioBinding
import com.example.letmebeyourchef.databinding.ActivitySplashScreenBinding


class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var uri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash_screen)

        try {

            uri = Uri.parse("android.resource://"+ packageName +"/"+R.raw.letmebeyourchef)
            binding.videoView.setVideoURI(uri)
            binding.videoView.setOnCompletionListener { jump() }
            binding.videoView.start()

/*            val videoHolder = VideoView(this)
            setContentView(videoHolder)
            val video = Uri.parse("android.resource://" + packageName + "/" + R.raw.letmebeyourchef)
            videoHolder.setVideoURI(video)

            videoHolder.setZOrderOnTop(true)
            videoHolder.verticalScrollbarPosition


            videoHolder.setOnCompletionListener { jump() }
            videoHolder.start()*/
        } catch (ex: Exception) {
            jump()
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        jump()
        return true
    }

    private fun jump() {
        if (isFinishing) return
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
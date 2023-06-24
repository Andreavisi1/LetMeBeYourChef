package com.example.letmebeyourchef

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.letmebeyourchef.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    var videoView: VideoView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        videoView = findViewById<View>(R.id.splashView) as VideoView
        val videoPath = StringBuilder("android.resource://")
            .append("/raw/letmebeyourchef")
            .toString()
        videoView!!.setVideoPath(videoPath)
        videoView!!.setOnCompletionListener {
            Handler().postDelayed({
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                finish()
            }, 500)
            videoView!!.start()
        }
    }
}
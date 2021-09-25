package com.example.githubsearch.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.githubsearch.R
import com.example.githubsearch.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()
        
        Handler(Looper.getMainLooper()).postDelayed({
            val homeIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(homeIntent)
            finish()
        }, 300)
    }
}
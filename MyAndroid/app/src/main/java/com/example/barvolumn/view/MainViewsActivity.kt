package com.example.barvolumn.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.barvolumn.R

class MainViewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_views)

        supportActionBar?.title = "Google PIXEL"
    }
}
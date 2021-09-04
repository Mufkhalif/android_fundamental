package com.example.barvolumn.intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.barvolumn.R

class MoveWithDataActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_AGE = "extra_age"
        const val EXTRA_NAME = "extre_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_data)

        val tvDataReceived:TextView = findViewById(R.id.tv_data_received)

        val name = intent.getStringExtra(EXTRA_NAME)
        val age = intent.getIntExtra(EXTRA_AGE, 0)

        val text = "Name: $name \nYour Age: $age"
        tvDataReceived.text = text
    }
}
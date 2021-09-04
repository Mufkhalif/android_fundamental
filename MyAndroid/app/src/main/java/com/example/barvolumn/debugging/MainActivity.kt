package com.example.barvolumn.debugging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.barvolumn.R
import org.w3c.dom.Text
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnSetvalue: Button
    private lateinit var tvText: TextView

    private var names = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        tvText = findViewById(R.id.tv_text)
        btnSetvalue = findViewById(R.id.btn_set_value)

        btnSetvalue.setOnClickListener(this)

        names.add("ABCD")
        names.add("FGJs")
        names.add("Mufkhalif")
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.btn_set_value) {
            Log.d("MainActivity", names.toString())

            val name = StringBuilder()

            for (i in 0..2) {
                name.append(names[i]).append("\n")
            }

            tvText.text = name.toString()

        }
    }
}
package com.example.barvolumn.intent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.barvolumn.R

class MainIntent : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvResult: TextView

    companion object {
        private const val REQUEST_CODE = 110
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_intent)

        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveWithDataActivity: Button = findViewById(R.id.btn_move_activity_data)
        btnMoveWithDataActivity.setOnClickListener(this)

        val btnDialPhone: Button = findViewById(R.id.btn_dial_number)
        btnDialPhone.setOnClickListener(this)

        val btnMoveObject: Button = findViewById(R.id.btn_move_activity_object)
        btnMoveObject.setOnClickListener(this)


        val btnMoveForResult: Button = findViewById(R.id.btn_move_for_result)
        btnMoveForResult.setOnClickListener(this)


        tvResult = findViewById(R.id.tv_result)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_move_activity -> {
                val moveIntent = Intent(this@MainIntent, MoveActivity::class.java)
                startActivity(moveIntent)
            }

            R.id.btn_move_activity_data -> {
                val moveWithDataIntent = Intent(this@MainIntent, MoveWithDataActivity::class.java)
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Mufkhalif")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 20)
                startActivity(moveWithDataIntent)
            }

            R.id.btn_dial_number -> {
                val phoneNumber = "082120125369"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel: $phoneNumber"))
                startActivity(dialPhoneIntent)
            }

            R.id.btn_move_activity_object -> {
                val person = Person(
                    "Mufkhalif",
                    20,
                    "mufkalif18@gmail.com",
                    "Tangerang"
                )

                val moveWithObjectIntent =
                    Intent(this@MainIntent, MoveWithObjectActivity::class.java)
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)

                startActivity(moveWithObjectIntent)
            }

            R.id.btn_move_for_result -> {
                val moveResultIntent = Intent(this@MainIntent, MoveForResultActivty::class.java)
                startActivityForResult(moveResultIntent, REQUEST_CODE)
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == REQUEST_CODE) {
            if(resultCode == MoveForResultActivty.RESULT_CODE) {
                val selectedValue = data?.getIntExtra(MoveForResultActivty.RESULT_CODE.toString(), 0)
                tvResult.text = "Hasil: $selectedValue"
            }
        }
    }
}
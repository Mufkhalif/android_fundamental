package com.example.barvolumn.testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.barvolumn.R
import com.example.barvolumn.databinding.ActivityMain4Binding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainViewModel: MainViewModel
    private var messageError: String = "Field ini tidak boleh kosong"
    private lateinit var binding: ActivityMain4Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = MainViewModel(CuboidModel())

        binding.btnSave.setOnClickListener(this)
        binding.btnCalculateVolume.setOnClickListener(this)
        binding.btnCalculateCircumference.setOnClickListener(this)
        binding.btnCalculateSurfaceArea.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val length = binding.edtLength.text.toString().trim()
        val width = binding.edtWidth.text.toString().trim()
        val height = binding.editHeight.text.toString().trim()

        when {
            length.isEmpty() -> binding.edtLength.error = messageError
            width.isEmpty() -> binding.edtWidth.error = messageError
            height.isEmpty() -> binding.editHeight.error = messageError

            else -> {
                val l = length.toDouble()
                val w = width.toDouble()
                val h = height.toDouble()

                when (v?.id) {
                    R.id.btn_save -> {
                        mainViewModel.save(l, w, h)
                        visible()
                    }
                    R.id.btn_calculate_circumference -> {
                        val result = mainViewModel.getCircumreference().toString()

                        Log.d("btn_calculate_circumference", result)
                        binding.tvResultTesting.text = result
                        gone()
                    }
                    R.id.btn_calculate_surface_area -> {
                        val result = mainViewModel.getSurfaceArea().toString()

                        Log.d("btn_calculate_surface_area", result)
                        binding.tvResultTesting.text = result
                        gone()
                    }
                    R.id.btn_calculate_volume -> {
                        val result = mainViewModel.getVolume().toString()

                        Log.d("btn_calculate_volume", result)
                        binding.tvResultTesting.text = result
                        gone()
                    }
                }
            }
        }
    }

    private fun visible() {
        binding.btnCalculateVolume.visibility = View.VISIBLE
        binding.btnCalculateCircumference.visibility = View.VISIBLE
        binding.btnCalculateSurfaceArea.visibility = View.VISIBLE
        binding.btnSave.visibility = View.GONE
    }

    private fun gone() {
        binding.btnCalculateVolume.visibility = View.GONE
        binding.btnCalculateCircumference.visibility = View.GONE
        binding.btnCalculateSurfaceArea.visibility = View.GONE
        binding.btnSave.visibility = View.VISIBLE
    }
}
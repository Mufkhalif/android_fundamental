package com.example.myreadwritefile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.myreadwritefile.databinding.ActivityFormUserPreferenceBinding

class FormUserPreferenceActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_TYPE_FORM = "extra_type_form"
        const val EXTRA_RESULT = "extra_result"
        const val RESULT_CODE = 101

        const val TYPE_ADD = 1
        const val TYPE_EDIT = 2

        private const val FIELD_REQUIRED = "Field tidak boleh kosong"
        private const val FIELD_DIGIT_ONLY = "Hanya boleh terisi numerik"
        private const val FIELD_IS_NOT_VALID = "Email tidak valid"
    }

    private lateinit var userModel: UserModel
    private lateinit var binding: ActivityFormUserPreferenceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormUserPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener(this)

        userModel = intent.getParcelableExtra<UserModel>("USER") as UserModel
        val formType = intent.getIntExtra(EXTRA_TYPE_FORM, 0)

        var actionbarTitle = ""
        var btnTitle = ""

        when (formType) {
            TYPE_ADD -> {
                actionbarTitle = "Tambah baru"
                btnTitle = "Simpan"
            }
            TYPE_EDIT -> {
                actionbarTitle = "Ubah"
                btnTitle = "Update"
                showPreferencesInForm()
            }
        }

        supportActionBar?.title = actionbarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSave.text = btnTitle
    }

    private fun showPreferencesInForm() {
        binding.edtName.setText(userModel.name)
        binding.edtEmail.setText(userModel.email)
        binding.edtAge.setText(userModel.age.toString())
        binding.edtPhone.setText(userModel.phoneNumber)

        if (userModel.isLove) {
            binding.rbYes.isChecked = true
        } else {
            binding.rbNo.isChecked = true
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            if (v.id == R.id.btn_save) {
                val name = binding.edtName.text.toString().trim()
                val email = binding.edtEmail.text.toString().trim()
                val age = binding.edtAge.text.toString().trim()
                val phoneNo = binding.edtPhone.text.toString().trim()
                val isLoveMu = binding.rgLoveMu.checkedRadioButtonId == R.id.rb_yes

                if (name.isEmpty()) {
                    binding.edtName.error = FIELD_REQUIRED
                    return
                }

                if (email.isEmpty()) {
                    binding.edtEmail.error = FIELD_REQUIRED
                    return
                }

                if (!isValidEmail(email)) {
                    binding.edtEmail.error = FIELD_IS_NOT_VALID
                    return
                }

                if (age.isEmpty()) {
                    binding.edtAge.error = FIELD_REQUIRED
                    return
                }

                if (phoneNo.isEmpty()) {
                    binding.edtPhone.error = FIELD_REQUIRED
                    return
                }

                if (!TextUtils.isDigitsOnly(phoneNo)) {
                    binding.edtPhone.error = FIELD_DIGIT_ONLY
                    return
                }

                saveUser(name, email, age, phoneNo, isLoveMu)

                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_RESULT, userModel)
                setResult(RESULT_CODE, resultIntent)
                finish()
            }
        }
    }

    private fun saveUser(
        name: String,
        email: String,
        age: String,
        phoneNu: String,
        isLoveMu: Boolean
    ) {

        val userPreference = UserPreference(this)
        userModel.name = name
        userModel.email = email
        userModel.age = age.toInt()
        userModel.phoneNumber = phoneNu
        userModel.isLove = isLoveMu

        userPreference.setUser(userModel)

        Toast.makeText(this, "Data tersimpan", Toast.LENGTH_SHORT).show()
    }

    private fun isValidEmail(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
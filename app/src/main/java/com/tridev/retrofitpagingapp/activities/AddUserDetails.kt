package com.tridev.retrofitpagingapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.tridev.retrofitpagingapp.databinding.ActivityAddUserDetailsBinding
import com.tridev.retrofitpagingapp.db.UserEntity
import com.tridev.retrofitpagingapp.viewmodel.LocalViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserDetails : AppCompatActivity() {

    lateinit var binding: ActivityAddUserDetailsBinding
    private val localViewModel: LocalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClick()
    }

    private fun onClick() {
        binding.btnSubmit.setOnClickListener {
            addUser(it)
            finish()
        }

        binding.imgCancel.setOnClickListener{
            finish()
        }
    }


    private fun addUser(view: View) {
        val firstName = binding.edtFirstName.text.toString()
        val lastName = binding.edtLastName.text.toString()
        val email = binding.edtEmail.text.toString()
        if (firstName.isNotEmpty() || lastName.isNotEmpty() || email.isNotEmpty()) {
            localViewModel.insertUsers(UserEntity(email, firstName ,lastName))
            Snackbar.make(
                view, "Saved Successfully",
                Snackbar.LENGTH_SHORT
            ).show()

        } else {
            val toast = Toast.makeText(
                this,
                "Fields can not be empty",
                Toast.LENGTH_SHORT
            )
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }
}
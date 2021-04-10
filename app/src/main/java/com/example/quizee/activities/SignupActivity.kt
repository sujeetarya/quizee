package com.example.quizee.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizee.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // firebase instance
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnSignup.setOnClickListener {
            signUpUser()
        }
    }
    /* Register new user */
    private fun signUpUser() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val confirmPassword = binding.etConfirmPassword.text.toString().trim()

        if (email.isBlank()) {
            binding.etEmail.error = "Can't be Empty"
            return
        }
        if (password.isBlank()) {
            binding.etPassword.error = "Can't be Empty"
            return
        }
        if (confirmPassword.isBlank()) {
            binding.etConfirmPassword.error = "Can't be Empty"
            return
        }

        if (password != confirmPassword) {
            binding.etPassword.error = "Password didn't match"
            binding.etConfirmPassword.error = "Password didn't match"
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Error Creating User", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
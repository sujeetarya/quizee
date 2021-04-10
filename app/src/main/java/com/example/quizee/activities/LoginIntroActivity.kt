package com.example.quizee.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizee.databinding.ActivityLoginIntroBinding
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

class LoginIntroActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginIntroBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginIntroBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        firebaseAuth = FirebaseAuth.getInstance()

        /* User already logged in*/
        if (firebaseAuth.currentUser != null) {
            // Toast.makeText(this,"User already logged in",Toast.LENGTH_SHORT)
            redirect("MAIN")
        }


        binding.btnGetStarted.setOnClickListener {
            redirect("LOGIN")
        }
    }

    private fun redirect(name: String) {

        val intent = when (name) {
            "MAIN" -> Intent(this, MainActivity::class.java)
            "LOGIN" -> Intent(this, LoginActivity::class.java)
            else -> throw Exception("No path exists")
        }
        startActivity(intent)
        finish()
    }
}
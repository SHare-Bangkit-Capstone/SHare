package com.rechit.share.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rechit.share.MainActivity
import com.rechit.share.R
import com.rechit.share.databinding.ActivityLoginBinding
import com.rechit.share.ui.dashboard.DashboardFragment

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        Log.d("tes", "The Worldxxxx")
        // Initialize
        auth = Firebase.auth
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val currentUser = auth.currentUser
        if(currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding.btnLogin.setOnClickListener{
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            Log.d("tes", "Masukah $email dan $password")
            signIn(email, password)
        }
        binding.tvSignup.setOnClickListener {
            Log.d("tes", "The World")
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            finish()
        }
    }
    private fun signIn(email: String, password: String){
        Log.d("tes", "The Worldz")
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Login Sukses.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(baseContext, "Login Gagal.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
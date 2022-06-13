package com.rechit.share.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rechit.share.MainActivity
import com.rechit.share.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
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
            signIn(email, password)
        }
        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            finish()
        }
    }
    private fun signIn(email: String, password: String){
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
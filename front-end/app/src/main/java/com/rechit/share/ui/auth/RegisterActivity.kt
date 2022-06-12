package com.rechit.share.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.rechit.share.MainActivity
import com.rechit.share.data.User
import com.rechit.share.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.btnRegister.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val phone_number = binding.phoneNumberEditText.text.toString()
            val age = binding.ageEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            Log.d("tes", "Masukah daftar $email dan $password serta $name")
            signUp(name, phone_number, age, email, password)
        }
    }

    private fun signUp(name: String, phone_number: String, age: String, email: String, password: String) {
        binding.progressBar.isVisible
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {task ->
                if(task.isSuccessful){
                    val db = FirebaseFirestore.getInstance()
                    val userId = auth.currentUser!!.uid
                    val currentUser = FirebaseAuth.getInstance().currentUser
                    val reference = db.collection("User").document(userId).collection("Profile").document("new")
                    val user = User()
                    user.id = currentUser?.uid
                    user.email = currentUser?.email
                    user.name = name
                    user.phone_number = phone_number
                    user.age = age

                    reference.set(user)
                        .addOnSuccessListener {
                            Toast.makeText(baseContext, "Registrasi Sukses.", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                            Log.d("insert", "DocumentSnapshot successfully written!")
                        }
                        .addOnFailureListener { e -> Log.w("insert", "Error writing document", e) }
                } else{
                    !binding.progressBar.isVisible
                    Toast.makeText(baseContext, "Registrasi Gagal.", Toast.LENGTH_SHORT).show()
                }
            }

    }

}
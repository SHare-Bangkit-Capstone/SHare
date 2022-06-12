package com.rechit.share.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.rechit.share.data.User
import com.rechit.share.databinding.ActivityProfileBinding
import com.rechit.share.ui.auth.LoginActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        val db = FirebaseFirestore.getInstance()
        val userId = auth.currentUser!!.uid
        val reference = db.collection("User").document(userId).collection("Profile").document("new")
        reference.get()
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    if(task.result != null){
                        val user = task.result.toObject(User::class.java)
                        if (user != null) {
                            binding.tvName.text = user.name
                            binding.tvEmail.text = user.email
                        }
                    } else{
                        Log.w("query", "Error Querying Document", task.getException() );
                    }
                }
            }
        binding.btnLogout.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
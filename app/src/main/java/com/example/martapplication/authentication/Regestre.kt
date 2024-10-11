package com.example.martapplication.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.martapplication.R
import com.example.martapplication.databinding.ActivityRegestreBinding
import com.google.firebase.auth.FirebaseAuth

class Regestre : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    private val binding: ActivityRegestreBinding by lazy {
        ActivityRegestreBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()

        // Navigate to Register screen when textView15 is clicked
        binding.textView15.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        // Handle registration process
        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail1.text.toString()
            val password = binding.etPassword1.text.toString()
            val confirmPassword = binding.etConfirmpassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    // Create user with email and password
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, RigesterFirstFrame::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty fields are not allowed!!", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle edge-to-edge window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

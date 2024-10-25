package com.example.martapplication.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.martapplication.R
import com.example.martapplication.data.imageforadapter.HelperClass
import com.example.martapplication.databinding.ActivityRegestreBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Regestre : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private val binding: ActivityRegestreBinding by lazy {
        ActivityRegestreBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

// Enable disk persistence (ideally do this in your Application class)
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("Users")

        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail1.text.toString()
            val password = binding.etPassword1.text.toString()
            val confirmPassword = binding.etConfirmpassword.text.toString()
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() && firstName.isNotEmpty() && lastName.isNotEmpty()) {
                if (password == confirmPassword) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val user = firebaseAuth.currentUser
                                user?.let {
                                    val userId = user.uid
                                    val userName = "$firstName $lastName"

                                    // Store user data in Firebase Realtime Database
                                    val userMap = HelperClass(firstName, lastName, email, userName, password)
                                    database.child(userId).setValue(userMap)
                                        .addOnSuccessListener {
                                            // Registration successful
                                            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                                            val intent = Intent(this, RigesterFirstFrame::class.java)
                                            startActivity(intent)
                                            finish()
                                        }
                                        .addOnFailureListener { exception ->
                                            // Handle registration error
                                            Toast.makeText(this, "Registration failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                                        }
                                }
                            } else {
                                // Handle authentication error
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

        binding.textView15.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
}
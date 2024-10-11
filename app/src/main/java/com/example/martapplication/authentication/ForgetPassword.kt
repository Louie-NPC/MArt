package com.example.martapplication.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.martapplication.R
import com.example.martapplication.databinding.ActivityForgetPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import kotlin.random.Random

class ForgetPassword : AppCompatActivity() {
    private val binding: ActivityForgetPasswordBinding by lazy {
        ActivityForgetPasswordBinding.inflate(layoutInflater)
    }

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var generatedOtp: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnSubmit.setOnClickListener {
            val email = binding.etEmail.text.toString()

            if (email.isNotEmpty()) {
                // Send Firebase password reset email
                firebaseAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Generate a 4-digit OTP
                            generatedOtp = generateOtp()

                            // Here, you'd send the OTP to the user via email or SMS
                            sendOtpToUser(email, generatedOtp)

                            // Move to the EnterOtp activity and pass the generated OTP
                            val intent = Intent(this, EnterOtp::class.java)
                            intent.putExtra("otp", generatedOtp)
                            intent.putExtra("email", email)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please enter your email!", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Function to generate a random 4-digit OTP
    private fun generateOtp(): String {
        return (1000..9999).random().toString()
    }

    // Mock function to "send" the OTP to the user's email (replace this with actual logic)
    private fun sendOtpToUser(email: String, otp: String) {
        // Ideally, you would send the OTP via an email service or SMS API here
        Toast.makeText(this, "OTP sent to $email: $otp", Toast.LENGTH_LONG).show()
    }
}

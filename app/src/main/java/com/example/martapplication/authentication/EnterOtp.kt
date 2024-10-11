package com.example.martapplication.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.martapplication.R
import com.example.martapplication.databinding.ActivityEnterOtpBinding
import com.chaos.view.PinView

class EnterOtp : AppCompatActivity() {
    private val binding: ActivityEnterOtpBinding by lazy {
        ActivityEnterOtpBinding.inflate(layoutInflater)
    }

    private lateinit var correctOtp: String // To store the correct OTP passed from previous activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Get the OTP and email from the previous activity
        correctOtp = intent.getStringExtra("otp") ?: ""
        val email = intent.getStringExtra("email") ?: ""

        // Display the email in the TextView
        binding.textView24.text = "A 4-digit code has been sent to \n$email"

        // Handle OTP verification
        binding.btnVerify.setOnClickListener {
            val enteredOtp = binding.otpViewOtpview.text.toString()

            if (enteredOtp.isNotEmpty() && enteredOtp == correctOtp) {
                // If OTP matches, proceed to Reset Password activity
                val intent = Intent(this, ResetPassword::class.java)
                startActivity(intent)
            } else {
                // Show error message if OTP doesn't match
                Toast.makeText(this, "Invalid OTP! Please try again.", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle resend OTP (if needed, you'd re-generate OTP and resend it here)
        binding.textView26.setOnClickListener {
            // Logic for resending OTP
            Toast.makeText(this, "Resending OTP...", Toast.LENGTH_SHORT).show()
            // You can call your resend OTP method here
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

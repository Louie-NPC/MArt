package com.example.martapplication.authentication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.martapplication.MainActivity
import com.example.martapplication.R
import com.example.martapplication.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {
    private val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    private var failedAttempts = 0
    private val maxAttempts = 5
    private val lockoutTime = 5 * 60 * 1000L // 5 minutes in milliseconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Initialize Firebase Auth and SharedPreferences
        firebaseAuth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE)

        // Check if user is locked out
        checkLockout()

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Check if user is still locked out before proceeding
                if (isLockedOut()) {
                    Toast.makeText(this, "You are temporarily locked out. Try again later.", Toast.LENGTH_SHORT).show()
                } else {
                    // Sign in with email and password
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            resetFailedAttempts() // Reset attempts on successful login
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            handleFailedAttempt() // Handle failed login attempt
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Empty fields are not allowed!!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.textView6.setOnClickListener {
            val intent = Intent(this, Regestre::class.java) // Check class name for registration
            startActivity(intent)
        }

        binding.textView4.setOnClickListener {
            val intent = Intent(this, ForgetPassword::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Function to handle failed login attempts
    private fun handleFailedAttempt() {
        failedAttempts++
        if (failedAttempts >= maxAttempts) {
            val lockoutEndTime = SystemClock.elapsedRealtime() + lockoutTime
            sharedPreferences.edit()
                .putLong("lockoutEndTime", lockoutEndTime)
                .putInt("failedAttempts", failedAttempts)
                .apply()
            Toast.makeText(this, "Too many failed attempts. Try again in 5 minutes.", Toast.LENGTH_SHORT).show()
        } else {
            sharedPreferences.edit().putInt("failedAttempts", failedAttempts).apply()
            Toast.makeText(this, "Incorrect email or password. Attempts left: ${maxAttempts - failedAttempts}", Toast.LENGTH_SHORT).show()
        }
    }

    // Function to check if the user is currently locked out
    private fun isLockedOut(): Boolean {
        val lockoutEndTime = sharedPreferences.getLong("lockoutEndTime", 0L)
        return SystemClock.elapsedRealtime() < lockoutEndTime
    }

    // Function to reset failed attempts after a successful login
    private fun resetFailedAttempts() {
        sharedPreferences.edit().putInt("failedAttempts", 0).apply()
    }

    // Function to check lockout status when the activity starts
    private fun checkLockout() {
        failedAttempts = sharedPreferences.getInt("failedAttempts", 0)
        if (isLockedOut()) {
            Toast.makeText(this, "You are temporarily locked out. Try again later.", Toast.LENGTH_SHORT).show()
        }
    }
}

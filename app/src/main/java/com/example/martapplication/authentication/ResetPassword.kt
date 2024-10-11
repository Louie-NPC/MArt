    package com.example.martapplication.authentication

    import android.content.Intent
    import android.os.Bundle
    import android.widget.Toast
    import androidx.activity.enableEdgeToEdge
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat
    import com.example.martapplication.R
    import com.example.martapplication.databinding.ActivityResetPasswordBinding
    import com.google.firebase.auth.EmailAuthProvider
    import com.google.firebase.auth.FirebaseAuth

    class ResetPassword : AppCompatActivity() {
        private val binding: ActivityResetPasswordBinding by lazy {
            ActivityResetPasswordBinding.inflate(layoutInflater)
        }

        private lateinit var firebaseAuth: FirebaseAuth

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(binding.root)

            firebaseAuth = FirebaseAuth.getInstance()

            binding.btnConfirm.setOnClickListener {
                val newPassword = binding.etNewpassword1.text.toString()
                val confirmPassword = binding.etConfirmpassword.text.toString()

                if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(this, "Please enter both fields", Toast.LENGTH_SHORT).show()
                } else if (newPassword != confirmPassword) {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                } else if (newPassword.length < 6) {
                    Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show()
                } else {
                    // Call re-authentication instead of directly resetting the password
                    reAuthenticateUser(newPassword)
                }
            }

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        // This is the re-authentication function
        private fun reAuthenticateUser(newPassword: String) {
            val user = firebaseAuth.currentUser
            user?.let {
                // Assuming the user signed in with email and password, ask for their current password
                val currentPassword = binding.etNewpassword.text.toString()  // You need a field for the current password
                val credential = EmailAuthProvider.getCredential(user.email!!, currentPassword)

                user.reauthenticate(credential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // If re-authentication is successful, proceed to reset the password
                            resetPassword(newPassword)
                        } else {
                            Toast.makeText(this, "Re-authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } ?: run {
                Toast.makeText(this, "No authenticated user found", Toast.LENGTH_SHORT).show()
            }
        }

        // This remains the same
        private fun resetPassword(newPassword: String) {
            val user = firebaseAuth.currentUser
            user?.let {
                user.updatePassword(newPassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Password successfully reset", Toast.LENGTH_SHORT).show()
                            // Navigate to the PasswordChangeFirstFrame activity
                            val intent = Intent(this, PasswordChangeFirstFrame::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            } ?: run {
                Toast.makeText(this, "No authenticated user found", Toast.LENGTH_SHORT).show()
            }
        }
    }


package com.example.martapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.martapplication.authentication.Register
import com.example.martapplication.databinding.ActivitySettingsBinding
import com.example.martapplication.fragment.ProfileFragment
import com.google.firebase.auth.FirebaseAuth

class SettingsActivity : AppCompatActivity() {
    private val binding: ActivitySettingsBinding by lazy {
        ActivitySettingsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Logout button listener
        binding.lagOut.setOnClickListener {
            // Log out from Firebase
            FirebaseAuth.getInstance().signOut()

            // Clear stored user data in SharedPreferences
            val sharedPref = getSharedPreferences("UserPref", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.clear() // This clears all stored values in UserPref
            editor.apply()

            val intent = Intent(this, Register::class.java)
            startActivity(intent)
            finish() // Finish SettingsActivity after logout
        }

            // Set up other listeners
        binding.textView60.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        binding.imageView15.setOnClickListener {
            val intent = Intent(this, ProfileFragment::class.java)
            // ProfileFragment is a Fragment, so use FragmentTransaction instead of Intent
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_fragment, ProfileFragment())
                .addToBackStack(null)
                .commit()
        }

        // Apply window insets to the root layout or a valid view
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

package com.example.martapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.martapplication.databinding.ActivityOverviewBinding
import com.example.martapplication.databinding.ActivityProductOverviewBinding

class OverviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOverviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val overViewButton = binding.overViewButton // Use binding to access views
        overViewButton.setOnClickListener {
            // If you want to go back to HomeFragment, you should launch its parent activity
            val intent = Intent(
                this, ProductOverviewActivity::class.java
            ) // Assuming MainActivity hosts HomeFragment
            startActivity(intent)
        }
    }
}

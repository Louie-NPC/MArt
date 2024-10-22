package com.example.martapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.martapplication.databinding.ActivityProductOverviewBinding

class ProductOverviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductOverviewBinding
    private var imageRes: Int = 0 // Declare the image resource ID variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Restore image resource if available
        if (savedInstanceState != null) {
            imageRes = savedInstanceState.getInt("image_res", 0)
            binding.imageView7.setImageResource(imageRes)
        } else {
            // Get the image resource passed from the intent
            imageRes = intent.getIntExtra("image_res", 0)
            binding.imageView7.setImageResource(imageRes)
        }

        // Set onClickListener for the arrow button
        val arrowButton = binding.backArrow // Access the back arrow view using binding
        arrowButton.setOnClickListener {
            // Go back to MainActivity, which is assumed to host the HomeFragment
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Set onClickListener for the overview button
        val overviewButton = binding.buttonOverVIew // Access the overview button using binding
        overviewButton.setOnClickListener {
            // Navigate to the OverviewActivity
            val intent = Intent(this, OverviewActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save the image resource ID in the outState bundle
        outState.putInt("image_res", imageRes)
    }
}

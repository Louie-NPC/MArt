package com.example.martapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.martapplication.databinding.ActivityProductOverviewBinding

class ProductOverviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductOverviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadProductImage()

        // Navigate back to MainActivity
        binding.backArrow.setOnClickListener {
            finish()
        }
    }

    private fun loadProductImage() {
        val imageRes = intent.getIntExtra("image_res", -1)
        val imageUri = intent.getStringExtra("imageUri")

        if (imageRes != -1) {
            binding.imageView7.setImageResource(imageRes)
        } else if (imageUri != null) {
            Glide.with(this).load(imageUri).into(binding.imageView7)
        }
    }
}

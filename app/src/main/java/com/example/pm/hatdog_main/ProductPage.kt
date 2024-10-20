package com.example.pm.hatdog_main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pm.R

class ProductPage : AppCompatActivity() {

    private lateinit var homeBackButton: AppCompatImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.product_posting)

        homeBackButton = findViewById<AppCompatImageButton>(R.id.home_back_button)

        homeBackButton.setOnClickListener{
            val intent = Intent(this, hatdog_home_page::class.java)
            startActivity(intent)

        }

    }
}
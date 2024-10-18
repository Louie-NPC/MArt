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

private lateinit var BackArrowProduct: AppCompatImageButton


class CommentPageActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.comment_section)

        BackArrowProduct = findViewById<AppCompatImageButton>(R.id.comment_button_back)

        BackArrowProduct.setOnClickListener{ //back to homepage
            val intent = Intent(this, face_product_template::class.java)
            startActivity(intent)

        }

    }
}
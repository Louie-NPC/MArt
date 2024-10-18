package com.example.pm.hatdog_main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pm.R

class SharePageActivity : AppCompatActivity() {
    private lateinit var OverviewButtonBack: AppCompatButton
    private lateinit var ShareButton: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.share_page_template)

        OverviewButtonBack = findViewById(R.id.overview_button)


        OverviewButtonBack.setOnClickListener{
            val intent = Intent(this, face_product_template::class.java)
            startActivity(intent)

        }

        
    }
}
package com.example.pm.hatdog_main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pm.R

class Overview_template : AppCompatActivity() {
    private lateinit var OverviewButtonBack: AppCompatButton
    private lateinit var ShareButton: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.overview_template)

        OverviewButtonBack = findViewById(R.id.overview_button)
        ShareButton = findViewById(R.id.share_button)

        OverviewButtonBack.setOnClickListener{ //back to homepage
            val intent = Intent(this, face_product_template::class.java)
            startActivity(intent)

        }

        ShareButton.setOnClickListener{ //to go to shar
            val intent = Intent(this, SharePageActivity::class.java)
            startActivity(intent)

        }


    }
}
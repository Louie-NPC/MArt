package com.example.pm.hatdog_main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pm.R

class hatdog_home_page : AppCompatActivity() {
    private lateinit var productOneButton: AppCompatImageButton
    private lateinit var productTwoButton: AppCompatImageButton
    private lateinit var productThreeButton: AppCompatImageButton
    private lateinit var productFourButton: AppCompatImageButton
    private lateinit var productFiveButton: AppCompatImageButton


    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_hatdog_home_page)

        productOneButton = findViewById<AppCompatImageButton>(R.id.productOne)
        productTwoButton = findViewById<AppCompatImageButton>(R.id.productTwo)
        productThreeButton = findViewById<AppCompatImageButton>(R.id.productThree)
        productFourButton = findViewById<AppCompatImageButton>(R.id.productFour)
        productFiveButton = findViewById<AppCompatImageButton>(R.id.productFive)


        productOneButton.setOnClickListener{
            val intent = Intent(this, face_product_template::class.java)
            startActivity(intent)
        }
        productTwoButton.setOnClickListener{
            val intent = Intent(this, face_product_template::class.java)
            startActivity(intent)

        }
        productThreeButton.setOnClickListener{
            val intent = Intent(this, face_product_template::class.java)
            startActivity(intent)

        }
        productFourButton.setOnClickListener{
            val intent = Intent(this, face_product_template::class.java)
            startActivity(intent)

        }
        productFiveButton.setOnClickListener{
            val intent = Intent(this, face_product_template::class.java)
            startActivity(intent)

        }


    }
}